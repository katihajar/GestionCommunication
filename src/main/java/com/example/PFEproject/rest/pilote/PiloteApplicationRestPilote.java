package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.PiloteApplication;
import com.example.PFEproject.service.PiloteApplicationService;
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
@RequestMapping("/api/pilote/piloteapplication")
public class PiloteApplicationRestPilote {
    @Autowired
    PiloteApplicationService piloteApplicationService;

    @GetMapping("/app/{nomApp}")
    public ResponseEntity<List<PiloteApplication>> findByApplicationNomApplication(@PathVariable String nomApp) {
        return ResponseEntity.ok().body(piloteApplicationService.findByApplicationNomApplication(nomApp));
    }
    @GetMapping("/app/user/{username}")
    public ResponseEntity<List<PiloteApplication>> findByPiloteUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(piloteApplicationService.findByPiloteUsername(username));
    }
}
