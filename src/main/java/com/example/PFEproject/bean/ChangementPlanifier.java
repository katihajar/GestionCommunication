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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ChangementPlanifier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 100)
    private String titre;
    @NonNull
    @Size(min = 3, max = 1000)
    private String detail;
    @NonNull
    @Size(min = 3, max = 100)
    private String statut;
    @NonNull
    @Size(min = 0, max = 100)
    private String version;
    @NonNull
    @Size(min = 3, max = 1000)
    private String impactMetier;
    private Date dateDebut;
    private Date dateFin;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "changementPlanifier")
    private List<ContenuChangement> contenuChangementList;
    @ManyToOne
    private Application application;
    @ManyToOne
    private User createurChangement;
    @NonNull
    private Date dateAjout;
}
