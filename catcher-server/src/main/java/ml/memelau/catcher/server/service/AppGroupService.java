package ml.memelau.catcher.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ml.memelau.catcher.server.mapper.AppGroupMapper;
import ml.memelau.catcher.server.model.AppGroup;
import ml.memelau.catcher.server.model.AppGroupExample;
import ml.memelau.catcher.server.vo.AppGroupResp;
import ml.memelau.catcher.server.vo.Rule;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppGroupService {

    @Autowired
    private AppGroupMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public void create(String name) {
        Date now = new Date();
        AppGroup appGroup = new AppGroup();
        appGroup.setAccessKey(UUID.randomUUID().toString());
        appGroup.setCreateTime(now);
        appGroup.setGroupName(name);
        appGroup.setUpdateTime(now);
        appGroup.setDeleted(false);
        appGroup.setRule("{}");
        mapper.insert(appGroup);
    }

    public List<AppGroupResp> list() {
        return mapper.selectByExample(new AppGroupExample())
                     .stream()
                     .map(appGroup -> {
                         AppGroupResp appGroupResp = new AppGroupResp();
                         BeanUtils.copyProperties(appGroup, appGroupResp);
                         try {
                             appGroupResp.setRule(objectMapper.readValue(appGroup.getRule(), Rule.class));
                         } catch (JsonProcessingException e) {
                             throw new RuntimeException(e);
                         }
                         return appGroupResp;
                     })
                     .collect(Collectors.toList());
    }

    public Optional<AppGroup> getByAccessKey(String accessKey) {
        AppGroupExample example = new AppGroupExample();
        example.createCriteria()
               .andAccessKeyEqualTo(accessKey);
        return mapper.selectByExample(example)
                     .stream()
                     .findAny();
    }

    public Optional<Rule> getRuleById(Integer id) {
        AppGroupExample example = new AppGroupExample();
        example.createCriteria()
               .andIdEqualTo(id);
        return mapper.selectByExample(example)
                     .stream()
                     .map(AppGroup::getRule)
                     .map(ruleStr -> {
                         try {
                             return objectMapper.readValue(ruleStr, Rule.class);
                         } catch (JsonProcessingException e) {
                             throw new RuntimeException(e);
                         }
                     })
                     .findAny();
    }
}
