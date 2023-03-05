package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class HealthCheckBwPerimetre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    @NonNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateAjout;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "healthCheckBwPerimetre")
    private List<HealthCheckBwPerimetreDetail> healthCheckBwPerimetreDetailList;

    @ManyToOne
    private User createurHealthCheckBwPerimetre;

}
