package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.service.IncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
