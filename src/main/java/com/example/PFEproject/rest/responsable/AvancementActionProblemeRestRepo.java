package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.AvancementActionProbleme;
import com.example.PFEproject.service.AvancementActionProblemeService;
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
@RequestMapping("/api/responsable/actionprobleme")
public class AvancementActionProblemeRestRepo {
    @Autowired
    AvancementActionProblemeService avancementActionProblemeService;
    @GetMapping("/probleme/{id}")
    public ResponseEntity<List<AvancementActionProbleme>> findByProblemeId(@PathVariable Long id) {
        return ResponseEntity.ok(avancementActionProblemeService.findByProblemeId(id));
    }
}
