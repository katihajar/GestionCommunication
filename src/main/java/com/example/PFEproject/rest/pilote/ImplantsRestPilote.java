package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Implants;
import com.example.PFEproject.service.ImplantsService;
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
@RequestMapping("/api/pilote/implants")
public class ImplantsRestPilote {
    @Autowired
    ImplantsService implantsService;
    @GetMapping("/mstoolbox/{id}")
    public ResponseEntity<List<Implants>> findByMstoolboxId(@PathVariable Long id) {
        return ResponseEntity.ok().body(implantsService.findByMstoolboxId(id));
    }
}
