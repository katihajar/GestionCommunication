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
@NoArgsConstructor(force = true)
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
    @Size( max = 1000)
    private String impact;
    @NonNull
    @Size(min = 3, max = 1000)
    private String causePrincipale;
    @Size( max = 1000)
    private String solutionContournement;
    @Size(min = 0, max = 1000)
    private String type;
    @Size(min = 0, max = 1000)
    private String detailResolution;
    @Size(min = 0, max = 1000)
    private String actionPrise;
    @NonNull
    private Date prochaineCommunication;
    @NonNull
    private Date dateAjout;
    @ManyToOne
    private User createurIncident;
    @ManyToOne
    private Application application;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="incident")
    private List<PlanAction> planActionList;

}
