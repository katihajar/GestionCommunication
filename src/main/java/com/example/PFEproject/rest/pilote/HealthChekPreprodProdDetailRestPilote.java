package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.HealthChekPreprodProdDetail;
import com.example.PFEproject.service.HealthChekPreprodProdDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/healthcheckdetail")
public class HealthChekPreprodProdDetailRestPilote {
    @Autowired
    HealthChekPreprodProdDetailService healthChekPreprodProdDetailService;
    @GetMapping("/health/{id}")
    public ResponseEntity<List<HealthChekPreprodProdDetail>> findByHealthChekPreprodProdId(@PathVariable Long id) {
        return ResponseEntity.ok().body(healthChekPreprodProdDetailService.findByHealthChekPreprodProdId(id));
    }
    @DeleteMapping("/delete/{id}")
    public int deleteHealthChekPreprodProdDetailById(@PathVariable Long id) {
        return healthChekPreprodProdDetailService.deleteHealthChekPreprodProdDetailById(id);
    }
}
