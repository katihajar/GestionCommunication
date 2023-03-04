package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
    Application findByNomApplication(String nom);
    int deleteApplicationById(Long id);
}