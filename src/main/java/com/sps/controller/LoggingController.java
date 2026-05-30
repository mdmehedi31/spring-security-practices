package com.sps.controller;

import com.sps.entity.LoggingEntity;
import com.sps.service.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
public class LoggingController {

    @Autowired
    private LoggingService loggingService;

    private static final Logger log = LoggerFactory.getLogger(LoggingController.class);

    @PostMapping("/add-log")
    public ResponseEntity<LoggingEntity> createLoggingEntity(@RequestBody LoggingEntity loggingEntity) {
        LoggingEntity response = this.loggingService.saveLog(loggingEntity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-log")
    public ResponseEntity<LoggingEntity> getLogById(@PathVariable Integer logId) {
        LoggingEntity loggingEntity = this.loggingService.getLoggedById(logId);
        return new ResponseEntity<>(loggingEntity, HttpStatus.OK);
    }
}
