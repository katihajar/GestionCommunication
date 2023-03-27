package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.DestinataireCommunication;
import com.example.PFEproject.service.DestinataireService;
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
@RequestMapping("/api/pilote/destinataire")
public class DestinataireRestPilote {
    @Autowired
    DestinataireService destinataireService;

    @GetMapping("/findAll")
    public ResponseEntity<List<DestinataireCommunication>> findAll() {
        return ResponseEntity.ok().body(destinataireService.findAll());
    }
    @DeleteMapping("/DeleteById/{id}")
    public int deleteDestinataireCommunicationById(@PathVariable Long id) {
        return destinataireService.deleteDestinataireCommunicationById(id);
    }
    @GetMapping("/findByApplication/{id}")
    public ResponseEntity<List<DestinataireCommunication>> findDestinataireCommunicationByApplicationId(@PathVariable Long id) {
        return ResponseEntity.ok().body(destinataireService.findDestinataireCommunicationByApplicationId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<DestinataireCommunication> save(@RequestBody DestinataireCommunication entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/destinataire/save").toUriString());
        return ResponseEntity.created(uri).body(destinataireService.save(entity));
    }
    @GetMapping("/findByNomApplication/{nom}")
    public ResponseEntity<List<DestinataireCommunication>> findByApplicationNomApplication(@PathVariable String nom) {
        return ResponseEntity.ok().body(destinataireService.findByApplicationNomApplication(nom));
    }
}
