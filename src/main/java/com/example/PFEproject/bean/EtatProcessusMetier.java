package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
public class EtatProcessusMetier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ProcessusMetier processusMetier;
    private String statut;
    @ManyToOne
    private HealthChekPreprodProd healthChekPreprodProd;

}
