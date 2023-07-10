package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Probleme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProblemeRepo extends JpaRepository<Probleme, Long> {
    List<Probleme> findByApplicationLot(String lots);
    Page<Probleme> findByApplicationLot(String lots, Pageable pageable);
    int deleteProblemeById(Long id);
    List<Probleme> findByApplicationResponsableId(Long id);
}
