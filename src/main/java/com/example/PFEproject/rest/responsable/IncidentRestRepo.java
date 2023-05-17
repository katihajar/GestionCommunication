package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.Operation;
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
@RequestMapping("/api/responsable/incident")
public class IncidentRestRepo {
    @Autowired
    IncidentService incidentService;
    @GetMapping("/lot/{lots}")
    public ResponseEntity<List<Incident>> findByApplicationLot(@PathVariable String lots) {
        return ResponseEntity.ok().body(incidentService.findByApplicationLot(lots));
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
