package com.yarkov.energymanagement.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergySavingMeasure {

    private String resourceName;
    private String measureName;
    private String expectedEffect;
    private Double potentialSavings;

}
