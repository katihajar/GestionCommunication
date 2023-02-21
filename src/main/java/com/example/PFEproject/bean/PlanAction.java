package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PlanAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private double numero;
    @NonNull
    private String statut;
    @NonNull
    @Size(min = 3, max = 100)
    private String description;
    @ManyToOne
    private Incident incident;
}
