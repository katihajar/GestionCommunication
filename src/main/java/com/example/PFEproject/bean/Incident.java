package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private String numeroIncident;
    private Date dateDebut;
    private Date dateFin;
    @NonNull
    @Size(min = 3, max = 1000)
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
    @ManyToOne
    private User createurIncident;
    @ManyToOne
    private Application application;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="incident")
    private List<PlanAction> planActionList;

}
