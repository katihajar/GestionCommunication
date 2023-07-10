package com.example.PFEproject.service;

import com.example.PFEproject.bean.NuitApplicative;
import com.example.PFEproject.bean.SuiviVolumetrie;
import com.example.PFEproject.repo.SuiviVolumetrieRepo;
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
public class SuiviVolumetrieService {
    @Autowired
    SuiviVolumetrieRepo suiviVolumetrieRepo;

    public List<SuiviVolumetrie> findByNuitApplicativeId(Long id) {
        return suiviVolumetrieRepo.findByNuitApplicativeId(id);
    }

    public int deleteSuiviVolumetrieById(Long id) {
        return suiviVolumetrieRepo.deleteSuiviVolumetrieById(id);
    }

    public int deleteByNuitApplicativeId(Long id) {
        return suiviVolumetrieRepo.deleteByNuitApplicativeId(id);
    }

    public List<SuiviVolumetrie> saveAll(NuitApplicative in, List<SuiviVolumetrie> suiviVolumetries) {
        List<SuiviVolumetrie> suiviVolumetrieList= new ArrayList<>();
        for (SuiviVolumetrie action : suiviVolumetries) {
            SuiviVolumetrie act = new SuiviVolumetrie();
            SuiviVolumetrie action2 ;
            act.setNuitApplicative(in);
            act.setStatut(action.getStatut());
            act.setNbActuel(action.getNbActuel());
            act.setTypeAlerte(action.getTypeAlerte());
            act.setNbMinimun(action.getNbMinimun());
            action2= suiviVolumetrieRepo.save(act);
            suiviVolumetrieList.add(action2);
        }
        return suiviVolumetrieList;
    }
}
