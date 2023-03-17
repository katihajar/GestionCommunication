package com.example.PFEproject.repo;

import com.example.PFEproject.bean.HealthChekPreprodProdDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface HealthChekPreprodProdDetailRepo extends JpaRepository<HealthChekPreprodProdDetail,Long> {
    List<HealthChekPreprodProdDetail> findByHealthChekPreprodProdId(Long id);
    int deleteHealthChekPreprodProdDetailById(Long id);
    int deleteByHealthChekPreprodProdId(Long id);

}
