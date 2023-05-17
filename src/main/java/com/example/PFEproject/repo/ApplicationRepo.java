package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
    Application findByNomApplication(String nom);
    Application findApplicationById(Long id);
    List<Application>  findApplicationByLot(String lots);
    List<Application> findByResponsableId(Long id);

    int deleteApplicationById(Long id);
}