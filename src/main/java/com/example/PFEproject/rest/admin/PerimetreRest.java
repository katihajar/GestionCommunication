package com.example.PFEproject.rest.admin;

import com.example.PFEproject.bean.Perimetre;
import com.example.PFEproject.service.PerimetreService;
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
@RequestMapping("/api/admin/perimetre")
public class PerimetreRest {
    @Autowired
    PerimetreService perimetreService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Perimetre>> findAll() {
        return ResponseEntity.ok().body(perimetreService.findAll());
    }
    @PostMapping("/save")
    public ResponseEntity<Perimetre> save(@RequestBody Perimetre perimetre) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/perimetre/save").toUriString());
        return ResponseEntity.created(uri).body(perimetreService.save(perimetre));
    }
    @PutMapping("/update")
    public ResponseEntity<Perimetre> update(@RequestBody Perimetre perimetre) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/perimetre/update").toUriString());
        return ResponseEntity.created(uri).body(perimetreService.update(perimetre));
    }
}
