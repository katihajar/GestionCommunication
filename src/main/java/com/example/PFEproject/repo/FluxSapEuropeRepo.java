package com.example.PFEproject.repo;

import com.example.PFEproject.bean.FluxSapEurope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FluxSapEuropeRepo extends JpaRepository<FluxSapEurope,Long> {
    List<FluxSapEurope> findByHealthCheckFlamingoId(Long id);
    int deleteByHealthCheckFlamingoId(Long id);
}
