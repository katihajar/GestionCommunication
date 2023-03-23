package com.example.PFEproject.service;

import com.example.PFEproject.bean.Perimetre;
import com.example.PFEproject.repo.PerimetreRepo;
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
public class PerimetreService {
    @Autowired
    PerimetreRepo perimetreRepo;

    public Perimetre findPerimetreById(Long id) {
        return perimetreRepo.findPerimetreById(id);
    }

    public Perimetre findByTitre(String titre) {
        return perimetreRepo.findByTitre(titre);
    }

    public List<Perimetre> findAll() {
        return perimetreRepo.findAll();
    }



    public  Perimetre save(Perimetre perimetre) throws Exception{
        Perimetre pmtr = findByTitre(perimetre.getTitre());
        if(pmtr == null) {
            return perimetreRepo.save(perimetre);
        }else {
            throw new Exception();
        }
    }

    public Perimetre update(Perimetre perimetre) throws Exception{
        Perimetre pmtr = findByTitre(perimetre.getTitre());
        System.out.println(perimetre.getId());
        Perimetre peri = findPerimetreById(perimetre.getId());
        if(pmtr == null || pmtr.getId() == peri.getId()){
            peri.setTitre(perimetre.getTitre());
            return perimetreRepo.save(peri);
        }else {
            throw new Exception();
        }
    }
}
