package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.repository.PostalItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostalItemService {
    @Autowired
    PostalItemRepository repository;
    @Autowired
    EventService eventService;
    Logger logger = LoggerFactory.getLogger(PostalItemService.class);

    public Item register(Item postalItem) {
        var item = repository.save(postalItem);
        eventService.addEvent(item, EventType.EVENT_TYPE_ID_REGISTER);
        logger.info("Register item: {}", item);
        return item;
    }

    public Item arrival(long itemId) {
        var item = repository.findById(itemId);
        logger.info("arrival item: {}", itemId);
        if (item.isPresent()) {
            eventService.addEvent(item.get(), EventType.EVENT_TYPE_ID_ARRIVED);
        }
        return item.get();
    }

    public Item departure(long itemId) {
        var item = repository.findById(itemId);
        logger.info("departure item: {}", itemId);
        if (item.isPresent()) {
            eventService.addEvent(item.get(), EventType.EVENT_TYPE_ID_DEPARTED);
        }
        return item.get();
    }

    public Item deliver(long itemId) {
        var item = repository.findById(itemId);
        logger.info("deliver item: {}", itemId);
        if (item.isPresent()) {
            eventService.addEvent(item.get(), EventType.EVENT_TYPE_ID_DELIVERED);
        }
        return item.get();
    }

    public Optional<Item> getItem(Long id) {
        var item = repository.findById(id);
        return item;
    }

    public Item addItem(Item postalItem) {
        logger.info("addItem item: {}", postalItem);
        return repository.save(postalItem);
    }
}
