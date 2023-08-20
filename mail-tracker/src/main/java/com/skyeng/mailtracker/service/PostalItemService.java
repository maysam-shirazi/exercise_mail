package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.repository.PostalItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Postal item service.
 */
@Service
public class PostalItemService {
    /**
     * The Repository.
     */
    @Autowired
    PostalItemRepository repository;
    /**
     * The Event service.
     */
    @Autowired
    EventService eventService;
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(PostalItemService.class);

    /**
     * Register item.
     *
     * @param postalItem the postal item
     * @return the item with id
     */
    public Item register(Item postalItem) {
        var item = repository.save(postalItem);
        eventService.addEvent(item, EventType.EVENT_TYPE_ID_REGISTER);
        logger.info("Register item: {}", item);
        return item;
    }

    /**
     * Arrival item.
     *
     * @param itemId the item id
     * @return the arrived item
     */
//TODO: add post office to arrival event
    public Item arrival(long itemId) {
        var item = repository.findById(itemId);
        logger.info("arrival item: {}", itemId);
        if (item.isPresent()) {
            eventService.addEvent(item.get(), EventType.EVENT_TYPE_ID_ARRIVED);
        }
        return item.get();
    }

    /**
     * Departure item.
     *
     * @param itemId the item id
     * @return the departured item
     */
//TODO: add post office to departure event
    public Item departure(long itemId) {
        var item = repository.findById(itemId);
        logger.info("departure item: {}", itemId);
        if (item.isPresent()) {
            eventService.addEvent(item.get(), EventType.EVENT_TYPE_ID_DEPARTED);
        }
        return item.get();
    }

    /**
     * Deliver item.
     *
     * @param itemId the item id
     * @return the delivered item
     */
//TODO: add the receipt model to post item for delivery
    public Item deliver(long itemId) {
        var item = repository.findById(itemId);
        logger.info("deliver item: {}", itemId);
        if (item.isPresent()) {
            eventService.addEvent(item.get(), EventType.EVENT_TYPE_ID_DELIVERED);
        }
        return item.get();
    }

    /**
     * Gets item.
     *
     * @param id the id
     * @return the item
     */
    public Optional<Item> getItem(Long id) {
        var item = repository.findById(id);
        return item;
    }

    /**
     * Add item item.
     *
     * @param postalItem the postal item
     * @return the item
     */
    public Item addItem(Item postalItem) {
        logger.info("addItem item: {}", postalItem);
        return repository.save(postalItem);
    }
}
