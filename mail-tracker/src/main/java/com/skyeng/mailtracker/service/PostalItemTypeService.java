package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.repository.PostalItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostalItemTypeService {
    @Autowired
    PostalItemTypeRepository repository;
    public List<ItemType> getPostalItemTypes(){
        var list = repository.findAll();
        return list.size()>0?list:new ArrayList<ItemType>();
    }

}
