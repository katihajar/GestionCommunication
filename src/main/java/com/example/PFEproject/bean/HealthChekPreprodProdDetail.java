package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class HealthChekPreprodProdDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Application application;
    private Date dateDebut;
    private Date dateFin;
    @NonNull
    private String statut;
    @NonNull
    private String feu;
    @NonNull
    @Size(min = 3, max = 500)
    private String impactClient;
    @NonNull
    @Size(min = 3, max = 500)
    private String processus;
    @NonNull
    @Size(min = 3, max = 500)
    private String impactMetier;
    @NonNull
    @Size(min = 3, max = 500)
    private String cause;
    @NonNull
    @Size(min = 3, max = 1000)
    private String planAction;
    @NonNull
    @Size(min = 3, max = 1000)
    private String information;
    @NonNull
    @Size(min = 3, max = 1000)
    private String problemeTechnique;
    @ManyToOne
    private HealthChekPreprodProd healthChekPreprodProd;

}
