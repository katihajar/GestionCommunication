package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Entity
public class SuiviVolumetrie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2, max = 100)
    private String typeAlerte;
    @Size(min = 1, max = 100)
    private String nbMinimun;
    private double nbActuel;
    @Size(min = 1, max = 10)
    private String statut;
    @ManyToOne
    private NuitApplicative nuitApplicative;
}
