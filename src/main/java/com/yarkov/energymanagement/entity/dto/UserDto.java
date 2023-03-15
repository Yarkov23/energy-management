package com.yarkov.energymanagement.entity.dto;

import com.yarkov.energymanagement.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String companyName;
    private List<Role> roles;

}
