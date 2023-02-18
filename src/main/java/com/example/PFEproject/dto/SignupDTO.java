package com.example.PFEproject.dto;

import com.example.PFEproject.bean.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {

    private String username;
    private String nom;
    private String prenom;
    private String password;
    private Role role;
}
