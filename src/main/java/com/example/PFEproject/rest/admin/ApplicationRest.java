package com.example.PFEproject.rest.admin;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.service.ApplicationService;
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
@RequestMapping("/api/admin/application")
public class ApplicationRest {
    @Autowired
    ApplicationService applicationService;
    @GetMapping("/lot/{lots}")
    public ResponseEntity<List<Application>> findApplicationByLots(@PathVariable String lots) {
        return ResponseEntity.ok().body(applicationService.findApplicationByLots(lots));
    }

    @DeleteMapping("/id/{id}")
    public int deleteApplicationById(@PathVariable Long id) {
        return applicationService.deleteApplicationById(id);
    }
    @GetMapping("/AllApp")
    public ResponseEntity<List<Application>> findAll() {
        return ResponseEntity.ok().body(applicationService.findAll());
    }
    @PostMapping("/saveApp")
    public ResponseEntity<Application> saveApp(@RequestBody Application application) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/application/saveApp").toUriString());
        return ResponseEntity.created(uri).body(applicationService.saveApp(application));
    }
    @PutMapping("/updateApp")
    public ResponseEntity<Application> updateApp(@RequestBody Application application) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/application/updateApp").toUriString());
        return ResponseEntity.created(uri).body(applicationService.updateApp(application));
    }
}
