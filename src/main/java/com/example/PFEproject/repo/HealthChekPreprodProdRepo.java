package com.example.PFEproject.repo;

import com.example.PFEproject.bean.HealthChekPreprodProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HealthChekPreprodProdRepo extends JpaRepository<HealthChekPreprodProd,Long> {
    List<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdId(Long id);
    int deleteHealthChekPreprodProdById(Long id);
}
