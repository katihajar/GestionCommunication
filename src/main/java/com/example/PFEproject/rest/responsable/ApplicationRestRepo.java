package com.example.PFEproject.rest.responsable;

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
@RequestMapping("/api/responsable/application")
public class ApplicationRestRepo {
    @Autowired
    ApplicationService applicationService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Application>> findByResponsableId(@PathVariable Long id) {
        return ResponseEntity.ok().body(applicationService.findByResponsableId(id));
    }
}
