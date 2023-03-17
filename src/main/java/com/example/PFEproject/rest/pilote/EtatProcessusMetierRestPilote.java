package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.EtatProcessusMetier;
import com.example.PFEproject.service.EtatProcessusMetierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/etatprocessus")
public class EtatProcessusMetierRestPilote {
    @Autowired
    EtatProcessusMetierService etatProcessusMetierService;
    @GetMapping("/etatprocessus/{id}")
    public ResponseEntity<List<EtatProcessusMetier>> findByHealthChekPreprodProdId(@PathVariable Long id) {
        return ResponseEntity.ok().body(etatProcessusMetierService.findByHealthChekPreprodProdId(id));
    }
    @DeleteMapping("/delete/{id}")
    public int deleteEtatProcessusMetierById(@PathVariable Long id) {
        return etatProcessusMetierService.deleteEtatProcessusMetierById(id);
    }
}
