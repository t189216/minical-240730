package com.ll.minical_240730.domain.cal.service;

import com.ll.minical_240730.domain.cal.entity.Calendar;
import com.ll.minical_240730.domain.cal.repository.CalDetailRepository;
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
    private final CalDetailRepository calDetailRepository;

    // 달력 생성
    @Transactional
    public RsData<Calendar> create(String title, String color) {
        Calendar calendar = Calendar.builder()
                .title(title)
                .color(color)
                .build();
        calRepository.save(calendar);

        return RsData.of("200-1", "이벤트가 등록되었습니다!");
    }

    public List<Calendar> findAll() {
        return calRepository.findAll();
    }

    public Optional<Calendar> findById(Long id) {
        return calRepository.findById(id);
    }
}