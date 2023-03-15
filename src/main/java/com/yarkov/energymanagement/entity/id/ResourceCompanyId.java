package com.yarkov.energymanagement.entity.id;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@EqualsAndHashCode
public class ResourceCompanyId implements Serializable {
    private Long companyId;

    private Long resourceId;

    private Integer expenseMonth;

    private Integer expenseYear;

}
