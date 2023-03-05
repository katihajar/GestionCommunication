package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String numeroIncident;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDebut;
    @JsonFormat(pattern="dd-MM-yyyy")
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
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date prochaineCommunication;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="incident")
    private List<PlanAction> planActions;
    @ManyToOne
    private User createurIncident;
    @ManyToOne
    private Application application;


}
