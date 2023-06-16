package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.dto.HealthChekPreprodProdDTO;
import com.example.PFEproject.service.HealthChekPreprodProdService;
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
@RequestMapping("/api/responsable/healthcheck")
public class HealthCheckPreprodProdRestRespo {
    @Autowired
    HealthChekPreprodProdService healthChekPreprodProdService;
    @GetMapping("/findAll")
    public ResponseEntity<List<HealthChekPreprodProd>> findAll() {
        return ResponseEntity.ok().body(healthChekPreprodProdService.findAll());
    }

    @GetMapping("/searchHealth")
    public ResponseEntity<Page<HealthChekPreprodProd>> searchHealth(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateAjout,
            @RequestParam(required = false) String type,
            @RequestParam(required = true) String lot,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<HealthChekPreprodProd> health;

        health = healthChekPreprodProdService.searchhealthMonetics(titre, type,dateAjout, lot, pageable);

        return ResponseEntity.ok(health);
    }


    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<HealthChekPreprodProd>> findByCreateurHealthChekPreprodProdLots(
            @PathVariable String lots,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<HealthChekPreprodProd> health = healthChekPreprodProdService.findByCreateurHealthChekPreprodProdLots(lots, page, pageSize);
        return ResponseEntity.ok(health);
    }
    @GetMapping("/last10/{lot}")
    public ResponseEntity<List<HealthChekPreprodProdDTO>> getLast10Added(@PathVariable String lot) {
        return ResponseEntity.ok().body(healthChekPreprodProdService.getLast10Added(lot));
    }
}
