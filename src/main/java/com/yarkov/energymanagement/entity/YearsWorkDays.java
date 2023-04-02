package com.yarkov.energymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearsWorkDays {

    private Integer year;
    private List<MonthWorkdays> monthWorkdays;

}
