package com.example.PFEproject.repo;

import com.example.PFEproject.bean.MonitoringMstoolbox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringMstoolboxRepo extends JpaRepository<MonitoringMstoolbox, Long> {
    List<MonitoringMstoolbox> findByCreateurMonitoringLots(String lots);
    Page<MonitoringMstoolbox> findByCreateurMonitoringLots(String lots, Pageable pageable);
    int deleteMonitoringMstoolboxById(Long id);
}
