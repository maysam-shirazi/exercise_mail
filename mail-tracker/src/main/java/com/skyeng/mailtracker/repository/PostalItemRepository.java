package com.skyeng.mailtracker.repository;

import com.skyeng.mailtracker.model.postalitem.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalItemRepository extends JpaRepository<Item, Long> {
}
