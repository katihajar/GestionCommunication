package com.example.PFEproject.rest.responsable;

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

import java.util.Date;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/nuitapplicative")
public class NuitApplicativeRestRespo {
    @Autowired
    NuitApplicativeService nuitApplicativeService;

    @GetMapping("/search")
    public Page<NuitApplicative> searchNuit(@RequestParam(required = false) String titre,
                                            @RequestParam(required = false) String statut,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                            @RequestParam(required = true) String lot,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NuitApplicative> nuitApplicative =nuitApplicativeService.searchNuit(titre, statut, date, lot, pageable);
        return nuitApplicativeService.searchNuit(titre, statut, date, lot, pageable);
    }
    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<NuitApplicative>> findByCreateurNuitApplicativeLots(@PathVariable String lots,
                                                                                   @RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int pageSize) {
        Page<NuitApplicative> nuitApplicative =nuitApplicativeService.findByCreateurNuitApplicativeLots(lots, page, pageSize);
        return ResponseEntity.ok(nuitApplicative);
    }

}
