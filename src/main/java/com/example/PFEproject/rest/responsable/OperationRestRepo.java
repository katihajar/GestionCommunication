package com.example.PFEproject.rest.responsable;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Operation>> findByApplicationResponsableId(@PathVariable Long id) {
        return ResponseEntity.ok().body(operationService.findByApplicationResponsableId(id));
    }
}
