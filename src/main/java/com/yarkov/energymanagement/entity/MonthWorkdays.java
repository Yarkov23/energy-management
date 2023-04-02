package com.yarkov.energymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthWorkdays {

    private Integer workDays;
    private Integer year;
    private Integer month;
    private Double useAmount;
    private Double avg;
    private Double max;
    private Double min;
    private Double unevennessCoefficient;

}
