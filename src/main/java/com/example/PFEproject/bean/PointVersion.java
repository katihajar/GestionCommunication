package com.example.PFEproject.bean;

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
public class PointVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 1, max = 100)
    private String titre;
    @NonNull
    @Size(min = 1, max = 100)
    private String version;
    @NonNull
    @Size(min = 0, max = 2000)
    private String lienComment;
    @NonNull
    @Size(min = 1, max = 200)
    private String goNoGoTNR;
    @NonNull
    @Size(min = 1, max = 200)
    private String goNoGoMEP;
    @NonNull
    @Size(min = 0, max = 2000)
    private String remarque;
    @NonNull
    @Size(min = 0, max = 1000)
    private String ticketConfirmer;
    @ManyToOne
    private Application application;
    @NonNull
    private Date dateAjout;
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
