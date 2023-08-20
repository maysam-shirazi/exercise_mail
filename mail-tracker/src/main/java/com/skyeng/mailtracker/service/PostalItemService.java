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
     * The PostOffice service.
     */
    @Autowired
    PostOfficeService postOfficeService;
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

    public Item arrival(long itemId,long postOfficeId) {
        var item = repository.findById(itemId);
        var postOffice = postOfficeService.getPostOffice(postOfficeId);
        logger.info("arrival item: {}", itemId);
        Item pitem = null;
        if (item.isPresent()&&postOffice.isPresent()) {
            pitem = item.get();
            pitem.setPostOffice(postOffice.get());
            repository.save(pitem);
            eventService.addEvent(pitem, EventType.EVENT_TYPE_ID_ARRIVED);
        }
        return pitem;
    }

    /**
     * Departure item.
     *
     * @param itemId the item id
     * @return the departured item
     */

    public Item departure(long itemId) {
        var item = repository.findById(itemId);
        logger.info("departure item: {}", itemId);
        Item pitem = null;
        if (item.isPresent()) {
            pitem = item.get();
            repository.save(pitem);
            eventService.addEvent(pitem, EventType.EVENT_TYPE_ID_DEPARTED);
        }
        return pitem;
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
