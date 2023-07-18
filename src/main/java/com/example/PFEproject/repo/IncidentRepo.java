package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.bean.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface IncidentRepo extends JpaRepository<Incident, Long> {
    List<Incident> findByCreateurIncidentUsername(String username);
    List<Incident> findByApplicationLot(String lots);
    List<Incident> findByApplicationLotAndDateAjout(String lots,Date date);
    Page<Incident> findByApplicationLot(String lots, Pageable pageable);
    int deleteIncidentById(Long id);
    List<Incident> findByApplicationResponsableId(Long id);

}
