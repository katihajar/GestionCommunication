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
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDebut;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateFin;
    @NonNull
    private String statut;
    @NonNull
    private String feu;
    @NonNull
    private boolean impactClient;
    @NonNull
    private String impactMetier;
    @NonNull
    @Size(min = 3, max = 200)
    private String cause;
    @NonNull
    @Size(min = 3, max = 100)
    private String planAction;
    @NonNull
    @Size(min = 3, max = 600)
    private String information;
    @NonNull
    @Size(min = 3, max = 600)
    private String problemeTechnique;

    @ManyToOne
    HealthChekPreprodProd healthChekPreprodProd;

}
