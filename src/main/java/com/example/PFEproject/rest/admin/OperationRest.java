package com.example.PFEproject.rest.admin;

import com.example.PFEproject.bean.Operation;
import com.example.PFEproject.service.OperationService;
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
@RequestMapping("/api/admin/operation")
public class OperationRest {
    @Autowired
    OperationService operationService;
    @GetMapping("/findAll")
    public ResponseEntity<List<Operation>> findAll() {
        return ResponseEntity.ok().body(operationService.findAll());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Operation>> findByApplicationResponsableId(@PathVariable Long id) {
        return ResponseEntity.ok().body(operationService.findByApplicationResponsableId(id));
    }
}
