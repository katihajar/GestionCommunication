package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.dto.HealthChekPreprodProdDTO;
import com.example.PFEproject.service.HealthChekPreprodProdService;
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
@RequestMapping("/api/responsable/healthcheck")
public class HealthCheckPreprodProdRestRespo {
    @Autowired
    HealthChekPreprodProdService healthChekPreprodProdService;
    @GetMapping("/findAll")
    public ResponseEntity<List<HealthChekPreprodProd>> findAll() {
        return ResponseEntity.ok().body(healthChekPreprodProdService.findAll());
    }

    @GetMapping("/lot/{lots}")
    public ResponseEntity<List<HealthChekPreprodProd>> findByCreateurHealthChekPreprodProdLot(@PathVariable String lots) {
        return ResponseEntity.ok().body(healthChekPreprodProdService.findByCreateurHealthChekPreprodProdLot(lots));
    }
    @GetMapping("/last10/{lot}")
    public ResponseEntity<List<HealthChekPreprodProdDTO>> getLast10Added(@PathVariable String lot) {
        return ResponseEntity.ok().body(healthChekPreprodProdService.getLast10Added(lot));
    }
}
