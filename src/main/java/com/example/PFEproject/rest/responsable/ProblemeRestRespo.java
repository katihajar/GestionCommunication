package com.example.PFEproject.rest.responsable;

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

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/probleme")
public class ProblemeRestRespo {
    @Autowired
    ProblemeService problemeService;
    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<Probleme>> findByApplicationLot( @PathVariable String lots,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int pageSize)  {
        Page<Probleme> problemePage =problemeService.findByApplicationLot(lots, page, pageSize);
        return ResponseEntity.ok(problemePage);
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

    public List<Probleme> findByApplicationResponsableId(@PathVariable Long id) {
        return problemeService.findByApplicationResponsableId(id);
    }
}
