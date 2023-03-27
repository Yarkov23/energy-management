package com.yarkov.energymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tariff")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tariff {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Long price;

    @Column(name = "tariff_condition")
    private String tariffCondition;

    @OneToMany(mappedBy = "tariff")
    private Set<ResourceCompany> resourceCompanies;

}
