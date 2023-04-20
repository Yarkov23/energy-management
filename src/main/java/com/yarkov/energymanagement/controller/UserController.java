package com.yarkov.energymanagement.controller;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Expense;
import com.yarkov.energymanagement.entity.User;
import com.yarkov.energymanagement.service.ChatGPT;
import com.yarkov.energymanagement.service.CompanyService;
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
    private final CompanyService companyService;

    @Autowired
    public UserController(UserService userService, ExpenseService expenseService, CompanyService companyService) {
        this.userService = userService;
        this.expenseService = expenseService;
        this.companyService = companyService;
    }

    @GetMapping("/expenses")
    public String showExpenseForm(Model model, Principal principal) {
        var expenses = expenseService.getExpensesForCurrentUser(principal);
        var company = companyService.getCompanyByUser(principal);
        var years = expenseService.findDistinctExpensesYearsByCompany(company);
        var expenseSummaries = expenseService.calculateExpensesByMonthAndResource(expenses);

        model.addAttribute("expenseSummaries", expenseSummaries);
        model.addAttribute("years", years);
        model.addAttribute("expenses", expenses);
        return "company-expenses";
    }

    @PostMapping("/expenses")
    public String showExpenseChart(Model model, @RequestParam("year") Integer year, Principal principal) {
        var company = companyService.getCompanyByUser(principal);
        var expensesForUser = expenseService.getExpensesForCurrentUser(principal);
        var expensesYearOrderByExpensesMonthAsc = expenseService.findByCompanyAndExpensesYearOrderByExpensesMonthAsc(company, year);
        var useAmountList = new ArrayList<>();
        var monthLabelList = new ArrayList<>();
        var expenseSummaries = expenseService.calculateExpensesByMonthAndResource(expensesForUser);

        for (Expense expenses : expensesYearOrderByExpensesMonthAsc) {
            useAmountList.add(expenses.getUseAmount());
            monthLabelList.add(expenses.getExpensesMonth().toString());
        }

        List<Integer> years = expenseService.findDistinctExpensesYearsByCompany(company);

        model.addAttribute("years", years);
        model.addAttribute("expenses", expensesForUser);
        model.addAttribute("year", year);
        model.addAttribute("expenseSummaries", expenseSummaries);
        model.addAttribute("useAmountList", useAmountList);
        model.addAttribute("monthLabelList", monthLabelList);
        return "company-expenses";
    }

    @GetMapping("/energy-indexes")
    public String showEnergyIndexes(Model model, Principal principal) {
        var expenses = expenseService.getExpensesForCurrentUser(principal);
        var company = companyService.getCompanyByUser(principal);
        var monthWorkdays = expenseService.getMonthWorkDays(expenses, company);
        var years = expenseService.findDistinctExpensesYearsByCompany(company);

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

    @GetMapping("/energy-saving-measures")
    public String showEnergySavingMeasures(Model model, Principal principal) throws Exception {
        var company = userService.findByEmail(principal.getName()).getCompany();
        var measuresForCompany = expenseService.getAllEnergySavingMeasuresForCompany(company);

        var testMeasures = expenseService.getExpensesForCurrentUser(principal);

        String answer = ChatGPT.chatGPT("Describe possible energy saving measure");
        model.addAttribute("answer", answer);

        model.addAttribute("measuresForCompany", measuresForCompany);
        return "energy-saving-measures";
    }

}
