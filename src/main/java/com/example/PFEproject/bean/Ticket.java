package com.example.PFEproject.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 100)
    private String numero;
    @NonNull
    @Size(min = 3, max = 500)
    private String description;
    @NonNull
    private String type;
    @JsonIgnore
    @ManyToOne
    private PointVersion pointVersion;
}
