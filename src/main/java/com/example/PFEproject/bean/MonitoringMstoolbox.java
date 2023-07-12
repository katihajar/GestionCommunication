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
public class MonitoringMstoolbox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 300)
    private String titre;
    private Date dateImplants;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="mstoolbox")
    private List<Implants> implantsList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="mstoolbox")
    private List<TransactionHandbid> transactionHandbidList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy="mstoolbox")
    private List<TransactionSmile> transactionSmileList;
    private Date dateAjout;
    @ManyToOne
    private User createurMonitoring;
}
