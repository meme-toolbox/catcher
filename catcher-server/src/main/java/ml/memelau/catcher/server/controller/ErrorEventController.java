package ml.memelau.catcher.server.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation("获取报警列表")
    @GetMapping
    public List<ml.memelau.catcher.server.model.ErrorEvent> listByDuration(@ApiParam("应用组ID") @RequestParam Integer groupId,
                                                                           @ApiParam("开始时间 yyyy-MM-dd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam Date startTime,
                                                                           @ApiParam("结束时间 yyyy-MM-dd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam Date endTime) {
        return service.listByDuration(groupId, startTime, endTime);
    }


    @ApiOperation("根据ID获取报警")
    @GetMapping("event")
    public ml.memelau.catcher.server.model.ErrorEvent getById(@RequestParam Integer eventId) {
        return service.getById(eventId);
    }

    @ApiOperation("获取报警详情列表")
    @GetMapping("counts")
    public List<ErrorEventCount> listByEventId(@RequestParam Integer eventId) {
        return service.listByEventId(eventId);
    }

    @ApiOperation("设置报警状态")
    @PutMapping("handling-state")
    public void setHandlingState(@ApiParam("报警ID") @RequestParam Integer id,
                                 @ApiParam("处理状态 0 待处理（只能选忽略或处理中）\n" +
                                         "1 处理中（只能选忽略或待部署）\n" +
                                         "2 待部署（只能选已处理）\n" +
                                         "3 已处理\n" +
                                         "-1 忽略（只能选处理中）\n" +
                                         "-2 复现") @RequestParam Integer handlingState) {
        service.setHandlingState(id, handlingState);
    }

}
