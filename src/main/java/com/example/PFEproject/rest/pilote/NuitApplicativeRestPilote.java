package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.NuitApplicative;
import com.example.PFEproject.service.NuitApplicativeService;
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
@RequestMapping("/api/pilote/nuitapplicative")
public class NuitApplicativeRestPilote {
    @Autowired
    NuitApplicativeService nuitApplicativeService;


    @DeleteMapping("/delete/{id}")
    public int deleteNuitApplicativeById(@PathVariable Long id) {
        return nuitApplicativeService.deleteNuitApplicativeById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<NuitApplicative> save(@RequestBody NuitApplicative entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/nuitapplicative/save").toUriString());
        return ResponseEntity.created(uri).body(nuitApplicativeService.save(entity));
    }
    @GetMapping("/search")
    public Page<NuitApplicative> searchNuit(@RequestParam(required = false) String titre,
                                            @RequestParam(required = false) String statut,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date date,
                                            @RequestParam(required = true) String lot,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NuitApplicative> nuitApplicative =nuitApplicativeService.searchNuit(titre, statut,  date, lot, pageable);
        return nuitApplicativeService.searchNuit(titre, statut,  date, lot, pageable);
    }
    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<NuitApplicative>> findByCreateurNuitApplicativeLots( @PathVariable String lots,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        Page<NuitApplicative> nuitApplicative =nuitApplicativeService.findByCreateurNuitApplicativeLots(lots, page, pageSize);
        return ResponseEntity.ok(nuitApplicative);
    }


}
