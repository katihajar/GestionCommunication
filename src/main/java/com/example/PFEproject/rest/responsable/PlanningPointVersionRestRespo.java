package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.PlanningPointVersion;
import com.example.PFEproject.service.PlanningPointVersionService;
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
@RequestMapping("/api/responsable/planningpoint")
public class PlanningPointVersionRestRespo {
    @Autowired
    PlanningPointVersionService planningPointVersionService;
    @GetMapping("/pointversion/{id}")
    public ResponseEntity<List<PlanningPointVersion>> findByPointVersionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(planningPointVersionService.findByPointVersionId(id));
    }
}
