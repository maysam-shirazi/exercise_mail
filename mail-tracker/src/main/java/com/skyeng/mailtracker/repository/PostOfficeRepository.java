package com.skyeng.mailtracker.repository;

import com.skyeng.mailtracker.model.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
}
