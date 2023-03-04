package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class HealthChekPreprodProd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 100)
    private String titre;
    @NonNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateAjout;
    @NonNull
    private String type;

    @ManyToOne
    private EtatProcessusMetier etatProcessusMetier;

    @OneToMany(mappedBy = "healthChekPreprodProd")
    List<HealthChekPreprodProdDetail> healthChekPreprodProdDetailList;

    @ManyToOne
    private User createurHealthChekPreprodProd;


}