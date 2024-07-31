package com.ll.minical_240730.domain.cal.service;

import com.ll.minical_240730.domain.cal.entity.Calendar;
import com.ll.minical_240730.domain.cal.entity.CalendarDetail;
import com.ll.minical_240730.domain.cal.repository.CalRepository;
import com.ll.minical_240730.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalService {
    private final CalRepository calRepository;

    @Transactional
    public RsData<Calendar> create(String title, String color, List<CalendarDetail> details) {
        Calendar calendar = Calendar.builder()
                .title(title)
                .color(color)
                .calendars(details)
                .build();
        for (CalendarDetail detail : details) {
            detail.setCalendar(calendar);
        }

        calRepository.save(calendar);
        return RsData.of("200", "등록되었습니다.");
    }

    public List<Calendar> findAll() {
        return calRepository.findAll();
    }

    public Optional<Calendar> findById(Long id) {
        return calRepository.findById(id);
    }
}