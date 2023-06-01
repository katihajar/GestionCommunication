package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.FluxSapHarmonie;
import com.example.PFEproject.service.FluxSapHarmonieService;
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
@RequestMapping("/api/pilote/healthcheck/fluxSapHarmonie")
public class FluxSapHarmonieRestPilote {
    @Autowired
    FluxSapHarmonieService fluxSapHarmonieService;
    @GetMapping("/flux/{id}")
    public ResponseEntity<List<FluxSapHarmonie>> findByHealthCheckFlamingoId(@PathVariable Long id) {
        return ResponseEntity.ok().body(fluxSapHarmonieService.findByHealthCheckFlamingoId(id));
    }
}
