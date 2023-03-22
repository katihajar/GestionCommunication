package com.example.PFEproject.service;

import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.bean.HealthChekPreprodProdDetail;
import com.example.PFEproject.repo.HealthChekPreprodProdDetailRepo;
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
public class HealthChekPreprodProdDetailService {
    @Autowired
    HealthChekPreprodProdDetailRepo healthChekPreprodProdDetailRepo;

    public List<HealthChekPreprodProdDetail> findByHealthChekPreprodProdId(Long id) {
        return healthChekPreprodProdDetailRepo.findByHealthChekPreprodProdId(id);
    }

    public int deleteHealthChekPreprodProdDetailById(Long id) {
        return healthChekPreprodProdDetailRepo.deleteHealthChekPreprodProdDetailById(id);
    }

    public int deleteByHealthChekPreprodProdId(Long id) {
        return healthChekPreprodProdDetailRepo.deleteByHealthChekPreprodProdId(id);
    }

    public  List<HealthChekPreprodProdDetail> saveAll(HealthChekPreprodProd in, List<HealthChekPreprodProdDetail> healthChekPreprodProdDetails) {
        List<HealthChekPreprodProdDetail> healthChekPreprodProdDetailList= new ArrayList<>();
        for (HealthChekPreprodProdDetail health : healthChekPreprodProdDetails) {
            HealthChekPreprodProdDetail healthcheck = new HealthChekPreprodProdDetail();
            healthcheck.setHealthChekPreprodProd(in);
            healthcheck.setApplication(health.getApplication());
            healthcheck.setFeu(health.getFeu());
            healthcheck.setCause(health.getCause());
            healthcheck.setDateFin(health.getDateFin());
            healthcheck.setDateDebut(health.getDateDebut());
            healthcheck.setInformation(health.getInformation());
            healthcheck.setImpactClient(health.getImpactClient());
            healthcheck.setImpactMetier(health.getImpactMetier());
            healthcheck.setProblemeTechnique(health.getProblemeTechnique());
            healthcheck.setPlanAction(health.getPlanAction());
            healthcheck.setStatut(health.getStatut());
            healthcheck.setProcessus(health.getProcessus());
            HealthChekPreprodProdDetail healthcheck2= healthChekPreprodProdDetailRepo.save(healthcheck);
            healthChekPreprodProdDetailList.add(healthcheck2);
        }
        return healthChekPreprodProdDetailList;
    }
}
