package com.example.PFEproject.dto;

import com.example.PFEproject.bean.EtatProcessusMetier;

import java.util.Date;
import java.util.List;

public class HealthChekPreprodProdDTO {
    private Long id;
    private String titre;
    private Date dateAjout;
    private String type;
    private List<EtatProcessusMetier> etatProcessusMetierList;

    // Constructors, getters, and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<EtatProcessusMetier> getEtatProcessusMetierList() {
        return etatProcessusMetierList;
    }

    public void setEtatProcessusMetierList(List<EtatProcessusMetier> etatProcessusMetierList) {
        this.etatProcessusMetierList = etatProcessusMetierList;
    }

    public HealthChekPreprodProdDTO(Long id, String titre, Date dateAjout, String type, List<EtatProcessusMetier> etatProcessusMetierList) {
        this.id = id;
        this.titre = titre;
        this.dateAjout = dateAjout;
        this.type = type;
        this.etatProcessusMetierList = etatProcessusMetierList;
    }
}

