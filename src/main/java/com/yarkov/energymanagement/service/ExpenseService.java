package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Expense;
import com.yarkov.energymanagement.entity.ResourceCompany;
import com.yarkov.energymanagement.entity.Tariff;
import com.yarkov.energymanagement.entity.dto.ExpenseSummary;
import com.yarkov.energymanagement.entity.dto.Tuple;
import com.yarkov.energymanagement.exception.NotFoundException;
import com.yarkov.energymanagement.repository.ExpenseRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService extends BaseService<Expense, Long, ExpenseRepo> {

    private final ResourceCompanyService resourceCompanyService;
    private final TariffService tariffService;

    public ExpenseService(ExpenseRepo repository, ResourceCompanyService resourceCompanyService, TariffService tariffService) {
        super(repository);
        this.resourceCompanyService = resourceCompanyService;
        this.tariffService = tariffService;
    }

    public List<Expense> findByCompany(Company company) {
        return repository.findByCompany(company);
    }

    public List<Expense> findByExpensesYearOrderByExpensesMonthAsc(Integer expensesYear) {
        return repository.findByExpensesYearOrderByExpensesMonthAsc(expensesYear);
    }

    public List<Integer> findDistinctExpenseYears() {
        return repository.findDistinctExpensesYears();
    }

    public List<ExpenseSummary> calculateExpensesByMonthAndResource() {
        List<ExpenseSummary> expenses = new ArrayList<>();
        Map<Tuple, Double> expenseMap = new HashMap<>();

        repository.findAll().forEach(expense -> {
            var resourceId = expense.getResource();
            var companyId = expense.getCompany();
            var useAmount = expense.getUseAmount();
            var month = expense.getExpensesMonth();
            var year = expense.getExpensesYear();

            ResourceCompany resourceCompany = resourceCompanyService.findByCompanyIdAndResourceId(companyId, resourceId);
            Tariff tariff = null;
            try {
                tariff = tariffService.findById(resourceCompany.getTariff().getId());
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }

            Tuple tuple = new Tuple(month, year, resourceId);
            if (expenseMap.containsKey(tuple)) {
                Double currentExpenses = expenseMap.get(tuple);
                expenseMap.put(tuple, currentExpenses + (useAmount * tariff.getPrice()));
            } else {
                expenseMap.put(tuple, useAmount * tariff.getPrice());
            }
        });

        for (Map.Entry<Tuple, Double> entry : expenseMap.entrySet()) {
            Tuple tuple = entry.getKey();
            Double totalExpenses = entry.getValue();
            ExpenseSummary summary = new ExpenseSummary(tuple.month(), tuple.year(), tuple.resourceId(), totalExpenses);
            expenses.add(summary);
        }

        return expenses;
    }

    public Map<Integer, Double> calculateTotalExpensesByYear(Company company) {

        Map<Integer, Double> resultMap = new HashMap<>();

        var expenseSummaryByYear = repository.findExpenseSummaryByYearForCompany(company);

        var resourceCompany = resourceCompanyService.findFirstByCompany(company);

        Long price = resourceCompany.getTariff().getPrice();

        for (Object[] object : expenseSummaryByYear) {
            resultMap.put((Integer) object[0], (Double) object[1] * price);
        }

        return resultMap;
    }

    public Double getMax() {
        return repository.findMax();
    }

    public Double getMin() {
        return repository.findMin();
    }

}
