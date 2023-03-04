package com.example.PFEproject.service;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.bean.Role;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.repo.ApplicationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ApplicationService {
    @Autowired
    ApplicationRepo applicationRepo;

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
            return applicationRepo.save(application);
        }else {
            return app;
        }
    }
    public Application updateApp(Application application){
        Application app = findByNomApplication(application.getNomApplication());
        app.setNomApplication(application.getNomApplication());
        app.setCharteIncident(application.getCharteIncident());
        app.setDisponibilite(application.isDisponibilite());
        app.setVersion(application.getVersion());
        return applicationRepo.save(app);
    }
}
