package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PointVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 100)
    private String titre;
    @NonNull
    @Size(min = 3, max = 500)
    private String lienComment;
    @NonNull
    @Size(min = 3, max = 100)
    private String GoNoGoTNR;
    @NonNull
    @Size(min = 3, max = 100)
    private String GoNoGoMEP;
    @NonNull
    @Size(min = 3, max = 300)
    private String remarque;
    @ManyToOne
    private Application application;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "pointVersion")
    private List<LivraisonCARM> livraisonCARMList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "pointVersion")
    private List<PlanningPointVersion> planningPointVersionList;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "pointVersion")
    private List<Ticket> ticketList;
    @ManyToOne
    private User createurPointVersion;
    private String imageType;
    private long imageSize;
    private byte[] image;

}
