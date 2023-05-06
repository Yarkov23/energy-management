package com.yarkov.energymanagement.controller;

import com.yarkov.energymanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private CompanyService companyService;

    @Autowired
    public HomeController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public String home() {
        return "main";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
