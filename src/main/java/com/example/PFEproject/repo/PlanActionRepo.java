package com.example.PFEproject.repo;

import com.example.PFEproject.bean.PlanAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlanActionRepo extends JpaRepository<PlanAction, Long> {
    List<PlanAction> findByIncidentId(Long id);
    int deletePlanActionByIncidentId(Long id);
}
