package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.PointVersion;
import com.example.PFEproject.service.PointVersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/pointversion")
public class PointVersionRestPilote {
    @Autowired
    PointVersionService pointVersionService;


    @GetMapping("/user/{id}")
    public ResponseEntity<List<PointVersion>> findByCreateurPointVersionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(pointVersionService.findByCreateurPointVersionId(id));
    }

    @DeleteMapping("/delete/{id}")
    public int deletePointVersionById(@PathVariable Long id) {
        return pointVersionService.deletePointVersionById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<PointVersion> save(@RequestBody PointVersion pointVersion) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/pointversion/save").toUriString());
        return ResponseEntity.created(uri).body(pointVersionService.save(pointVersion));
    }
}
