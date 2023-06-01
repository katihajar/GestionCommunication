package com.example.PFEproject.rest.responsable;

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
@RequestMapping("/api/responsable/healthcheck/flamingo")
public class HealthCheckFlamingoRestRespo {
    @Autowired
    HealthCheckFlamingoService healthCheckFlamingoService;
    @GetMapping("/lot/{lot}")
    public ResponseEntity<List<HealthCheckFlamingo>> findByCreateurHealthCheckFlamingoLots(@PathVariable String lot) {
        System.out.println(lot);
        return ResponseEntity.ok().body(healthCheckFlamingoService.findByCreateurHealthCheckFlamingoLots(lot));
    }
}
