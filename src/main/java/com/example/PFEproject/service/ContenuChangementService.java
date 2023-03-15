package com.example.PFEproject.service;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.ContenuChangement;
import com.example.PFEproject.repo.ContenuChangementRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class ContenuChangementService {
    @Autowired
    ContenuChangementRepo contenuChangementRepo;

    public List<ContenuChangement> findByChangementPlanifierId(Long id) {
        return contenuChangementRepo.findByChangementPlanifierId(id);
    }

    public int deletePlanActionByChangementPlanifierId(Long id) {
        return contenuChangementRepo.deletePlanActionByChangementPlanifierId(id);
    }
    public  List<ContenuChangement> saveAllContent(ChangementPlanifier chg, List<ContenuChangement> contenuChangements) {
        List<ContenuChangement> contenuChangementList= new ArrayList<>();
        for (ContenuChangement contenu : contenuChangements) {
            ContenuChangement cont = new ContenuChangement();
            ContenuChangement contenuChangement2 ;
            cont.setChangementPlanifier(chg);
            cont.setTitre(contenu.getTitre());
            cont.setDescription(contenu.getDescription());
            contenuChangement2= contenuChangementRepo.save(cont);
            contenuChangementList.add(contenuChangement2);
        }
        return contenuChangementList;
    }
}
