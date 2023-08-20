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

@RestController
@RequestMapping("/postal-item")
public class PostalItemController {
    @Autowired
    PostalItemService service;
    @Autowired
    EventService eventService;

    @PostMapping("/register")
    public ResponseEntity<Item> register(@RequestBody Item item) {
        var pi = service.register(item);
        return new ResponseEntity<Item>(pi, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/arrival")
    public ResponseEntity<Item> arrival(@RequestBody ItemMinimal item) {
        var pi = service.arrival(item.getId());
        return new ResponseEntity<Item>(pi, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/departure")
    public ResponseEntity<Item> departure(@RequestBody ItemMinimal item) {
        var pi = service.departure(item.getId());
        return new ResponseEntity<Item>(pi, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/deliver")
    public ResponseEntity<Item> deliver(@RequestBody ItemMinimal item) {
        var pi = service.departure(item.getId());
        return new ResponseEntity<Item>(pi, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Item> getPostalItem(@RequestParam Long itemid) {
        var item = service.getItem(itemid);
        return new ResponseEntity<Item>(item.get(), new HttpHeaders(), HttpStatus.OK);
    }

}
