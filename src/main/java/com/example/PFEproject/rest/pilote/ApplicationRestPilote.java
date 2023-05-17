package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.service.ApplicationService;
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
@RequestMapping("/api/pilote/application")
public class ApplicationRestPilote {
    @Autowired
    ApplicationService applicationService;
    @GetMapping("/lot/{lots}")
    public ResponseEntity<List<Application>> findApplicationByLots(@PathVariable String lots) {
        return ResponseEntity.ok().body(applicationService.findApplicationByLots(lots));
    }
    @GetMapping("/AllApp")
    public ResponseEntity<List<Application>> findAll() {
        return ResponseEntity.ok().body(applicationService.findAll());
    }
}
