package com.ll.minical_240730.domain.cal.repository;

import com.ll.minical_240730.domain.cal.entity.CalendarDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalDetailRepository extends JpaRepository<CalendarDetail, Long> {
}