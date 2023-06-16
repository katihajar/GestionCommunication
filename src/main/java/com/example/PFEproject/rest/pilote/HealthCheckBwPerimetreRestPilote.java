package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.service.HealthCheckBwPerimetreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/pilote/healthcheck/bwperimetre")
public class HealthCheckBwPerimetreRestPilote {
    @Autowired
    HealthCheckBwPerimetreService healthCheckBwPerimetreService;
    @GetMapping("/findAll")
    public ResponseEntity<List<HealthCheckBwPerimetre>> findAll() {
        return ResponseEntity.ok().body(healthCheckBwPerimetreService.findAll());
    }

    @GetMapping("/searchHealth")
    public ResponseEntity<Page<HealthCheckBwPerimetre>> searchHealth(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateAjout,
            @RequestParam(required = true) String lot,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HealthCheckBwPerimetre> health;
        health = healthCheckBwPerimetreService.searchhealthBI(titre,dateAjout, lot, pageable);
        return ResponseEntity.ok(health);
    }
    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<HealthCheckBwPerimetre>> findByCreateurHealthCheckBwPerimetreLots(
            @PathVariable String lots,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<HealthCheckBwPerimetre> health = healthCheckBwPerimetreService.findByCreateurHealthCheckBwPerimetreLots(lots, page, pageSize);
        return ResponseEntity.ok(health);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<HealthCheckBwPerimetre>> findByCreateurHealthCheckBwPerimetreId(@PathVariable Long id) {
        return ResponseEntity.ok().body(healthCheckBwPerimetreService.findByCreateurHealthCheckBwPerimetreId(id));
    }
    @DeleteMapping("/delete/{id}")
    public int deleteHealthCheckBwPerimetreById(@PathVariable Long id) {
        return healthCheckBwPerimetreService.deleteHealthCheckBwPerimetreById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<HealthCheckBwPerimetre> save(@RequestBody HealthCheckBwPerimetre entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/healthcheck/bwperimetre/save").toUriString());
        return ResponseEntity.created(uri).body(healthCheckBwPerimetreService.save(entity));
    }
}
