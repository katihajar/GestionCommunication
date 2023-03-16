package com.example.PFEproject.service;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.repo.ChangementPlanifierRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ChangementPlanifierService {
    @Autowired
    ChangementPlanifierRepo changementPlanifierRepo;

    @Autowired
    ContenuChangementService contenuChangementService;

    public List<ChangementPlanifier> findByCreateurChangementId(Long id) {
        return changementPlanifierRepo.findByCreateurChangementId(id);
    }

    public List<ChangementPlanifier> findByApplicationResponsableId(Long id) {
        return changementPlanifierRepo.findByApplicationResponsableId(id);
    }

    public int deleteChangementPlanifierById(Long id) {
        int r1= contenuChangementService.deletePlanActionByChangementPlanifierId(id);
        int r2 = changementPlanifierRepo.deleteChangementPlanifierById(id);
        return r1 + r2;
    }

    public List<ChangementPlanifier> findAll() {
        return changementPlanifierRepo.findAll();
    }

    public  ChangementPlanifier save(ChangementPlanifier entity) throws Exception{
        if(entity != null) {
            ChangementPlanifier chg = new ChangementPlanifier();
            chg.setStatut(entity.getStatut());
            chg.setApplication(entity.getApplication());
            chg.setCreateurChangement(entity.getCreateurChangement());
            chg.setDateFin(entity.getDateFin());
            chg.setImpactMetier(entity.getImpactMetier());
            chg.setVersion(entity.getVersion());
            chg.setTitre(entity.getTitre());
            chg.setDateDebut(entity.getDateDebut());
            chg.setDetail(entity.getDetail());
            ChangementPlanifier chg1 = changementPlanifierRepo.save(chg);
            if(entity.getContenuChangementList() !=null){
                contenuChangementService.saveAllContent(chg1,entity.getContenuChangementList());
            }
            return chg1;
        }else {
            throw new Exception();
        }
    }
}
