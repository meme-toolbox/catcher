package ml.memelau.catcher.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ml.memelau.catcher.server.service.AppGroupService;
import ml.memelau.catcher.server.vo.AppGroupResp;
import ml.memelau.catcher.server.vo.Rule;
import ml.memelau.catcher.server.vo.SendLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("app-groups")
public class AppGroupController {

    @Autowired
    private AppGroupService service;

    @PostMapping
    public void create(@RequestParam String name) {
        service.create(name);
    }

    @GetMapping
    public List<AppGroupResp> list() {
        return service.list();
    }

    @PostMapping("{groupId}/rules")
    public void setRuleByGroupId(@PathVariable Integer groupId, @RequestBody Rule rule) {
        service.setRuleByGroupId(groupId, rule);
    }

    @SneakyThrows
    public static void main(String[] args) {

        Rule rule = new Rule();
        SendLevel sendLevel1 = new SendLevel();
        sendLevel1.setCount(1L);
        sendLevel1.setMailAddresses(Collections.singletonList("liubangzong@mem853.com"));
        SendLevel sendLevel2 = new SendLevel();
        sendLevel2.setCount(1L);
        sendLevel2.setMailAddresses(Collections.singletonList("malapiv617@upcmaill.com"));
        rule.setSendLevels(Arrays.asList(sendLevel1, sendLevel2));
        System.out.println(new ObjectMapper().writeValueAsString(rule));

    }

}
