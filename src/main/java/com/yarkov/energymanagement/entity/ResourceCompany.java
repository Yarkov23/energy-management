package com.yarkov.energymanagement.entity;

import com.yarkov.energymanagement.entity.id.ResourceCompanyId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "resource_company")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResourceCompany {
    @EmbeddedId
    private ResourceCompanyId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @MapsId("companyId")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    @MapsId("resourceId")
    private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

}
