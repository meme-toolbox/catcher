package ml.memelau.catcher.server.service;

import com.google.common.hash.Hashing;
import ml.memelau.catcher.event.ErrorEvent;
import ml.memelau.catcher.server.mapper.ErrorEventCountMapper;
import ml.memelau.catcher.server.mapper.ErrorEventMapper;
import ml.memelau.catcher.server.model.AppGroup;
import ml.memelau.catcher.server.model.ErrorEventExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
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

        }


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
}
