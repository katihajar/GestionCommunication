package com.example.PFEproject.dto;

import com.example.PFEproject.bean.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDTO {
    private User user;
    private String accessToken;
    private String refreshToken;
}
