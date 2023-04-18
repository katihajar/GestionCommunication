package com.example.PFEproject.repo;

import com.example.PFEproject.bean.HealthChekPreprodProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface HealthChekPreprodProdRepo extends JpaRepository<HealthChekPreprodProd,Long> {
    List<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdId(Long id);
    List<HealthChekPreprodProd> findFirst10ByDateAjoutBeforeOrderByDateAjoutDesc(Date date);

    int deleteHealthChekPreprodProdById(Long id);
}
