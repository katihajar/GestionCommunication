package com.example.PFEproject.repo;

import com.example.PFEproject.bean.FluxEAI;
import com.example.PFEproject.bean.FluxSalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FluxEAIRepo extends JpaRepository<FluxEAI,Long> {
    List<FluxEAI> findByHealthCheckFlamingoId(Long id);
    int deleteByHealthCheckFlamingoId(Long id);
}
