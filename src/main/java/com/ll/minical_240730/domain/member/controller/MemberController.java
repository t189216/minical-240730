package com.ll.minical_240730.domain.member.controller;

import com.ll.minical_240730.domain.member.entity.Member;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "MemberController", description = "회원 CRUD 컨트롤러")
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/join")
    @Operation(summary = "가입 폼")
    public String showJoin() {
        return "domain/member/join";
    }

    @Setter
    @Getter
    public static class JoinForm {
        @NotBlank
        private String username;

        @NotBlank
        private String password;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    @Operation(summary = "가입 폼 처리")
    public String join(@Valid JoinForm joinForm) {
        RsData<Member> joinRs = memberService.join(joinForm.getUsername(), joinForm.getPassword());

        return rq.redirectOrBack(joinRs, "/member/login");
    }

    @GetMapping("/login")
    @Operation(summary = "로그인 폼")
    public String showLogin() {
        return "domain/member/login";
    }
}