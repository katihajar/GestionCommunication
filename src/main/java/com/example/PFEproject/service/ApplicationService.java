package com.example.PFEproject.service;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.repo.ApplicationRepo;
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
public class ApplicationService {
    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    PiloteApplicationService piloteApplicationService;



    public int deleteApplicationById(Long id) {
        return applicationRepo.deleteApplicationById(id);
    }

    public List<Application> findAll() {
        return applicationRepo.findAll();
    }

    public Application findByNomApplication(String nom) {
        return applicationRepo.findByNomApplication(nom);
    }
    public Application saveApp(Application application){
        Application app = findByNomApplication(application.getNomApplication());
        if (app == null) {
            Application appli = new Application();
            appli.setNomApplication(application.getNomApplication());
            appli.setCharteIncident(application.getCharteIncident());
            appli.setVersion(application.getVersion());
            appli.setLot(application.getLot());
            appli.setDisponibilite(application.getDisponibilite());
            appli.setResponsable(application.getResponsable());
            applicationRepo.save(appli);
            Application appliSave = findByNomApplication(application.getNomApplication());
            if(application.getPiloteApplicationList()!=null){
                piloteApplicationService.saveAll(appliSave, application.getPiloteApplicationList());
            }
            return appliSave;
        }else {
            return app;
        }
    }

    public Application updateApp(Application application){
        Application app = findByNomApplication(application.getNomApplication());
        app.setNomApplication(application.getNomApplication());
        app.setCharteIncident(application.getCharteIncident());
        app.setDisponibilite(application.getDisponibilite());
        app.setVersion(application.getVersion());
        return applicationRepo.save(app);
    }
}
