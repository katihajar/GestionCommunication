package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DestinataireCommunication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    @NonNull
    @Size(max = 60)
    @Column( unique = true)
    @Email
    private String email;

    @ManyToOne
    private Application application;


}
