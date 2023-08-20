package com.skyeng.mailtracker.repository;

import com.skyeng.mailtracker.model.postalitem.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByPostalItemId(Long itemId);
    List<Event> findByPostalItemIdOrderByIdDesc(Long itemId);
}
