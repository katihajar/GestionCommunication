package com.example.PFEproject.service;

import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.repo.IncidentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class IncidentService {
    @Autowired
    IncidentRepo incidentRepo;

    public List<Incident> findByCreateurIncidentUsername(String username) {
        return incidentRepo.findByCreateurIncidentUsername(username);
    }
}
