package com.yarkov.energymanagement.controller;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Expense;
import com.yarkov.energymanagement.entity.User;
import com.yarkov.energymanagement.service.ExpenseService;
import com.yarkov.energymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final UserService userService;
    private final ExpenseService expenseService;

    @Autowired
    public UserController(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public String showExpenseForm(Model model, Principal principal) {
        var expenses = expenseService.getExpensesForCurrentUser(principal);
        var years = expenseService.findDistinctExpenseYears();
        var expenseSummaries = expenseService.calculateExpensesByMonthAndResource();

        model.addAttribute("expenseSummaries", expenseSummaries);
        model.addAttribute("years", years);
        model.addAttribute("expenses", expenses);
        return "company-expenses";
    }

    @PostMapping("/expenses")
    public String showExpenseChart(Model model, @RequestParam("year") Integer year, Principal principal) {
        var expensesList = expenseService.findByExpensesYearOrderByExpensesMonthAsc(year);
        var useAmountList = new ArrayList<>();
        var monthLabelList = new ArrayList<>();
        var expenseSummaries = expenseService.calculateExpensesByMonthAndResource();

        for (Expense expenses : expensesList) {
            useAmountList.add(expenses.getUseAmount());
            monthLabelList.add(expenses.getExpensesMonth().toString());
        }

        List<Expense> expenses = expenseService.getExpensesForCurrentUser(principal);
        List<Integer> years = expenseService.findDistinctExpenseYears();

        model.addAttribute("years", years);
        model.addAttribute("expenses", expenses);
        model.addAttribute("year", year);
        model.addAttribute("expenseSummaries", expenseSummaries);
        model.addAttribute("useAmountList", useAmountList);
        model.addAttribute("monthLabelList", monthLabelList);
        return "company-expenses";
    }

    @GetMapping("/energy-indexes")
    public String showEnergyIndexes(Model model, Principal principal) {
        var expenses = expenseService.getExpensesForCurrentUser(principal);
        var monthWorkdays = expenseService.getMonthWorkDays(expenses);
        var years = expenseService.findDistinctExpenseYears();

        model.addAttribute("monthWorkDays", monthWorkdays);
        model.addAttribute("expenses", expenses);
        model.addAttribute("years", years);

        return "energy-indexes";
    }

    @GetMapping("/summary-charts")
    public String showExpenseCharts(Model model, Principal principal) {

        String name = principal.getName();
        User user = userService.findByEmail(name);
        Company company = user.getCompany();

        var totalByYear = expenseService.calculateTotalExpensesByYear(company);
        var totalUseAmount = expenseService.calculateTotalUseAmountByYear(company);

        model.addAttribute("totalUseAmount", totalUseAmount);
        model.addAttribute("totalByYear", totalByYear);

        return "summary-charts";
    }

    @GetMapping("/resource-summary")
    public String showResourceSummaryCharts(Model model, Principal principal) {
        List<Expense> expensesForCurrentUser = expenseService.getExpensesForCurrentUser(principal);
        Map<String, Double> expenseSummaryByResource = expenseService.getExpenseSummaryByResource(expensesForCurrentUser);
        model.addAttribute("expenseSummary", expenseSummaryByResource);
        return "resource-summary";
    }
}
