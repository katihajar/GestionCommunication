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

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
public class Probleme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 60)
    private String Topic;
    @NonNull
    private String statut;
    private Date dateAjout;
    @Size(min = 0, max = 1000)
    private String description ;
    @Size(min = 0, max = 1000)
    private String ananlyse ;
    @ManyToOne
    private Application application;
    @ManyToOne
    private User createurProbleme;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="probleme")
    private List<AvancementActionProbleme> avancementActionProbleme;

}
