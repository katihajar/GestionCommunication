package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Perimetre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerimetreRepo extends JpaRepository<Perimetre, Long> {
    Perimetre findPerimetreById(Long id);
    Perimetre findByTitre(String titre);
    Perimetre findPerimetreByIdAndTitre(Long id,String titre);

}
