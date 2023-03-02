package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String nomApplication;
    @NonNull
    private String version;
    @ManyToOne
    private User responsable;
    @NonNull
    private String lot;
    @NonNull
    private String charteIncident;
    @NonNull
    private String disponibilite;
    @OneToMany(mappedBy="application")
    private List<PiloteApplication> piloteApplicationList;
    @OneToMany(mappedBy="application")
    private List<Incident> incidentList;
    @OneToMany(mappedBy="application")
    private List<Operation> operationList;
    @OneToMany(mappedBy="application")
    private List<DestinataireCommunication> destinataireCommunicationList;
    @OneToMany(mappedBy="application")
    private List<PointVersion> pointVersionList;
    @OneToMany(mappedBy="application")
    private List<HealthChekPreprodProdDetail> healthChekPreprodProdDetailList;
    @OneToMany(mappedBy="application")
    private List<ChangementPlanifier> changementPlanifierList;
}
