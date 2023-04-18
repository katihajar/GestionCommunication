package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.ProcessusMetier;
import com.example.PFEproject.service.ProcessusMetierService;
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
@RequestMapping("/api/responsable/processusmetier")
public class ProcessusMetierRestRespo {
    @Autowired
    ProcessusMetierService processusMetierService;
    @GetMapping("/findAll")
    public ResponseEntity<List<ProcessusMetier>> findAll() {
        return ResponseEntity.ok().body(processusMetierService.findAll());
    }
}
