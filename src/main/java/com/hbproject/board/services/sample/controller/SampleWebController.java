package com.hbproject.board.services.sample.controller;

import com.hbproject.board.services.sample.dto.Sample;
import com.hbproject.board.services.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SampleWebController {

    private final SampleService sampleService;

    @RequestMapping("/sample")
    public String sample(Model model) {
        List<Sample> allList = sampleService.getAllList();
        model.addAttribute("list", allList);
        return "index";
    }
}
