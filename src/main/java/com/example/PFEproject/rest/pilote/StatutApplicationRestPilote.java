package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.StatutApplication;
import com.example.PFEproject.service.StatutApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/statutapp")
public class StatutApplicationRestPilote {
    @Autowired
    StatutApplicationService statutApplicationService;

    @GetMapping("/statutapp/{id}")
    public ResponseEntity<List<StatutApplication>> findByHealthChekPreprodProdId(@PathVariable Long id) {
        return ResponseEntity.ok().body(statutApplicationService.findByHealthChekPreprodProdId(id));
    }
    @DeleteMapping("/delete/{id}")
    public int deleteStatutApplicationById(@PathVariable Long id) {
        return statutApplicationService.deleteStatutApplicationById(id);
    }
}
