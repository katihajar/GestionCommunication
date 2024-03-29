package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Entity
public class DestinataireCommunication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(max = 60)
    @Column( unique = true)
    @Email
    private String email;
    @NonNull
    private String typeDest;
    private String statutRespo;

    @ManyToOne
    private Application application;

}
