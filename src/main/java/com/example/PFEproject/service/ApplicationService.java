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

    public List<Application> findApplicationByLots(String lots) {
        return applicationRepo.findApplicationByLot(lots);
    }

    public List<Application> findByResponsableId(Long id) {
        return applicationRepo.findByResponsableId(id);
    }

    public Application findApplicationById(Long id) {
        return applicationRepo.findApplicationById(id);
    }

    public int deleteApplicationById(Long id) {
        return applicationRepo.deleteApplicationById(id);
    }

    public List<Application> findAll() {
        return applicationRepo.findAll();
    }

    public Application findByNomApplication(String nom) {
        return applicationRepo.findByNomApplication(nom);
    }
    public Application saveApp(Application application) throws Exception {
        Application app = findByNomApplication(application.getNomApplication());
        if (app == null) {
            if (application.getNomApplication() != "" ) {
                Application appli = new Application();
                appli.setNomApplication(application.getNomApplication());
                appli.setCharteIncident(application.getCharteIncident());
                appli.setLot(application.getLot());
                appli.setDisponibilite(application.getDisponibilite());
                appli.setResponsable(application.getResponsable());
                appli.setCharteChangement(application.getCharteChangement());
                applicationRepo.save(appli);
                Application appliSave = findByNomApplication(application.getNomApplication());
                if (application.getPiloteApplicationList() != null) {
                    piloteApplicationService.saveAll(appliSave, application.getPiloteApplicationList());
                }
                return appliSave;
            }else {
                throw new Exception();

            }
        }else {
            throw new Exception();
        }
    }

    public Application updateApp(Application application){
        Application app = findApplicationById(application.getId());
        app.setNomApplication(application.getNomApplication());
        app.setCharteIncident(application.getCharteIncident());
        app.setCharteChangement(application.getCharteChangement());
        app.setDisponibilite(application.getDisponibilite());
        return applicationRepo.save(app);
    }
}
