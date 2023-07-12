package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.TransactionSmile;
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
@RequestMapping("/api/responsable/transactionSmile")
public class TransactionSmileRestRepo {
    @Autowired
    TransactionSmileService transactionSmileService;

    @GetMapping("/mstoolbox/{id}")
    public ResponseEntity<List<TransactionSmile>> findByMstoolboxId(@PathVariable Long id) {
        return ResponseEntity.ok().body(transactionSmileService.findByMstoolboxId(id));
    }
}
