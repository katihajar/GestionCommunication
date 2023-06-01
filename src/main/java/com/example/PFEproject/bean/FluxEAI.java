package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
public class FluxEAI {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String process;
    @NonNull
    private String subProcess;
    @NonNull
    private String etat;
    @NonNull
    private String commentaire;
    @ManyToOne
    private HealthCheckFlamingo healthCheckFlamingo;
}
