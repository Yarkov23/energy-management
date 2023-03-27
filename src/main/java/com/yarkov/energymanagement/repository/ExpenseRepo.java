package com.yarkov.energymanagement.repository;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    List<Expense> findByCompany(Company company);

    @Query("SELECT DISTINCT e.expensesYear FROM Expense e")
    List<Integer> findDistinctExpensesYears();;

    List<Expense> findByExpensesYearOrderByExpensesMonthAsc(Integer expensesYear);

}
