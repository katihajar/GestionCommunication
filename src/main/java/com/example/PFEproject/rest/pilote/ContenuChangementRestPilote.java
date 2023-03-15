package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.ContenuChangement;
import com.example.PFEproject.service.ChangementPlanifierService;
import com.example.PFEproject.service.ContenuChangementService;
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
@RequestMapping("/api/pilote/contenuchangement")
public class ContenuChangementRestPilote {
    @Autowired
    ContenuChangementService contenuChangementService;

    @GetMapping("/changement/{id}")
    public ResponseEntity<List<ContenuChangement>> findByChangementPlanifierId(@PathVariable Long id) {
        return ResponseEntity.ok().body(contenuChangementService.findByChangementPlanifierId(id));
    }
}
