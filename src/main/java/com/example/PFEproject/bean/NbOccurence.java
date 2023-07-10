package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Entity
public class NbOccurence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2, max = 100)
    private String traitement;
    @Size(min = 1, max = 10)
    private String statut;
    private Date date;
    private double nombreOcurrence;
    @ManyToOne
    private NuitApplicative nuitApplicative;
}
