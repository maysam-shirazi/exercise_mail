package com.skyeng.mailtracker.repository;

import com.skyeng.mailtracker.model.postalitem.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
