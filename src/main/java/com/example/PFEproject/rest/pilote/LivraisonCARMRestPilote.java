package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.LivraisonCARM;
import com.example.PFEproject.service.LivraisonCARMService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/livraisoncarm")
public class LivraisonCARMRestPilote {
    @Autowired
    LivraisonCARMService livraisonCARMService;

    @GetMapping("/pointversion/{id}")
    public ResponseEntity<List<LivraisonCARM>> findByPointVersionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(livraisonCARMService.findByPointVersionId(id));
    }

    @DeleteMapping("/delete/{id}")
    public int deleteLivraisonCARMById(@PathVariable Long id) {
        return livraisonCARMService.deleteLivraisonCARMById(id);
    }
}
