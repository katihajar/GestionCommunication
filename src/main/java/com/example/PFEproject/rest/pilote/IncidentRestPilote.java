package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.service.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/incident")
public class IncidentRestPilote {

    @Autowired
    IncidentService incidentService;
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Incident>> findByCreateurIncidentUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(incidentService.findByCreateurIncidentUsername(username));
    }

    @DeleteMapping("/delete/{id}")
    public int deleteIncidentById(@PathVariable Long id) {
        return incidentService.deleteIncidentById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Incident> save(@RequestBody Incident incident) throws Exception {
        System.out.println("Incident "+ JSON.toString(incident));
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/incident/save").toUriString());
        return ResponseEntity.created(uri).body(incidentService.save(incident));
    }
}
