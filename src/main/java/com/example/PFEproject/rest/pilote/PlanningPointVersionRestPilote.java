package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.PlanningPointVersion;
import com.example.PFEproject.service.PlanningPointVersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/planningpoint")
public class PlanningPointVersionRestPilote {
    @Autowired
    PlanningPointVersionService planningPointVersionService;
    @GetMapping("/pointversion/{id}")
    public ResponseEntity<List<PlanningPointVersion>> findByPointVersionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(planningPointVersionService.findByPointVersionId(id));
    }
    @DeleteMapping("/delete/{id}")
    public int deletePlanningPointVersionById(@PathVariable Long id) {
        return planningPointVersionService.deletePlanningPointVersionById(id);
    }
}
