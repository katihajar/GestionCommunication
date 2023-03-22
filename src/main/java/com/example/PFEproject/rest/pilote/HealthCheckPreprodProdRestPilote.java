package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.service.HealthChekPreprodProdService;
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
@RequestMapping("/api/pilote/healthcheck")
public class HealthCheckPreprodProdRestPilote {
    @Autowired
    HealthChekPreprodProdService healthChekPreprodProdService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<HealthChekPreprodProd>> findByCreateurHealthChekPreprodProdId(@PathVariable Long id) {
        return ResponseEntity.ok().body(healthChekPreprodProdService.findByCreateurHealthChekPreprodProdId(id));
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
