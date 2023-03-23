package com.example.PFEproject.service;

import com.example.PFEproject.bean.EtatProcessusMetier;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.repo.EtatProcessusMetierRepo;
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
public class EtatProcessusMetierService {
    @Autowired
    EtatProcessusMetierRepo etatProcessusMetierRepo;

    public List<EtatProcessusMetier> findByHealthChekPreprodProdId(Long id) {
        return etatProcessusMetierRepo.findByHealthChekPreprodProdId(id);
    }

    public int deleteEtatProcessusMetierById(Long id) {
        return etatProcessusMetierRepo.deleteEtatProcessusMetierById(id);
    }

    public int deleteByHealthChekPreprodProdId(Long id) {
        return etatProcessusMetierRepo.deleteByHealthChekPreprodProdId(id);
    }

    public  List<EtatProcessusMetier> saveAllEtatProcessus(HealthChekPreprodProd in, List<EtatProcessusMetier> etatProcessusMetiers) {
        List<EtatProcessusMetier> etatProcessusMetierList= new ArrayList<>();
        for (EtatProcessusMetier etat : etatProcessusMetiers) {
            EtatProcessusMetier etatproc = new EtatProcessusMetier();
            etatproc.setHealthChekPreprodProd(in);
            etatproc.setProcessusMetier(etat.getProcessusMetier());
            etatproc.setStatut(etat.getStatut());
            EtatProcessusMetier etat2= etatProcessusMetierRepo.save(etatproc);
            etatProcessusMetierList.add(etat2);
        }
        return etatProcessusMetierList;
    }
}
