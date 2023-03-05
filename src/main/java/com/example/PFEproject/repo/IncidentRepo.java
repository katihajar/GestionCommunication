package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.bean.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IncidentRepo extends JpaRepository<Incident, Long> {
    List<Incident> findByCreateurIncidentUsername (String username);
}
