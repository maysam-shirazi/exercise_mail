package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.service.PostOfficeService;
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

/**
 * The type Post office controller.
 */
@RestController
@RequestMapping("/post-offices")
public class PostOfficeController {
    /**
     * The Service.
     */
    @Autowired
    PostOfficeService service;
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(PostOfficeController.class);

    /**
     * Gets post offices.
     *
     * @return the post offices
     */
    @GetMapping
    public ResponseEntity<List<PostOffice>> getPostOffices() {
        var list = service.getPostOffices();
        return new ResponseEntity<List<PostOffice>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
