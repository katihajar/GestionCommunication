package com.example.PFEproject.repo;

import com.example.PFEproject.bean.HealthCheckBwPerimetreDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HealthCheckBwPerimetreDetailRepo extends JpaRepository<HealthCheckBwPerimetreDetail,Long> {
    List<HealthCheckBwPerimetreDetail> findByHealthCheckBwPerimetreId(Long id);
    int deleteHealthCheckBwPerimetreDetailById(Long id);
    int deleteByHealthCheckBwPerimetreId(Long id);

}
