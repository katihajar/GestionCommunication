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
public class HealthChekPreprodProd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 200)
    private String titre;
    @NonNull
    private Date dateAjout;
    @NonNull
    private String type;


    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "healthChekPreprodProd")
    private List<EtatProcessusMetier> etatProcessusMetierList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "healthChekPreprodProd")
    List<HealthChekPreprodProdDetail> healthChekPreprodProdDetailList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "healthChekPreprodProd")
    List<StatutApplication> statutApplicationList;

    @ManyToOne
    private User createurHealthChekPreprodProd;


}
