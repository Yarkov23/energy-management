package com.yarkov.energymanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tariffs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tariff {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Long price;

    @Column(name = "tariff_condition")
    private String tariffCondition;

    @OneToMany(mappedBy = "tariff")
    @ToString.Exclude
    private Set<ResourceCompany> resourceCompanies;

}
