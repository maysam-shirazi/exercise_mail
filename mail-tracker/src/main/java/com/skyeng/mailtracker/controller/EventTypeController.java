package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.service.EventTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event-types")
public class EventTypeController {
    @Autowired
    EventTypeService service;
    Logger logger = LoggerFactory.getLogger(EventTypeController.class);
    @GetMapping
    public ResponseEntity<List<EventType>> getAllEventTypes() {
        var list = service.getEventTypes();
        logger.trace(list.get(0).getTitle());
        return new ResponseEntity<List<EventType>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
