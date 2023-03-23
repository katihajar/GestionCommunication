package com.example.PFEproject.service;

import com.example.PFEproject.bean.PlanningPointVersion;
import com.example.PFEproject.bean.PointVersion;
import com.example.PFEproject.repo.PlanningPointVersionRepo;
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
public class PlanningPointVersionService {
    @Autowired
    PlanningPointVersionRepo planningPointVersionRepo;

    public List<PlanningPointVersion> findByPointVersionId(Long id) {
        return planningPointVersionRepo.findByPointVersionId(id);
    }

    public int deletePlanningPointVersionById(Long id) {
        return planningPointVersionRepo.deletePlanningPointVersionById(id);
    }

    public int deleteByPointVersionId(Long id) {
        return planningPointVersionRepo.deleteByPointVersionId(id);
    }

    public  List<PlanningPointVersion> saveAll(PointVersion in, List<PlanningPointVersion> planningPointVersions) {
        List<PlanningPointVersion> planningPointVersionList= new ArrayList<>();
        for (PlanningPointVersion plan : planningPointVersions) {
            PlanningPointVersion planningPointVersion = new PlanningPointVersion();
            planningPointVersion.setPointVersion(in);
            planningPointVersion.setDescription(plan.getDescription());
            planningPointVersion.setTitre(plan.getTitre());
            PlanningPointVersion planningPointVersion2= planningPointVersionRepo.save(planningPointVersion);
            planningPointVersionList.add(planningPointVersion2);
        }
        return planningPointVersionList;
    }
}
