package com.example.PFEproject.service;

import com.example.PFEproject.bean.ProcessusMetier;
import com.example.PFEproject.repo.ProcessusMetierRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class ProcessusMetierService {
    @Autowired
    ProcessusMetierRepo processusMetierRepo;

    public ProcessusMetier findProcessusMetierById(Long id) {
        return processusMetierRepo.findProcessusMetierById(id);
    }

    public ProcessusMetier findByTitre(String titre) {
        return processusMetierRepo.findByTitre(titre);
    }

    public List<ProcessusMetier> findAll() {
        return processusMetierRepo.findAll();
    }

    public ProcessusMetier save(ProcessusMetier processusMetier) throws Exception {
        ProcessusMetier process = findByTitre(processusMetier.getTitre());
        if(process == null) {
            return processusMetierRepo.save(processusMetier);
        }else {
            throw new Exception();
        }
    }

    public ProcessusMetier update(ProcessusMetier processusMetier) throws Exception{
        ProcessusMetier processus1 = findByTitre(processusMetier.getTitre());
        ProcessusMetier proces = findProcessusMetierById(processusMetier.getId());
        if(processus1 == null || processus1.getId() == proces.getId()){
            proces.setTitre(processusMetier.getTitre());
            return processusMetierRepo.save(proces);
        }else {
            throw new Exception();
        }
    }
}
