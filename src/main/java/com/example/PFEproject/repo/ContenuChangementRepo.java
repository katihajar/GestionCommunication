package com.example.PFEproject.repo;

import com.example.PFEproject.bean.ContenuChangement;
import com.example.PFEproject.bean.PlanAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContenuChangementRepo extends JpaRepository<ContenuChangement,Long> {
    List<ContenuChangement> findByChangementPlanifierId(Long id);
    int deletePlanActionByChangementPlanifierId(Long id);
}
