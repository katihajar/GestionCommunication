package com.example.PFEproject.rest.admin;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.service.ChangementPlanifierService;
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
@RequestMapping("/api/admin/changementplanifier")
public class ChangementPlanifierRest {
    @Autowired
    ChangementPlanifierService changementPlanifierService;
    @GetMapping("/findAll")
    public ResponseEntity<List<ChangementPlanifier>> findAll() {
        return ResponseEntity.ok().body(changementPlanifierService.findAll());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ChangementPlanifier>> findByApplicationResponsableId(@PathVariable Long id) {
        return ResponseEntity.ok().body(changementPlanifierService.findByApplicationResponsableId(id));
    }

}
