package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 60)
    private String titreIncident;
    @NonNull

    private String statut;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateFin;
    @NonNull
    @Size(min = 3, max = 100)
    private String description;
    @NonNull
    @Size(min = 3, max = 1000)
    private String situationActuelle;
    @Size(min = 0, max = 1000)
    private String impact;
    @NonNull
    @Size(min = 3, max = 1000)
    private String causePrincipale;
    @Size(min = 0, max = 1000)
    private String solutionContournement;
    @NonNull
    private Date prochaineCommunication;
    @NonNull
    @OneToMany(mappedBy="incident")
    private List<PlanAction> planActions;
    @NonNull
    @ManyToOne
    private User createurIncident;
    @NonNull
    @ManyToOne
    private Application application;


}
