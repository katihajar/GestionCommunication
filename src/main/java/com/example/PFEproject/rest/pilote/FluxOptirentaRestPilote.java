package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.FluxOptirenta;
import com.example.PFEproject.service.FluxOptirentaService;
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
@RequestMapping("/api/pilote/fluxOptirenta")
public class FluxOptirentaRestPilote {
    @Autowired
    FluxOptirentaService fluxOptirentaService;
    @GetMapping("/optirenta/{id}")
    public ResponseEntity<List<FluxOptirenta>> findByOptirentaId(@PathVariable Long id) {
        return ResponseEntity.ok().body(fluxOptirentaService.findByOptirentaId(id));
    }
}
