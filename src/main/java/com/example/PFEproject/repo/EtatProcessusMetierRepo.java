package com.example.PFEproject.repo;

import com.example.PFEproject.bean.EtatProcessusMetier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EtatProcessusMetierRepo extends JpaRepository<EtatProcessusMetier,Long> {
    List<EtatProcessusMetier> findByHealthChekPreprodProdId(Long id);
    int deleteByHealthChekPreprodProdId(Long id);
    int deleteEtatProcessusMetierById(Long id);
}