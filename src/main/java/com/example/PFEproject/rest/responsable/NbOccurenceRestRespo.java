package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.NbOccurence;
import com.example.PFEproject.service.NbOccurenceService;
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
@RequestMapping("/api/responsable/nboccurence")
public class NbOccurenceRestRespo {
    @Autowired
    NbOccurenceService nbOccurenceService;
    @GetMapping("/nuitApplicative/{id}")
    public ResponseEntity<List<NbOccurence>> findByNuitApplicativeId(@PathVariable Long id) {
        return ResponseEntity.ok(nbOccurenceService.findByNuitApplicativeId(id));
    }
}
