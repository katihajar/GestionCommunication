package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.LivraisonCARM;
import com.example.PFEproject.service.LivraisonCARMService;
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
@RequestMapping("/api/responsable/livraisoncarm")
public class LivraisonCARMRestRespo {

    @Autowired
    LivraisonCARMService livraisonCARMService;

    @GetMapping("/pointversion/{id}")
    public ResponseEntity<List<LivraisonCARM>> findByPointVersionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(livraisonCARMService.findByPointVersionId(id));
    }
}
