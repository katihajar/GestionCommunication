package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.HealthCheckFlamingo;
import com.example.PFEproject.service.HealthCheckFlamingoService;
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
@RequestMapping("/api/pilote/healthcheck/flamingo")
public class HealthCheckFlamingoRestPilote {
    @Autowired
    HealthCheckFlamingoService healthCheckFlamingoService;
    @GetMapping("/lot/{lot}")
    public ResponseEntity<List<HealthCheckFlamingo>> findByCreateurHealthCheckFlamingoLots(@PathVariable String lot) {
        System.out.println(lot);
        return ResponseEntity.ok().body(healthCheckFlamingoService.findByCreateurHealthCheckFlamingoLots(lot));
    }
    @DeleteMapping("/delete/{id}")
    public int deleteHealthCheckFlamingoById(@PathVariable Long id) {
        return healthCheckFlamingoService.deleteHealthCheckFlamingoById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<HealthCheckFlamingo> save(@RequestBody HealthCheckFlamingo healthCheckFlamingo) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/healthcheck/flamingo/save").toUriString());
        return ResponseEntity.created(uri).body(healthCheckFlamingoService.save(healthCheckFlamingo));
    }
}
