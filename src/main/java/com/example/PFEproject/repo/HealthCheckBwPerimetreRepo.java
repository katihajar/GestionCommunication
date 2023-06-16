package com.example.PFEproject.repo;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HealthCheckBwPerimetreRepo  extends JpaRepository<HealthCheckBwPerimetre,Long> {
    List<HealthCheckBwPerimetre> findByCreateurHealthCheckBwPerimetreId(Long id);
    List<HealthCheckBwPerimetre> findByCreateurHealthCheckBwPerimetreLots(String lot);
    Page<HealthCheckBwPerimetre> findByCreateurHealthCheckBwPerimetreLots(String lots, Pageable pageable);

    int deleteHealthCheckBwPerimetreById(Long id);
}
