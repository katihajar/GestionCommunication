package com.example.PFEproject.bean;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User owner;
}
