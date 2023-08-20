package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.postalitem.Event;
import com.skyeng.mailtracker.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService service;
    Logger logger = LoggerFactory.getLogger(EventController.class);
    @GetMapping
    public ResponseEntity<List<Event>> getPostalItemEvents(@RequestParam Long itemid) {
        var list = service.getEvents(itemid);
        return new ResponseEntity<List<Event>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/item-status")
    public ResponseEntity<Event> getPostalItemLatestEvent(@RequestParam Long itemid) {
        var event = service.getLatestEvent(itemid);
        return new ResponseEntity<Event>(event, new HttpHeaders(), HttpStatus.OK);

    }
}
