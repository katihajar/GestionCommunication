package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.HealthCheckBwPerimetreDetail;
import com.example.PFEproject.service.HealthCheckBwPerimetreDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/healthcheckdetail/bwperimetre")
public class HealthCheckBwPerimetreDetailRestPilote {
    @Autowired
    HealthCheckBwPerimetreDetailService healthCheckBwPerimetreDetailService;

    @GetMapping("/health/{id}")
    public ResponseEntity<List<HealthCheckBwPerimetreDetail>> findByHealthCheckBwPerimetreId(@PathVariable Long id) {
        return ResponseEntity.ok().body(healthCheckBwPerimetreDetailService.findByHealthCheckBwPerimetreId(id));
    }
    @DeleteMapping("/delete/{id}")
    public int deleteHealthCheckBwPerimetreDetailById(@PathVariable Long id) {
        return healthCheckBwPerimetreDetailService.deleteHealthCheckBwPerimetreDetailById(id);
    }
}
