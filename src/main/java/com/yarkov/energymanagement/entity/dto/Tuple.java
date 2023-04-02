package com.yarkov.energymanagement.entity.dto;

import com.yarkov.energymanagement.entity.Resource;


public record Tuple(Integer month, Integer year, Resource resourceId) {
}
