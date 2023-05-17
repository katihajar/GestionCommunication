package com.example.PFEproject.repo;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HealthCheckBwPerimetreRepo  extends JpaRepository<HealthCheckBwPerimetre,Long> {
    List<HealthCheckBwPerimetre> findByCreateurHealthCheckBwPerimetreId(Long id);
    List<HealthCheckBwPerimetre> findByCreateurHealthCheckBwPerimetreLots(String lot);

    int deleteHealthCheckBwPerimetreById(Long id);
}
