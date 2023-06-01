package com.example.PFEproject.repo;

import com.example.PFEproject.bean.FluxSapHarmonie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FluxSapHarmonieRepo extends JpaRepository<FluxSapHarmonie,Long> {
    List<FluxSapHarmonie> findByHealthCheckFlamingoId(Long id);
    int deleteByHealthCheckFlamingoId(Long id);
}