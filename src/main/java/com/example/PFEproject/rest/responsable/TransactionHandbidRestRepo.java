package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.TransactionHandbid;
import com.example.PFEproject.service.TransactionHandbidService;
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
@RequestMapping("/api/responsable/transactionHandbid")
public class TransactionHandbidRestRepo {
    @Autowired
    TransactionHandbidService transactionHandbidService;
    @GetMapping("/mstoolbox/{id}")
    public ResponseEntity<List<TransactionHandbid>> findByMstoolboxId(@PathVariable Long id) {
        return ResponseEntity.ok().body(transactionHandbidService.findByMstoolboxId(id));
    }
}
