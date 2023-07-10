package com.example.PFEproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Entity
public class AvancementActionProbleme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Size(min = 3, max = 1000)
    private String topic;
    @NonNull
    @Size(min = 3, max = 1000)
    private String update;
    @ManyToOne
    private Probleme probleme;
}
