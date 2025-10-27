package com.lost_and_found.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/items/report")
    public String reportItem() {
        return "items/report";
    }

    @GetMapping("/items/search")
    public String searchItems() {
        return "items/search";
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }
}