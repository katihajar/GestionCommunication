package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.Operation;
import com.example.PFEproject.service.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/incident")
public class IncidentRestRepo {
    @Autowired
    IncidentService incidentService;
    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<Incident>> findByApplicationLot(
            @PathVariable String lots,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<Incident> incidents = incidentService.findByApplicationLot(lots, page, pageSize);
        return ResponseEntity.ok(incidents);
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

    @GetMapping("/findAll")
    public ResponseEntity<List<Incident>> findAll() {
        return ResponseEntity.ok().body(incidentService.findAll());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Incident>> findByApplicationResponsableId(@PathVariable Long id) {
        return ResponseEntity.ok().body(incidentService.findByApplicationResponsableId(id));
    }
}
