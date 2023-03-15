package com.example.PFEproject.rest.responsable;

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
@RequestMapping("/api/responsable/destinataire")
public class DestinataireRestRespo {
    @Autowired
    DestinataireService destinataireService;

    @DeleteMapping("/deleteById/{id}")
    public int deleteDestinataireCommunicationById(@PathVariable Long id) {
        return destinataireService.deleteDestinataireCommunicationById(id);
    }
    @GetMapping("/findByApplication/{id}")
    public ResponseEntity<List<DestinataireCommunication>> findDestinataireCommunicationByApplicationId(@PathVariable Long id) {
        return ResponseEntity.ok().body(destinataireService.findDestinataireCommunicationByApplicationId(id));
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
