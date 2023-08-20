package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.model.postalitem.ItemMinimal;
import com.skyeng.mailtracker.service.EventService;
import com.skyeng.mailtracker.service.PostalItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Postal item controller.
 */
@RestController
@RequestMapping("/postal-item")
public class PostalItemController {
    /**
     * The Service.
     */
    @Autowired
    PostalItemService service;
    /**
     * The Event service.
     */
    @Autowired
    EventService eventService;

    /**
     * Register the item.
     *
     * @param item the item
     * @return the response entity
     */
    @PostMapping("/register")
    public ResponseEntity<Item> register(@RequestBody Item item) {
        var pi = service.register(item);
        return new ResponseEntity<Item>(pi, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Arrival the item.
     *
     * @param item the item
     * @return the response entity
     */
    @PostMapping("/arrival")
    public ResponseEntity<Item> arrival(@RequestBody ItemMinimal item) {
        var pi = service.arrival(item.getId());
        return new ResponseEntity<Item>(pi, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Departure the item.
     *
     * @param item the item
     * @return the response entity
     */
    @PostMapping("/departure")
    public ResponseEntity<Item> departure(@RequestBody ItemMinimal item) {
        var pi = service.departure(item.getId());
        return new ResponseEntity<Item>(pi, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Deliver the item.
     *
     * @param item the item
     * @return the response entity
     */
    @PostMapping("/deliver")
    public ResponseEntity<Item> deliver(@RequestBody ItemMinimal item) {
        var pi = service.deliver(item.getId());
        return new ResponseEntity<Item>(pi, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Gets postal item.
     *
     * @param itemid the itemid
     * @return the postal item
     */
    @GetMapping
    public ResponseEntity<Item> getPostalItem(@RequestParam Long itemid) {
        var item = service.getItem(itemid);
        return new ResponseEntity<Item>(item.isPresent()?item.get():new Item(), new HttpHeaders(), HttpStatus.OK);
    }

}
