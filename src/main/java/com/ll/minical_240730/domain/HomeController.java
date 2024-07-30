package com.ll.minical_240730.domain;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Tag(name = "HomeController", description = "홈 컨트롤러")
public class HomeController {
    @GetMapping("/")
    public String showMain() {
        return "domain/home/main";
    }
}