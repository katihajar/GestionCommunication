package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.HealthChekPreprodProdDetail;
import com.example.PFEproject.service.HealthChekPreprodProdDetailService;
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
@RequestMapping("/api/responsable/healthcheckdetail")
public class HealthChekPreprodProdDetailRestRespo {
    @Autowired
    HealthChekPreprodProdDetailService healthChekPreprodProdDetailService;
    @GetMapping("/health/{id}")
    public ResponseEntity<List<HealthChekPreprodProdDetail>> findByHealthChekPreprodProdId(@PathVariable Long id) {
        return ResponseEntity.ok().body(healthChekPreprodProdDetailService.findByHealthChekPreprodProdId(id));
    }
}
