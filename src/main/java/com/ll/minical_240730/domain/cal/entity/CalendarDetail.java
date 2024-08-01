package com.ll.minical_240730.domain.cal.entity;

import com.ll.minical_240730.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class CalendarDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "calendar_dt_id")
    private Long id;

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    private boolean isRepeating; // 반복 여부
    private String repeatType; // 반복 유형: 매일, 매주, 매월, 매년
    private Integer repeatInterval; // 반복 간격 (예: 2일마다, 3주마다 등)

    private String location;
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cal_id")
    private Calendar calendar;
}