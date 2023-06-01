package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.FluxSapEurope;
import com.example.PFEproject.service.FluxSapEuropeService;
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
@RequestMapping("/api/pilote/healthcheck/fluxSapEurope")
public class FluxSapEuropeRestPilote {
    @Autowired
    FluxSapEuropeService fluxSapEuropeService;
    @GetMapping("/flux/{id}")
    public ResponseEntity<List<FluxSapEurope>> findByHealthCheckFlamingoId(@PathVariable Long id) {
        return ResponseEntity.ok().body(fluxSapEuropeService.findByHealthCheckFlamingoId(id));
    }
}
