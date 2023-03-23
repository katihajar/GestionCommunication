package com.example.PFEproject.service;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.bean.PiloteApplication;
import com.example.PFEproject.repo.ApplicationRepo;
import com.example.PFEproject.repo.PiloteApplicationRepo;
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
public class PiloteApplicationService {

    @Autowired
    PiloteApplicationRepo piloteApplicationRepo;


    @Autowired
    ApplicationRepo applicationRepo;
    public PiloteApplication findByPiloteUsernameAndApplicationNomApplication(String username, String nomApp) {
        return piloteApplicationRepo.findByPiloteUsernameAndApplicationNomApplication(username, nomApp);
    }

    public List<PiloteApplication> findByApplicationNomApplication(String nomApp) {
        return piloteApplicationRepo.findByApplicationNomApplication(nomApp);
    }

    public List<PiloteApplication> findByPiloteUsername(String username) {
        return piloteApplicationRepo.findByPiloteUsername(username);
    }

    public List<PiloteApplication> saveAll(Application app, List<PiloteApplication> piloteApplications) {
        List<PiloteApplication> piloteApplicationList = new ArrayList<>();
        for (PiloteApplication pilote : piloteApplications) {
            PiloteApplication pilote2 ;
            pilote.setApplication(app);
            pilote2= piloteApplicationRepo.save(pilote);
            piloteApplicationList.add(pilote2);
        }
        return piloteApplicationList;
    }

    public PiloteApplication savePiloteApp(PiloteApplication pilote) throws Exception {
        PiloteApplication p = findByPiloteUsernameAndApplicationNomApplication(pilote.getPilote().getUsername(),pilote.getApplication().getNomApplication());
        if (p==null){
            piloteApplicationRepo.save(pilote);
            PiloteApplication pp = findByPiloteUsernameAndApplicationNomApplication(pilote.getPilote().getUsername(),pilote.getApplication().getNomApplication());
            Application app = applicationRepo.findByNomApplication(pilote.getApplication().getNomApplication());
            applicationRepo.save(app);
            app.getPiloteApplicationList().add(pp);
            return pilote;
        }else {
            throw new Exception();
        }
    }
}
