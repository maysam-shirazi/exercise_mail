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

/**
 * The type Event controller.
 */
@RestController
@RequestMapping("/event")
public class EventController {
    /**
     * The Service.
     */
    @Autowired
    EventService service;
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(EventController.class);

    /**
     * Gets postal item events.
     *
     * @param itemid the itemid
     * @return the postal item events
     */
    @GetMapping
    public ResponseEntity<List<Event>> getPostalItemEvents(@RequestParam Long itemid) {
        var list = service.getEvents(itemid);
        return new ResponseEntity<List<Event>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Gets postal item latest event.
     *
     * @param itemid the itemid
     * @return the postal item latest event
     */
    @GetMapping("/item-status")
    public ResponseEntity<Event> getPostalItemLatestEvent(@RequestParam Long itemid) {
        var event = service.getLatestEvent(itemid);
        return new ResponseEntity<Event>(event, new HttpHeaders(), HttpStatus.OK);

    }
}
