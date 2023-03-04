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
public class LivraisonCARM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 60)
    private String statutMEI;
    @NonNull
    private double NombreTicket;
    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateMEI;
    @ManyToOne
    private PointVersion pointVersion;



}