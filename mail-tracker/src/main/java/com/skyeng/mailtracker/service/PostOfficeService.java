package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.repository.PostOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostOfficeService {
    @Autowired
    PostOfficeRepository repository;
    public List<PostOffice> getPostOffices(){
        var list = repository.findAll();
        return list.size()>0?list:new ArrayList<PostOffice>();
    }

    public Optional<PostOffice> getPostOffice(long postOfficeId) {
        return repository.findById(postOfficeId);

    }
}
