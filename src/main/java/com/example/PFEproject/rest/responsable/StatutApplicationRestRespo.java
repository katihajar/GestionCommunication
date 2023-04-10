package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.StatutApplication;
import com.example.PFEproject.service.StatutApplicationService;
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
@RequestMapping("/api/responsable/statutapp")
public class StatutApplicationRestRespo {
    @Autowired
    StatutApplicationService statutApplicationService;

    @GetMapping("/statutapp/{id}")
    public ResponseEntity<List<StatutApplication>> findByHealthChekPreprodProdId(@PathVariable Long id) {
        return ResponseEntity.ok().body(statutApplicationService.findByHealthChekPreprodProdId(id));
    }
}
