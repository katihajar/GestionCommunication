package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.ChangementPlanifier;
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

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/changementplanifier")
public class ChangementPlanifierRestRespo {
    @Autowired
    ChangementPlanifierService changementPlanifierService;
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
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ChangementPlanifier>> findByApplicationResponsableId(@PathVariable Long id) {
        return ResponseEntity.ok().body(changementPlanifierService.findByApplicationResponsableId(id));
    }

}
