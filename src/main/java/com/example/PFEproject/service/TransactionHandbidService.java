package com.example.PFEproject.service;

import com.example.PFEproject.bean.MonitoringMstoolbox;
import com.example.PFEproject.bean.TransactionHandbid;
import com.example.PFEproject.repo.TransactionHandbidRepo;
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
public class TransactionHandbidService {
    @Autowired
    TransactionHandbidRepo transactionHandbidRepo;

    public List<TransactionHandbid> findByMstoolboxId(Long id) {
        return transactionHandbidRepo.findByMstoolboxId(id);
    }

    public int deleteByMstoolboxId(Long id) {
        return transactionHandbidRepo.deleteByMstoolboxId(id);
    }
    public List<TransactionHandbid> saveAll(MonitoringMstoolbox monitoring, List<TransactionHandbid> transactionHandbids) {
        List<TransactionHandbid> transactionHandbidList= new ArrayList<>();
        for (TransactionHandbid transaction : transactionHandbids) {
            TransactionHandbid transactionHandbid = new TransactionHandbid();
            TransactionHandbid transactionHandbid2 ;
            transactionHandbid.setMstoolbox(monitoring);
            transactionHandbid.setDate(transaction.getDate());
            transactionHandbid.setNombreRequet(transaction.getNombreRequet());
            transactionHandbid.setNombreRequetNontraite(transaction.getNombreRequetNontraite());
            transactionHandbid.setPourcentageDemandeReussie(transaction.getPourcentageDemandeReussie());
            transactionHandbid.setPourcentageDemandeNontraite(transaction.getPourcentageDemandeNontraite());
            transactionHandbid2= transactionHandbidRepo.save(transactionHandbid);
            transactionHandbidList.add(transactionHandbid2);
        }
        return transactionHandbidList;
    }
}
