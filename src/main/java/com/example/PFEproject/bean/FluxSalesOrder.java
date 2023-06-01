package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Entity
public class FluxSalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String systeme;
    @NonNull
    private String etat;
    @NonNull
    private String commentaire;
    @ManyToOne
    private HealthCheckFlamingo healthCheckFlamingo;
}
