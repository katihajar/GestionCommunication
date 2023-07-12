package com.example.PFEproject.repo;

import com.example.PFEproject.bean.FluxOptirenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FluxOptirentaRepo extends JpaRepository<FluxOptirenta, Long> {
        List<FluxOptirenta> findByOptirentaId(Long id);
        int deleteByOptirentaId(Long id);
}