package com.ll.minical_240730.domain.cal.controller;

import com.ll.minical_240730.domain.cal.entity.Calendar;
import com.ll.minical_240730.domain.cal.entity.CalendarDetail;
import com.ll.minical_240730.domain.cal.service.CalService;
import com.ll.minical_240730.domain.member.sevice.MemberService;
import com.ll.minical_240730.global.rq.Rq;
import com.ll.minical_240730.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cal")
@RequiredArgsConstructor
@Tag(name = "CalController", description = "달력 CRUD 컨트롤러")
public class CalController {
    private final CalService calService;
    private final MemberService memberService;
    private final Rq rq;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    @Operation(summary = "달력 폼")
    public String showCal(Model model){
        CreateForm createForm = new CreateForm();
        DetailForm detailForm = new DetailForm();
        createForm.getDetails().add(detailForm);

        model.addAttribute("createForm", createForm);
        model.addAttribute("detailForm", detailForm);
        return "domain/cal/create";
    }

    @Setter
    @Getter
    public static class CreateForm {
        @NotBlank
        private String title;
        @NotBlank
        private String color;
        private List<DetailForm> details = new ArrayList<>();
    }

    @Setter
    @Getter
    public static class DetailForm {
        private LocalDate startDate;
        private LocalTime startTime;
        private LocalDate endDate;
        private LocalTime endTime;
        private boolean isRepeating;
        private String repeatType;
        private Integer repeatInterval;
        private String location;
        private String memo;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    @Operation(summary = "달력 폼 처리")
    public String create(@Valid CreateForm createForm) {
        List<CalendarDetail> details = createForm.getDetails().stream()
                .map(detailForm -> CalendarDetail.builder()
                        .startDate(detailForm.getStartDate())
                        .startTime(detailForm.getStartTime())
                        .endDate(detailForm.getEndDate())
                        .endTime(detailForm.getEndTime())
                        .isRepeating(detailForm.isRepeating())
                        .repeatType(detailForm.getRepeatType())
                        .repeatInterval(detailForm.getRepeatInterval())
                        .location(detailForm.getLocation())
                        .memo(detailForm.getMemo())
                        .build())
                .toList();
        RsData<Calendar> createRs = calService.create(createForm.getTitle(), createForm.getColor(), details);

        return rq.redirectOrBack(createRs, "/calendar/list");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    @Operation(summary = "달력 목록")
    public String list(Model model) {
        List<Calendar> calendars = calService.findAll();
        model.addAttribute("calendars", calendars);
        return "domain/calendar/list";
    }
}
