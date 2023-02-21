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
    @OneToMany(mappedBy="application")
    private List<ResponsableApplication> responsableApplications;
    @OneToMany(mappedBy="application")
    private List<PiloteApplication> piloteApplications;

    @OneToMany(mappedBy="application")
    private List<Incident> incidentList;
    @OneToMany(mappedBy="application")
    private List<Changement> changementList;

    @OneToMany(mappedBy="application")
    private List<DestinataireCommunication> destinataireCommunicationList;

}
