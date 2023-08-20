package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.service.PostalItemTypeService;
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
@RequestMapping("/postal-item-types")
public class PostalItemTypeController {
    @Autowired
    PostalItemTypeService service;
    Logger logger = LoggerFactory.getLogger(PostalItemTypeController.class);
    @GetMapping
    public ResponseEntity<List<ItemType>> getPostalItemTypes() {
        List<ItemType> list = service.getPostalItemTypes();
        return new ResponseEntity<List<ItemType>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
