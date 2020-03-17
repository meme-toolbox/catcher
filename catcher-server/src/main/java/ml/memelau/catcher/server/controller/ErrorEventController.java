package ml.memelau.catcher.server.controller;

import ml.memelau.catcher.event.ErrorEvent;
import ml.memelau.catcher.server.service.ErrorEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("error-events")
public class ErrorEventController {

    @Autowired
    private ErrorEventService service;

    @PostMapping
    public void receive(@RequestBody ErrorEvent.DefaultErrorEvent event) {
        service.receive(event);
    }

}
