package com.example.PFEproject.repo;

import com.example.PFEproject.bean.PlanningPointVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanningPointVersionRepo extends JpaRepository<PlanningPointVersion,Long> {
        List<PlanningPointVersion> findByPointVersionId(Long id);
        int deletePlanningPointVersionById(Long id);
        int deleteByPointVersionId(Long id);
        }