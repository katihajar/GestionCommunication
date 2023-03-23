package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.service.HealthCheckBwPerimetreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/healthcheck/bwperimetre")
public class HealthCheckBwPerimetreRestPilote {
    @Autowired
    HealthCheckBwPerimetreService healthCheckBwPerimetreService;

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
