package com.yarkov.energymanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "resources")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Resource {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "units")
    private String unit;

    @ToString.Exclude
    @OneToMany(mappedBy = "resource")
    private Set<ResourceCompany> resourceCompanies;

    @ToString.Exclude
    @OneToMany(mappedBy = "resource")
    private Set<Expense> resources;


}
