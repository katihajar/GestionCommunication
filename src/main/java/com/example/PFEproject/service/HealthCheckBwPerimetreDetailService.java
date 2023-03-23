package com.example.PFEproject.service;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.bean.HealthCheckBwPerimetreDetail;
import com.example.PFEproject.repo.HealthCheckBwPerimetreDetailRepo;
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
public class HealthCheckBwPerimetreDetailService {
    @Autowired
    HealthCheckBwPerimetreDetailRepo healthCheckBwPerimetreDetailRepo;

    public List<HealthCheckBwPerimetreDetail> findByHealthCheckBwPerimetreId(Long id) {
        return healthCheckBwPerimetreDetailRepo.findByHealthCheckBwPerimetreId(id);
    }

    public int deleteHealthCheckBwPerimetreDetailById(Long id) {
        return healthCheckBwPerimetreDetailRepo.deleteHealthCheckBwPerimetreDetailById(id);
    }

    public int deleteByHealthCheckBwPerimetreId(Long id) {
        return healthCheckBwPerimetreDetailRepo.deleteByHealthCheckBwPerimetreId(id);
    }
    public  List<HealthCheckBwPerimetreDetail> saveAll(HealthCheckBwPerimetre in, List<HealthCheckBwPerimetreDetail> healthChekPreprodProdDetails) {
        List<HealthCheckBwPerimetreDetail> healthCheckBwPerimetreDetailList= new ArrayList<>();
        for (HealthCheckBwPerimetreDetail health : healthChekPreprodProdDetails) {
            HealthCheckBwPerimetreDetail healthcheck = new HealthCheckBwPerimetreDetail();
            healthcheck.setHealthCheckBwPerimetre(in);
            healthcheck.setPerimetre(health.getPerimetre());
            healthcheck.setComment(health.getComment());
            healthcheck.setFeedBack(health.getFeedBack());
            healthcheck.setIncidentCompleted(health.getIncidentCompleted());
            healthcheck.setIncidentInProgress(health.getIncidentInProgress());
            healthcheck.setStatusDataIntegrity(health.getStatusDataIntegrity());
            healthcheck.setStatusNightTreatment(health.getStatusNightTreatment());
            HealthCheckBwPerimetreDetail healthcheck2= healthCheckBwPerimetreDetailRepo.save(healthcheck);
            healthCheckBwPerimetreDetailList.add(healthcheck2);
        }
        return healthCheckBwPerimetreDetailList;
    }
}
