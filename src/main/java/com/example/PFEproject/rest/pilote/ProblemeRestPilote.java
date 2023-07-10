package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.NuitApplicative;
import com.example.PFEproject.bean.Probleme;
import com.example.PFEproject.service.ProblemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/probleme")
public class ProblemeRestPilote {
    @Autowired
    ProblemeService problemeService;
    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<Probleme>> findByApplicationLot( @PathVariable String lots,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int pageSize)  {
        Page<Probleme> problemePage =problemeService.findByApplicationLot(lots, page, pageSize);
        return ResponseEntity.ok(problemePage);
    }
    @DeleteMapping("/delete/{id}")
    public int deleteProblemeById(@PathVariable Long id) {
        return problemeService.deleteProblemeById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<Probleme> save(@RequestBody Probleme prbl) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/probleme/save").toUriString());
        return ResponseEntity.created(uri).body(problemeService.save(prbl));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Probleme>> searchProb(@RequestParam(required = false) String topic,
                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateAjout,
                                     @RequestParam(required = false) String statut,
                                     @RequestParam(required = false) Long id,
                                     @RequestParam(required = false) String desc,
                                     @RequestParam(required = true) String lot,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Probleme> probleme;
        if (id != null) {
            probleme = problemeService.searchProb(topic, dateAjout, statut, id, desc, lot, pageable);
        } else {
            probleme = problemeService.searchProb(topic, dateAjout, statut, 0, desc, lot, pageable);
        }

        return ResponseEntity.ok(probleme);
    }
}
