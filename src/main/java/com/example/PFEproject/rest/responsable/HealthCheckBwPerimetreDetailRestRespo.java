package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.HealthCheckBwPerimetreDetail;
import com.example.PFEproject.service.HealthCheckBwPerimetreDetailService;
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
@RequestMapping("/api/responsable/healthcheckdetail/bwperimetre")
public class HealthCheckBwPerimetreDetailRestRespo {
    @Autowired
    HealthCheckBwPerimetreDetailService healthCheckBwPerimetreDetailService;

    @GetMapping("/health/{id}")
    public ResponseEntity<List<HealthCheckBwPerimetreDetail>> findByHealthCheckBwPerimetreId(@PathVariable Long id) {
        return ResponseEntity.ok().body(healthCheckBwPerimetreDetailService.findByHealthCheckBwPerimetreId(id));
    }
}
