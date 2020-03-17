package ml.memelau.catcher.server.controller;

import ml.memelau.catcher.server.model.AppGroup;
import ml.memelau.catcher.server.service.AppGroupService;
import ml.memelau.catcher.server.vo.AppGroupResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
