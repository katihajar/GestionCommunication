package com.example.PFEproject.service;

import com.example.PFEproject.bean.EtatProcessusMetier;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.bean.StatutApplication;
import com.example.PFEproject.repo.StatutApplicationRepo;
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
public class StatutApplicationService {
    @Autowired
    StatutApplicationRepo statutApplicationRepo;


    public List<StatutApplication> findByHealthChekPreprodProdId(Long id) {
        return statutApplicationRepo.findByHealthChekPreprodProdId(id);
    }

    public int deleteByHealthChekPreprodProdId(Long id) {
        return statutApplicationRepo.deleteByHealthChekPreprodProdId(id);
    }

    public int deleteStatutApplicationById(Long id) {
        return statutApplicationRepo.deleteStatutApplicationById(id);
    }
    public  List<StatutApplication> saveAllStatutApp(HealthChekPreprodProd in, List<StatutApplication> statutApplications) {
        List<StatutApplication> statutApplicationList= new ArrayList<>();
        for (StatutApplication statutApplication : statutApplications) {
            StatutApplication statut = new StatutApplication();
            statut.setHealthChekPreprodProd(in);
            statut.setApplication(statutApplication.getApplication());
            statut.setStatut(statutApplication.getStatut());
            StatutApplication statutApplication1= statutApplicationRepo.save(statut);
            statutApplicationList.add(statutApplication1);
        }
        return statutApplicationList;
    }
}
