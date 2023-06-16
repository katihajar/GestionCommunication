package com.example.PFEproject.repo;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.HealthCheckFlamingo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthCheckFlamingoRepo extends JpaRepository<HealthCheckFlamingo,Long> {
    List<HealthCheckFlamingo> findByCreateurHealthCheckFlamingoLots(String lot);
    Page<HealthCheckFlamingo> findByCreateurHealthCheckFlamingoLots(String lots, Pageable pageable);

    int deleteHealthCheckFlamingoById(Long id);

}
