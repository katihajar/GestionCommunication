package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Entity
public class HealthCheckFlamingo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String remarque;
    @NonNull
    private String titre;
    @NonNull
    private Date dateAjout;
    @NonNull
    private Date dateFlux;
    @ManyToOne
    private User createurHealthCheckFlamingo;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "healthCheckFlamingo")
    private List<FluxEAI> fluxEAIList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "healthCheckFlamingo")
    private List<FluxSalesOrder> fluxSalesOrderList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "healthCheckFlamingo")
    private List<FluxSapEurope> fluxSapEuropeList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "healthCheckFlamingo")
    private List<FluxSapHarmonie> fluxSapHarmonies;
}
