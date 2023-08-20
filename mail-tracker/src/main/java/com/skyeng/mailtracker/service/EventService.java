package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.postalitem.Event;
import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository repository;
    Logger logger = LoggerFactory.getLogger(EventService.class);

    public List<Event> getEvents(Long postalItemId){
        var list = repository.findByPostalItemId(postalItemId);
        return list.size()>0?list:new ArrayList<Event>();
    }

    public Event getLatestEvent(Long postalItemId){
        var list = repository.findByPostalItemIdOrderByIdDesc(postalItemId);
        logger.info("Latest event: {}",list);
        return list.size()>0?list.get(0):null;
    }


    public Event addEvent(Item item, long eventTypeId) {
        var eventType = EventType.builder().id(eventTypeId).build();
        var event = Event.builder()
                .eventTime(new Date())
                .postalItem(item)
                .type(eventType)
                .build();
        logger.info("new event: {}",event);
        event = repository.save(event);
        getLatestEvent(item.getId());
        return event;
    }
}
