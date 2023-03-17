package com.yarkov.energymanagement.controller;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private CompanyService companyService;

    @Autowired
    public HomeController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

}
