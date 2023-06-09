package com.yarkov.energymanagement.repository;

import com.yarkov.energymanagement.entity.Company;
import com.yarkov.energymanagement.entity.Expense;
import com.yarkov.energymanagement.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    List<Expense> findByCompany(Company company);

    @Query("SELECT DISTINCT e.expensesYear FROM Expense e WHERE e.company = :company")
    List<Integer> findDistinctExpensesYearsByCompany(@Param("company") Company company);

    List<Expense> findByCompanyAndExpensesYearOrderByExpensesMonthAsc(Company company, Integer expensesYear);

    @Query("SELECT MAX(e.useAmount) FROM Expense e WHERE e.company = :company")
    Double findMax(@Param("company") Company company);

    @Query("SELECT MIN(e.useAmount) FROM Expense e WHERE e.company = :company")
    Double findMin(@Param("company") Company company);

    @Query("SELECT e.expensesYear, SUM(e.useAmount) " +
            "FROM Expense e " +
            "WHERE e.company = :company " +
            "GROUP BY e.expensesYear")
    List<Object[]> findExpenseSummaryByYearForCompany(Company company);

    List<Expense> findByCompanyAndResourceOrderByExpensesYearAscExpensesMonthAsc(Company company, Resource resource);

}
