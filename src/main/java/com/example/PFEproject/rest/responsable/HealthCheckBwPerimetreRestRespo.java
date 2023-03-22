package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.service.HealthCheckBwPerimetreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/healthcheck/bwperimetre")
public class HealthCheckBwPerimetreRestRespo {
    @Autowired
    HealthCheckBwPerimetreService healthCheckBwPerimetreService;
    @GetMapping("/findAll")
    public ResponseEntity<List<HealthCheckBwPerimetre>> findAll() {
        return ResponseEntity.ok().body(healthCheckBwPerimetreService.findAll());
    }
}
