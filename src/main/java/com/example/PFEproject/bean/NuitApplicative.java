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
public class NuitApplicative {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 0, max = 60)
    private String titre;
    @Size(min = 0, max = 60)
    private String statut;
    private Date date;
    @Size(min = 0, max = 60)
    private String typeChainesReferentiels;
    @Size(min = 0, max = 1000)
    private String descChainesReferentiels;
    @Size(min = 0, max = 60)
    private String typeChainesTransactions;
    @Size(min = 0, max = 1000)
    private String descChainesTransactions;
    @Size(min = 0, max = 60)
    private String typeChainesPCOM ;
    @Size(min = 0, max = 1000)
    private String descChainesPCOM;
    @Size(min = 0, max = 60)
    private String typeChainesGRanalytics;
    @Size(min = 0, max = 1000)
    private String descChainesGRanalytics;
    @Size(min = 0, max = 60)
    private String typeChainesFacturation ;
    @Size(min = 0, max = 1000)
    private String descChainesFacturation;
    @Size(min = 0, max = 60)
    private String typeFocusEmail;
    @Size(min = 0, max = 1000)
    private String descFocusEmail;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="nuitApplicative")
    private List<NbOccurence> nbOccurenceList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="nuitApplicative")
    private List<SuiviVolumetrie> suiviVolumetrieList;
    @ManyToOne
    private User createurNuitApplicative;
}
