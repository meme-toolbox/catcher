package ml.memelau.catcher.server.controller;

import ml.memelau.catcher.event.ErrorEvent;
import ml.memelau.catcher.server.model.ErrorEventCount;
import ml.memelau.catcher.server.service.ErrorEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("error-events")
public class ErrorEventController {

    @Autowired
    private ErrorEventService service;

    @PostMapping
    public void receive(@RequestBody ErrorEvent.DefaultErrorEvent event, @RequestParam("access_key") String accessKey) {
        service.receive(event, accessKey);
    }

    @GetMapping
    public List<ml.memelau.catcher.server.model.ErrorEvent> listByDuration(@RequestParam Integer groupId,
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam Date startTime,
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam Date endTime) {
        return service.listByDuration(groupId, startTime, endTime);
    }

    @GetMapping("counts")
    public List<ErrorEventCount> listByEventId(@RequestParam Integer eventId) {
        return service.listByEventId(eventId);
    }

    @PutMapping("{id}/handling-state")
    public void setHandlingState(@PathVariable Integer id, @RequestParam Integer handlingState) {
        service.setHandlingState(id, handlingState);
    }

}
