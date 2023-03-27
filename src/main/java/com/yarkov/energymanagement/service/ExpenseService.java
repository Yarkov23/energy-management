package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Expense;
import com.yarkov.energymanagement.repository.ExpenseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService extends BaseService<Expense, Long, ExpenseRepo> {
    public ExpenseService(ExpenseRepo repository) {
        super(repository);
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

}
