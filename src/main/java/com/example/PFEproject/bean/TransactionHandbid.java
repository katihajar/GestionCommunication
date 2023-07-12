package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Entity
public class TransactionHandbid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @NonNull
    private double nombreRequet;
    @NonNull
    private double nombreRequetNontraite;
    @NonNull
    @Column(precision = 5, scale = 2)
    private BigDecimal pourcentageDemandeReussie;
    @NonNull
    @Column(precision = 5, scale = 2)
    private BigDecimal pourcentageDemandeNontraite;
    @ManyToOne
    private MonitoringMstoolbox mstoolbox;
}
