package com.example.PFEproject.dto;

import com.example.PFEproject.bean.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupDTO {
    @NotBlank
    @Size(min = 3, max = 30)
    private String username;
    @NotBlank
    @Size(min = 3, max = 20)
    private String nom;
    @NotBlank
    @Size(min = 3, max = 20)
    private String prenom;
    @NotBlank
    @Size(min = 6, max = 60)
    private String password;
    private Role role;
}
