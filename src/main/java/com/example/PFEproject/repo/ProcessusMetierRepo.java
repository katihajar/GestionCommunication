package com.example.PFEproject.repo;

import com.example.PFEproject.bean.ProcessusMetier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessusMetierRepo extends JpaRepository<ProcessusMetier,Long> {
    ProcessusMetier findProcessusMetierById(Long id);
    ProcessusMetier findByTitre(String titre);
    ProcessusMetier findProcessusMetierByIdAndTitre(Long id,String titre);
}
