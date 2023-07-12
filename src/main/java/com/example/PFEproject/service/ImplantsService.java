package com.example.PFEproject.service;

import com.example.PFEproject.bean.Implants;
import com.example.PFEproject.bean.MonitoringMstoolbox;
import com.example.PFEproject.bean.NbOccurence;
import com.example.PFEproject.bean.NuitApplicative;
import com.example.PFEproject.repo.ImplantsRepo;
import com.example.PFEproject.repo.NbOccurenceRepo;
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
public class ImplantsService {
    @Autowired
    ImplantsRepo implantsRepo;

    public List<Implants> findByMstoolboxId(Long id) {
        return implantsRepo.findByMstoolboxId(id);
    }

    public int deleteByMstoolboxId(Long id) {
        return implantsRepo.deleteByMstoolboxId(id);
    }

    public List<Implants> findAll() {
        return implantsRepo.findAll();
    }

    public List<Implants> saveAll(MonitoringMstoolbox monitoring, List<Implants> implants) {
        List<Implants> implantsList= new ArrayList<>();
        for (Implants implant : implants) {
            Implants imp = new Implants();
            Implants imp2 ;
            imp.setMstoolbox(monitoring);
            imp.setNumeroImplants(implant.getNumeroImplants());
            imp2= implantsRepo.save(imp);
            implantsList.add(imp2);
        }
        return implantsList;
    }
}
