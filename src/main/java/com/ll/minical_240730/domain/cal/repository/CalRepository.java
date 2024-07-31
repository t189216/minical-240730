package com.ll.minical_240730.domain.cal.repository;

import com.ll.minical_240730.domain.cal.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalRepository extends JpaRepository<Calendar, Long> {
}