package com.example.PFEproject.repo;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangementPlanifierRepo extends JpaRepository<ChangementPlanifier,Long> {
    List<ChangementPlanifier> findByCreateurChangementId(Long id);
    List<ChangementPlanifier> findByApplicationLot(String lots);
    Page<ChangementPlanifier> findByApplicationLot(String lots, Pageable pageable);
    List<ChangementPlanifier> findByApplicationResponsableId(Long id);
    int deleteChangementPlanifierById(Long id);
}
