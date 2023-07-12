package com.example.PFEproject.service;

import com.example.PFEproject.bean.Implants;
import com.example.PFEproject.bean.MonitoringMstoolbox;
import com.example.PFEproject.bean.TransactionHandbid;
import com.example.PFEproject.bean.TransactionSmile;
import com.example.PFEproject.repo.ImplantsRepo;
import com.example.PFEproject.repo.TransactionSmileRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class TransactionSmileService {
    @Autowired
    TransactionSmileRepo transactionSmileRepo;

    public List<TransactionSmile> findByMstoolboxId(Long id) {
        return transactionSmileRepo.findByMstoolboxId(id);
    }

    public int deleteByMstoolboxId(Long id) {
        return transactionSmileRepo.deleteByMstoolboxId(id);
    }
    public List<TransactionSmile> saveAll(MonitoringMstoolbox monitoring, List<TransactionSmile> transactionSmiles) {
        List<TransactionSmile> transactionSmileList= new ArrayList<>();
        for (TransactionSmile transaction : transactionSmiles) {
            TransactionSmile transactionSmile = new TransactionSmile();
            TransactionSmile transactionSmile2 ;
            transactionSmile.setMstoolbox(monitoring);
            transactionSmile.setDate(transaction.getDate());
            transactionSmile.setNombreRequet(transaction.getNombreRequet());
            transactionSmile.setNombreRequetNontraite(transaction.getNombreRequetNontraite());
            transactionSmile.setPourcentageDemandeReussie(transaction.getPourcentageDemandeReussie());
            transactionSmile.setPourcentageDemandeNontraite(transaction.getPourcentageDemandeNontraite());
            transactionSmile2= transactionSmileRepo.save(transactionSmile);
            transactionSmileList.add(transactionSmile2);
        }
        return transactionSmileList;
    }
}
