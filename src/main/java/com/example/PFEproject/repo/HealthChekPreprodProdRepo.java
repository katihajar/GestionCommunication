package com.example.PFEproject.repo;

import com.example.PFEproject.bean.HealthChekPreprodProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface HealthChekPreprodProdRepo extends JpaRepository<HealthChekPreprodProd,Long> {
    List<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdId(Long id);
    List<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdLots(String lot);

    List<HealthChekPreprodProd> findFirst10ByCreateurHealthChekPreprodProdLotsAndDateAjoutBeforeOrderByDateAjoutDesc(String lot,Date date);
    List<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdLotsAndDateAjoutBetweenOrderByDateAjoutDesc(String lot,Date tenDaysBefore,Date today);

    int deleteHealthChekPreprodProdById(Long id);
}
