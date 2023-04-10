package com.example.PFEproject.repo;

import com.example.PFEproject.bean.StatutApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatutApplicationRepo extends JpaRepository<StatutApplication,Long> {
    List<StatutApplication> findByHealthChekPreprodProdId(Long id);
    int deleteByHealthChekPreprodProdId(Long id);
    int deleteStatutApplicationById(Long id);
}