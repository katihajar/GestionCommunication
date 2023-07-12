package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Entity
public class FluxOptirenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 60)
    private String nomFlux;
    @NonNull
    @Size(min = 2, max = 60)
    private String etat;
    @Size(min = 2, max = 1000)
    private String commentaire;
    @ManyToOne
    private MonitoringOptirenta optirenta;
}
