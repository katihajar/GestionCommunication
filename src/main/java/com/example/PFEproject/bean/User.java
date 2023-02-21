package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    @Column( unique = true)
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String lots;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @OneToMany(mappedBy = "createurChangement")
    private List<Changement> changementList;
    @OneToMany(mappedBy = "createurIncident")
    private List<Incident> incidentList;

    @OneToMany(mappedBy = "pilote")
    private List<PiloteApplication> piloteApplicationList;
    @OneToMany(mappedBy = "responsable")
    private List<ResponsableApplication> responsableApplicationList;
    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
