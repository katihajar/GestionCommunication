package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.Operation;
import com.example.PFEproject.service.ChangementPlanifierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/changementplanifier")
public class ChangementPlanifierRestPilote {

    @Autowired
    ChangementPlanifierService changementPlanifierService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ChangementPlanifier>> findByCreateurChangementId(@PathVariable Long id) {
        return ResponseEntity.ok().body(changementPlanifierService.findByCreateurChangementId(id));
    }

    @DeleteMapping("/delete/{id}")
    public int deleteChangementPlanifierById(@PathVariable Long id) {
        return changementPlanifierService.deleteChangementPlanifierById(id);
    }

    @PostMapping("/savechange")
    public ResponseEntity<ChangementPlanifier> save(@RequestBody ChangementPlanifier entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/changementplanifier/savechange").toUriString());
        return ResponseEntity.created(uri).body(changementPlanifierService.save(entity));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<ChangementPlanifier>> findAll() {
        return ResponseEntity.ok().body(changementPlanifierService.findAll());
    }
    @GetMapping("/searchChange")
    public ResponseEntity<Page<ChangementPlanifier>> searchChange(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDebut,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin,
            @RequestParam(required = false) String statut,
            @RequestParam(required = false) Long applicationId, // Change the type to Long
            @RequestParam(required = false) String vers,
            @RequestParam(required = true) String lot,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ChangementPlanifier> change;
        if (applicationId != null) {
            change = changementPlanifierService.searchChanges(titre, dateDebut, dateFin, statut, applicationId, vers, lot, pageable);
        } else {
            change = changementPlanifierService.searchChanges(titre, dateDebut, dateFin, statut, 0,vers, lot, pageable);
        }

        return ResponseEntity.ok(change);
    }


    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<ChangementPlanifier>> findByApplicationLot(
            @PathVariable String lots,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<ChangementPlanifier> changes = changementPlanifierService.findByApplicationLot(lots, page, pageSize);
        return ResponseEntity.ok(changes);
    }
}
