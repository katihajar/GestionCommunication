package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.service.ApplicationService;
import com.example.PFEproject.service.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/incident")
public class IncidentRestPilote {

    @Autowired
    IncidentService incidentService;
    @Autowired
    ApplicationService applicationService;
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Incident>> findByCreateurIncidentUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(incidentService.findByCreateurIncidentUsername(username));
    }
    @GetMapping("/searchIncident")
    public ResponseEntity<Page<Incident>> searchIncidents(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDebut,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin,
            @RequestParam(required = false) String statut,
            @RequestParam(required = false) Long applicationId, // Change the type to Long
            @RequestParam(required = false) String desc,
            @RequestParam(required = true) String lot,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Incident> incidents;
        if (applicationId != null) {
            incidents = incidentService.searchIncidents(titre, dateDebut, dateFin, statut, applicationId, desc, lot, pageable);
        } else {
            incidents = incidentService.searchIncidents(titre, dateDebut, dateFin, statut, 0,desc, lot, pageable);
        }

        return ResponseEntity.ok(incidents);
    }


    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<Incident>> findByApplicationLot(
            @PathVariable String lots,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<Incident> incidents = incidentService.findByApplicationLot(lots, page, pageSize);
        return ResponseEntity.ok(incidents);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Incident>> findAll() {
        return ResponseEntity.ok().body(incidentService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public int deleteIncidentById(@PathVariable Long id) {
        return incidentService.deleteIncidentById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Incident> save(@RequestBody Incident incident) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/incident/save").toUriString());
        return ResponseEntity.created(uri).body(incidentService.save(incident));
    }
}
