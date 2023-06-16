package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.HealthCheckFlamingo;
import com.example.PFEproject.service.HealthCheckFlamingoService;
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
@RequestMapping("/api/responsable/healthcheck/flamingo")
public class HealthCheckFlamingoRestRespo {
    @Autowired
    HealthCheckFlamingoService healthCheckFlamingoService;
    @GetMapping("/searchHealth")
    public ResponseEntity<Page<HealthCheckFlamingo>> searchHealth(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFlux,
            @RequestParam(required = true) String lot,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HealthCheckFlamingo> health;
        health = healthCheckFlamingoService.searchhealthFlamingo(titre,dateFlux, lot, pageable);
        return ResponseEntity.ok(health);
    }
    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<HealthCheckFlamingo>> findByCreateurHealthCheckFlamingoLots(
            @PathVariable String lots,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<HealthCheckFlamingo> health = healthCheckFlamingoService.findByCreateurHealthCheckFlamingoLots(lots, page, pageSize);
        return ResponseEntity.ok(health);
    }
}
