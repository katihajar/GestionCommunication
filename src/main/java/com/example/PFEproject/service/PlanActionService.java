package com.example.PFEproject.service;

import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.PlanAction;
import com.example.PFEproject.repo.PlanActionRepo;
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
public class PlanActionService {
    @Autowired
    PlanActionRepo planActionRepo;

    public List<PlanAction> findByIncidentId(Long id) {
        return planActionRepo.findByIncidentId(id);
    }

    public int deletePlanActionByIncidentId(Long id) {
        return planActionRepo.deletePlanActionByIncidentId(id);
    }

    public List<PlanAction> findAll() {
        return planActionRepo.findAll();
    }

    public  List<PlanAction> saveAllAction(Incident in, List<PlanAction> planActions) {
        List<PlanAction> PlanActionList= new ArrayList<>();
        System.out.println("Plan save");
        for (PlanAction action : planActions) {
            System.out.println("Plan for");
            PlanAction act = new PlanAction();
            PlanAction action2 ;
            act.setIncident(in);
            act.setNumero(action.getNumero());
            act.setDescription(action.getDescription());
            act.setStatut(action.getStatut());
            action2= planActionRepo.save(act);
            PlanActionList.add(action2);
        }
        return PlanActionList;
    }
}
