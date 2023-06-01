package com.example.PFEproject.repo;

import com.example.PFEproject.bean.HealthCheckFlamingo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthCheckFlamingoRepo extends JpaRepository<HealthCheckFlamingo,Long> {
    List<HealthCheckFlamingo> findByCreateurHealthCheckFlamingoLots(String lot);
    int deleteHealthCheckFlamingoById(Long id);

}
