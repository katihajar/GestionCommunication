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
public class MonitoringOptirenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateAjout;
    @ManyToOne
    private User createurMonitoring;
    @NonNull
    @Size(min = 3, max = 60)
    private String titre;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="optirenta")
    private List<FluxOptirenta> fluxOptirentaList;
}
