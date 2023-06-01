package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.FluxEAI;
import com.example.PFEproject.service.FluxEAIService;
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
@RequestMapping("/api/pilote/healthcheck/fluxEAI")
public class FluxEAIRestPilote {
    @Autowired
    FluxEAIService fluxEAIService;
    @GetMapping("/flux/{id}")
    public ResponseEntity<List<FluxEAI>> findByHealthCheckFlamingoId(@PathVariable Long id) {
        return ResponseEntity.ok().body(fluxEAIService.findByHealthCheckFlamingoId(id));
    }
}
