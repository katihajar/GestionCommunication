package com.example.PFEproject.rest.admin;

import com.example.PFEproject.bean.PiloteApplication;
import com.example.PFEproject.service.PiloteApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/piloteapplication")
public class PiloteApplicationRest {
    @Autowired
    PiloteApplicationService piloteApplicationService;

    @GetMapping("/{username}/{nomApp}")
    public PiloteApplication findByPiloteUsernameAndApplicationNomApplication(@PathVariable String username,@PathVariable String nomApp) {
        return piloteApplicationService.findByPiloteUsernameAndApplicationNomApplication(username, nomApp);
    }
    @PostMapping("/savePiloteApp")
    public ResponseEntity<PiloteApplication> savePiloteApp(@RequestBody PiloteApplication pilote) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/piloteapplication/savePiloteApp").toUriString());
        return ResponseEntity.created(uri).body(piloteApplicationService.savePiloteApp(pilote));
    }
}
