package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Perimetre;
import com.example.PFEproject.service.PerimetreService;
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
@RequestMapping("/api/pilote/perimetre")
public class PerimetreRestPilote {
    @Autowired
    PerimetreService perimetreService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Perimetre>> findAll() {
        return ResponseEntity.ok().body(perimetreService.findAll());
    }
}
