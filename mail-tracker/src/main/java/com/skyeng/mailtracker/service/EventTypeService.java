package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventTypeService {
    @Autowired
    EventTypeRepository repository;

    public List<EventType> getEventTypes(){
        var list = repository.findAll();
        return list.size()>0?list:new ArrayList<EventType>();
    }
    public Optional<EventType> getEventType(Long id){
        return repository.findById(id);
    }
}
