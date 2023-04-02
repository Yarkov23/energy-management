package com.yarkov.energymanagement.entity.dto;

import com.yarkov.energymanagement.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseSummary {

    private Integer month;
    private Integer year;
    private Resource resourceId;
    private Double totalExpenses;

}
