package com.example.PFEproject.repo;

import com.example.PFEproject.bean.MonitoringOptirenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringOptirentaRepo extends JpaRepository<MonitoringOptirenta, Long> {
    List<MonitoringOptirenta> findByCreateurMonitoringLots(String lots);
    Page<MonitoringOptirenta> findByCreateurMonitoringLots(String lots, Pageable pageable);
    int deleteMonitoringOptirentaById(Long id);
}
