package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.DestinataireCommunication;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.service.DestinataireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @GetMapping("/findAllByApplication/{id}")
    public ResponseEntity<List<DestinataireCommunication>> findDestinataireCommunicationByApplicationId(@PathVariable Long id) {
        return ResponseEntity.ok(destinataireService.findDestinataireCommunicationByApplicationId(id));
    }

    @GetMapping("/findByApplication/{id}")
    public ResponseEntity<Page<DestinataireCommunication>> findDestinataireCommunicationByApplicationId(@PathVariable Long id,
                                                                                        @RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Page<DestinataireCommunication> dests = destinataireService.findDestinataireCommunicationByApplicationId(id, page, pageSize);
        return ResponseEntity.ok(dests);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DestinataireCommunication>> searchDest(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String statut,
            @RequestParam(required = true) long id,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DestinataireCommunication> dests = destinataireService.searchDest(email, statut, id, type, pageable);

        return ResponseEntity.ok(dests);
    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<DestinataireCommunication>> saveAll(@RequestBody List<DestinataireCommunication> destinataireCommunications) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/destinataire/saveAll").toUriString());
        return ResponseEntity.created(uri).body(destinataireService.saveAll(destinataireCommunications));
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

    @PutMapping("/validate")
    public ResponseEntity<DestinataireCommunication> validationResponsable(@RequestBody DestinataireCommunication entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/responsable/destinataire/validate").toUriString());
        return ResponseEntity.created(uri).body(destinataireService.validationResponsable(entity));
    }
    @PutMapping("/retirer")
    public ResponseEntity<DestinataireCommunication> RetirerDest(@RequestBody DestinataireCommunication entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/responsable/destinataire/retirer").toUriString());
        return ResponseEntity.created(uri).body(destinataireService.RetirerDest(entity));
    }
}
