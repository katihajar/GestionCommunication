package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class EtatProcessusMetier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 100)
    private String titre;
    @NonNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateAjout;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "etatProcessusMetier")
    private List<EtatProcessusMetierDetail> etatProcessusMetierDetailList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
     @OneToMany(mappedBy = "etatProcessusMetier")
    private List<HealthChekPreprodProd> healthChekPreprodProdList;




}
