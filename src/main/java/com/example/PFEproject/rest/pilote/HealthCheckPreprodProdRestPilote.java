package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.dto.HealthChekPreprodProdDTO;
import com.example.PFEproject.service.HealthChekPreprodProdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/healthcheck")
public class HealthCheckPreprodProdRestPilote {
    @Autowired
    HealthChekPreprodProdService healthChekPreprodProdService;

    @GetMapping("/lot/{lots}")
    public ResponseEntity<List<HealthChekPreprodProd>> findByCreateurHealthChekPreprodProdLot(@PathVariable String lots) {
        return ResponseEntity.ok().body(healthChekPreprodProdService.findByCreateurHealthChekPreprodProdLot(lots));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<HealthChekPreprodProd>> findByCreateurHealthChekPreprodProdId(@PathVariable Long id) {
        return ResponseEntity.ok().body(healthChekPreprodProdService.findByCreateurHealthChekPreprodProdId(id));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<HealthChekPreprodProd>> findAll() {
        return ResponseEntity.ok().body(healthChekPreprodProdService.findAll());
    }
    @GetMapping("/last10/{lot}")
    public ResponseEntity<List<HealthChekPreprodProdDTO>> getLast10Added(@PathVariable String lot) {
        return ResponseEntity.ok().body(healthChekPreprodProdService.getLast10Added(lot));
    }
    @GetMapping("/historique/{lot}")
    public ResponseEntity<List<HealthChekPreprodProdDTO>> getHistorique(@PathVariable String lot) {
        return ResponseEntity.ok().body(healthChekPreprodProdService.getHistorique(lot));
    }

    @DeleteMapping("/delete/{id}")
    public int deleteHealthChekPreprodProdById(@PathVariable Long id) {
        return healthChekPreprodProdService.deleteHealthChekPreprodProdById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<HealthChekPreprodProd> save(@RequestBody HealthChekPreprodProd entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/healthcheck/save").toUriString());
        return ResponseEntity.created(uri).body(healthChekPreprodProdService.save(entity));
    }
}
