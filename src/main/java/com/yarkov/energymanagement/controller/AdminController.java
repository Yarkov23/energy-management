package com.yarkov.energymanagement.controller;

import com.yarkov.energymanagement.entity.Role;
import com.yarkov.energymanagement.entity.User;
import com.yarkov.energymanagement.entity.dto.UserDto;
import com.yarkov.energymanagement.exception.NotFoundException;
import com.yarkov.energymanagement.service.CompanyService;
import com.yarkov.energymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final CompanyService companyService;

    @Autowired
    public AdminController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping
    public String showAdminPanel(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("roles", List.of(new Role("ROLE_USER"),
                new Role("ROLE_ADMIN")));
        model.addAttribute("companyList", companyService.getAll());
        model.addAttribute("user", new UserDto());
        return "add-user-form";
    }

    @PostMapping("/create")
    public String createUser(UserDto userDto) {

        String email = userDto.getEmail();

        if (userService.findByEmail(email) != null) {
            return "redirect:/admin";
        }

        User user = new User(userDto.getEmail(),
                "{noop}" + userDto.getPassword(),
                userDto.getRoles(),
                companyService.findByName(userDto.getCompanyName()));

        userService.save(user);

        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        try {
            userService.delete(userService.findById(id));
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin";
    }


    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        try {
            var user = userService.findById(id);
            model.addAttribute("user", user);
            model.addAttribute("companyList", companyService.getAll());
            model.addAttribute("allRoles", List.of(new Role("ROLE_USER"),
                    new Role("ROLE_ADMIN")));
            return "update-user-form";
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        String password = "{noop}" + user.getPassword();
        user.setPassword(password);

        userService.save(user);
        return "redirect:/admin";
    }

}
