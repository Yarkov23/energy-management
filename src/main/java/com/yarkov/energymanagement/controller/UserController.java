package com.yarkov.energymanagement.controller;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Expense;
import com.yarkov.energymanagement.entity.User;
import com.yarkov.energymanagement.service.CompanyService;
import com.yarkov.energymanagement.service.ExpenseService;
import com.yarkov.energymanagement.service.ResourceService;
import com.yarkov.energymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
public class UserController {

    private UserService userService;

    private CompanyService companyService;

    private ExpenseService expenseService;

    private ResourceService resourceService;

    @Autowired
    public UserController(UserService userService, CompanyService companyService, ExpenseService expenseService, ResourceService resourceService) {
        this.userService = userService;
        this.companyService = companyService;
        this.expenseService = expenseService;
        this.resourceService = resourceService;
    }

    @GetMapping("/expenses")
    public String getCompanyExpenseData(Model model, Principal principal) {

        String email = principal.getName();

        User user = userService.findByEmail(email);
        Company company = user.getCompany();

        List<Expense> expenses = expenseService.findByCompany(company);

        model.addAttribute("expenses", expenses);
        model.addAttribute("resources", resourceService.getAll());

        return "company-expenses";
    }


    @GetMapping("/average-consumption")
    public String showChartForm(Model model, Principal principal) {
        List<Expense> expenses = getExpensesForCurrentUser(principal);

        List<Integer> years = expenseService.findDistinctExpenseYears();
        model.addAttribute("years", years);
        model.addAttribute("expenses", expenses);
        return "average-consumption";
    }

    @PostMapping("/average-consumption")
    public String showChart(Model model, @RequestParam("year") Integer year, Principal principal) {
        List<Expense> expensesList = expenseService.findByExpensesYearOrderByExpensesMonthAsc(year);
        List<Double> useAmountList = new ArrayList<>();
        List<String> monthLabelList = new ArrayList<>();
        for (Expense expenses : expensesList) {
            useAmountList.add(expenses.getUseAmount());
            monthLabelList.add(expenses.getExpensesMonth().toString());
        }

        List<Expense> expenses = getExpensesForCurrentUser(principal);
        List<Integer> years = expenseService.findDistinctExpenseYears();

        model.addAttribute("years", years);
        model.addAttribute("expenses", expenses);
        model.addAttribute("year", year);
        model.addAttribute("useAmountList", useAmountList);
        model.addAttribute("monthLabelList", monthLabelList);
        return "average-consumption";
    }

    public List<Expense> getExpensesForCurrentUser(Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        Company company = user.getCompany();
        return expenseService.findByCompany(company);
    }

}
