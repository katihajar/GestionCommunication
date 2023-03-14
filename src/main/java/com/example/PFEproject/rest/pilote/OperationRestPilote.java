package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Operation;
import com.example.PFEproject.service.OperationService;
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
@RequestMapping("/api/pilote/operation")
public class OperationRestPilote {
    @Autowired
    OperationService operationService;
    @DeleteMapping("/delete/{id}")
    public int deleteOperationById(@PathVariable Long id) {
        return operationService.deleteOperationById(id);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<Operation>> findByCreateurOperationId(@PathVariable Long id) {
        return ResponseEntity.ok().body(operationService.findByCreateurOperationId(id));
    }
    @PostMapping("/saveoperation")
    public ResponseEntity<Operation> save(@RequestBody Operation entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/operation/saveoperation").toUriString());
        return ResponseEntity.created(uri).body(operationService.save(entity));
    }
}
