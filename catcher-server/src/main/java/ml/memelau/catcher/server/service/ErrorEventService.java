package ml.memelau.catcher.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.SneakyThrows;
import ml.memelau.catcher.event.ErrorEvent;
import ml.memelau.catcher.server.mapper.ErrorEventCountMapper;
import ml.memelau.catcher.server.mapper.ErrorEventMapper;
import ml.memelau.catcher.server.model.AppGroup;
import ml.memelau.catcher.server.model.ErrorEventCount;
import ml.memelau.catcher.server.model.ErrorEventCountExample;
import ml.memelau.catcher.server.model.ErrorEventExample;
import ml.memelau.catcher.server.vo.Rule;
import ml.memelau.catcher.server.vo.SendLevel;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("UnstableApiUsage")
@Service
public class ErrorEventService {

    @Autowired
    private AppGroupService appGroupService;

    @Autowired
    private ErrorEventMapper errorEventMapper;

    @Autowired
    private ErrorEventCountMapper errorEventCountMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @SneakyThrows
    public void receive(ErrorEvent.DefaultErrorEvent event, String accessKey) {
        Integer groupId = appGroupService.getByAccessKey(accessKey)
                                         .map(AppGroup::getId)
                                         .orElse(0);

        ml.memelau.catcher.server.model.ErrorEvent errorEvent = new ml.memelau.catcher.server.model.ErrorEvent();
        errorEvent.setEventType(event.getEventType());
        errorEvent.setErrorType(event.getErrorType());
        errorEvent.setAppName(event.getAppName());
        errorEvent.setEnv(event.getEnv());
        errorEvent.setHostname(event.getHostname());
        errorEvent.setIp(event.getIp());
        errorEvent.setGroupId(groupId);
        errorEvent.setCreateTime(Date.from(event.getOccurredTime().atZone(ZoneId.systemDefault()).toInstant()));
        errorEvent.setUpdateTime(Date.from(event.getOccurredTime().atZone(ZoneId.systemDefault()).toInstant()));
        errorEvent.setHandlingState(0);
        errorEvent.setHandlingTimes(0);
        errorEvent.setErrorMessage(event.getErrorMessage());
        //详情散列（事件类型，错误类型，信息详情，环境，应用名称，项目组id）
        errorEvent.setEventHash(getEventHash(errorEvent));

        ErrorEventExample example = new ErrorEventExample();
        example.createCriteria()
               .andEventHashEqualTo(errorEvent.getEventHash());
        Integer errorEventId = errorEventMapper.selectByExample(example)
                                               .stream()
                                               .findAny()
                                               .map(ml.memelau.catcher.server.model.ErrorEvent::getId)
                                               .orElse(null);
        if (Objects.isNull(errorEventId)) {
            errorEventMapper.insert(errorEvent);
        }

        errorEventId = errorEventMapper.selectByExample(example)
                                       .stream()
                                       .findAny()
                                       .map(ml.memelau.catcher.server.model.ErrorEvent::getId)
                                       .orElse(null);

        ErrorEventCount errorEventCount = new ErrorEventCount();
        errorEventCount.setEventId(errorEventId);
        errorEventCount.setOccurredTime(Date.from(event.getOccurredTime().atZone(ZoneId.systemDefault()).toInstant()));
        errorEventCount.setAdditions(objectMapper.writeValueAsString(event.getAdditions()));
        errorEventCountMapper.insert(errorEventCount);

        //TODO event driven
        alarm(groupId, errorEventId);

    }

    private void alarm(Integer groupId, Integer errorEventId) {
        ErrorEventCountExample example = new ErrorEventCountExample();
        example.createCriteria()
               .andEventIdEqualTo(errorEventId);
        long count = errorEventCountMapper.countByExample(example);

        ml.memelau.catcher.server.model.ErrorEvent errorEvent = errorEventMapper.selectByPrimaryKey(errorEventId);
        if (Objects.equals(errorEvent.getHandlingState(), 0)) {
            appGroupService.getRuleById(groupId)
                           .ifPresent(rule -> sendAlarmByRule(rule, count, errorEvent));
        }
    }

    private void sendAlarmByRule(Rule rule, long count, ml.memelau.catcher.server.model.ErrorEvent errorEvent) {
        rule.getSendLevels()
            .stream()
            .filter(sendLevel -> sendLevel.getCount() <= count)
            .max(Comparator.comparing(SendLevel::getCount))
            .ifPresent(sendLevel -> sendLevel.getMailAddresses()
                                             .forEach(address -> sendAlarm(address, errorEvent))
            );

    }

    private void sendAlarm(String address, ml.memelau.catcher.server.model.ErrorEvent errorEvent) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(address);
        mail.setSubject(getTitle(errorEvent));
        mail.setText(getContent(errorEvent));
        mail.setFrom(from);
        mailSender.send(mail);
    }

    private String getContent(ml.memelau.catcher.server.model.ErrorEvent errorEvent) {
        return String.format(
                ALARM_FORMAT,
                errorEvent.getId(),
                errorEvent.getErrorType(),
                errorEvent.getHostname(),
                errorEvent.getIp(),
                errorEvent.getErrorMessage());
    }

    private static final String ALARM_FORMAT =
            "错误ID: %s\n错误类型: %s\nhostname: %s\nIp: %s\n错误信息: %s\n";

    public String getTitle(ml.memelau.catcher.server.model.ErrorEvent errorEvent) {
        return String.format("警报:[%s][%s][%s][%s]", errorEvent.getEnv(), errorEvent.getCreateTime(), errorEvent.getAppName(), errorEvent.getErrorType());
    }

    private String getEventHash(ml.memelau.catcher.server.model.ErrorEvent errorEvent) {
        return Hashing.sha256().hashObject(errorEvent, (errorEvent1, funnel) ->
            funnel.putUnencodedChars(errorEvent1.getAppName())
                .putUnencodedChars(errorEvent1.getEnv())
                .putUnencodedChars(errorEvent1.getErrorMessage())
                .putUnencodedChars(errorEvent1.getErrorType())
                .putUnencodedChars(errorEvent1.getEventType())
                .putInt(errorEvent1.getGroupId())
        ).toString();
    }

    public List<ml.memelau.catcher.server.model.ErrorEvent> listByDuration(Integer groupId, Date startTime, Date endTime) {
        ErrorEventExample example = new ErrorEventExample();
        example.createCriteria()
               .andGroupIdEqualTo(groupId)
               .andUpdateTimeBetween(startTime, endTime);
        return errorEventMapper.selectByExampleWithBLOBs(example);
    }

    public List<ErrorEventCount> listByEventId(Integer eventId) {
        ErrorEventCountExample example = new ErrorEventCountExample();
        example.createCriteria()
               .andEventIdEqualTo(eventId);
        example.setOrderByClause(" ORDER BY id DESC");
        return errorEventCountMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(0, 20));
    }

    public void setHandlingState(Integer id, Integer handlingState) {
        ml.memelau.catcher.server.model.ErrorEvent errorEvent = new ml.memelau.catcher.server.model.ErrorEvent();
        errorEvent.setId(id);
        errorEvent.setHandlingState(handlingState);
        errorEventMapper.updateByPrimaryKeySelective(errorEvent);
    }

    public ml.memelau.catcher.server.model.ErrorEvent getById(Integer eventId) {
        return errorEventMapper.selectByPrimaryKey(eventId);
    }
}
