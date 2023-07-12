package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.TransactionHandbid;
import com.example.PFEproject.bean.TransactionSmile;
import com.example.PFEproject.service.TransactionHandbidService;
import com.example.PFEproject.service.TransactionSmileService;
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
@RequestMapping("/api/pilote/transactionSmile")
public class TransactionSmileRestPilote {
    @Autowired
    TransactionSmileService transactionSmileService;

    @GetMapping("/mstoolbox/{id}")
    public ResponseEntity<List<TransactionSmile>> findByMstoolboxId(@PathVariable Long id) {
        return ResponseEntity.ok().body(transactionSmileService.findByMstoolboxId(id));
    }
}
