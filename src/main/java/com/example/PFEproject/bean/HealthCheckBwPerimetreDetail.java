package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class HealthCheckBwPerimetreDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String statusNightTreatment;
    private String statusDataIntegrity;
    private String comment;
    private String actionPlan;
    private boolean feedBack;
    private double incidentInProgress;
    private double incidentCompleted;
    @ManyToOne
    private Perimetre perimetre;
    @ManyToOne
    private HealthCheckBwPerimetre healthCheckBwPerimetre;

}
