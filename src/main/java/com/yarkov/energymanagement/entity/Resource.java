package com.yarkov.energymanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "resources")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resource {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "resourceName")
    private String resourceName;

    @Column(name = "units")
    private String unit;

    @OneToMany(mappedBy = "resource")
    private Set<ResourceCompany> resourceCompanies;

    @OneToMany(mappedBy = "resource")
    private Set<Expense> resources;

}
