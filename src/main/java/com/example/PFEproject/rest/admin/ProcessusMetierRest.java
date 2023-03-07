package com.example.PFEproject.rest.admin;

import com.example.PFEproject.bean.ProcessusMetier;
import com.example.PFEproject.service.ProcessusMetierService;
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
@RequestMapping("/api/admin/processusmetier")
public class ProcessusMetierRest {
    @Autowired
    ProcessusMetierService processusMetierService;
    @GetMapping("/findAll")
    public ResponseEntity<List<ProcessusMetier>> findAll() {
        return ResponseEntity.ok().body(processusMetierService.findAll());
    }
    @PostMapping("/save")
    public ResponseEntity<ProcessusMetier> save(@RequestBody ProcessusMetier processusMetier) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/processusmetier/save").toUriString());
        return ResponseEntity.created(uri).body(processusMetierService.save(processusMetier));
    }
    @PutMapping("/update")
    public ResponseEntity<ProcessusMetier> update(@RequestBody ProcessusMetier processusMetier) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/processusmetier/update").toUriString());
        return ResponseEntity.created(uri).body(processusMetierService.update(processusMetier));
    }
}
