package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.SuiviVolumetrie;
import com.example.PFEproject.service.SuiviVolumetrieService;
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
@RequestMapping("/api/responsable/suivivolumetrie")
public class SuiviVolumetrieRestRespo {
    @Autowired
    SuiviVolumetrieService suiviVolumetrieService;
    @GetMapping("/nuitApplicative/{id}")
    public ResponseEntity<List<SuiviVolumetrie>> findByNuitApplicativeId(@PathVariable Long id) {
        return ResponseEntity.ok(suiviVolumetrieService.findByNuitApplicativeId(id));
    }
}
