package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.Operation;
import com.example.PFEproject.service.OperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/operation")
public class OperationRestRepo {
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
    @GetMapping("/lot/{lots}")
    public ResponseEntity<List<Operation>> findByApplicationLot(@PathVariable String lots) {
        return ResponseEntity.ok().body(operationService.findByApplicationLot(lots));
    }
}
