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
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 60)
    private String titre;
    @NonNull
    private String statut;
    @Size(min = 0, max = 60)
    private String numero;
    @NonNull
    @Size(min = 3, max = 1000)
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateFin;
    @ManyToOne
    private Application application;
    @ManyToOne
    private User createurOperation;
    @NonNull
    private Date dateAjout;
    @NonNull
    @Size(min = 3, max = 1000)
    private String impactMetier;
}
