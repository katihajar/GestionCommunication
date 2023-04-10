package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.Operation;
import com.example.PFEproject.service.ChangementPlanifierService;
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
}
