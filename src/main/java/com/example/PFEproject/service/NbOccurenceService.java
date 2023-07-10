package com.example.PFEproject.service;

import com.example.PFEproject.bean.NbOccurence;
import com.example.PFEproject.bean.NuitApplicative;
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
public class NbOccurenceService {

    @Autowired
    NbOccurenceRepo nbOccurenceRepo;

    public List<NbOccurence> findByNuitApplicativeId(Long id) {
        return nbOccurenceRepo.findByNuitApplicativeId(id);
    }

    public int deleteNbOccurenceById(Long id) {
        return nbOccurenceRepo.deleteNbOccurenceById(id);
    }

    public int deleteByNuitApplicativeId(Long id) {
        return nbOccurenceRepo.deleteByNuitApplicativeId(id);
    }

    public List<NbOccurence> saveAll(NuitApplicative in, List<NbOccurence> nbOccurences) {
        List<NbOccurence> nbOccurenceList= new ArrayList<>();
        for (NbOccurence ocurence : nbOccurences) {
            NbOccurence ocur = new NbOccurence();
            NbOccurence ocur2 ;
            ocur.setNuitApplicative(in);
            ocur.setStatut(ocurence.getStatut());
            ocur.setDate(ocurence.getDate());
            ocur.setTraitement(ocurence.getTraitement());
            ocur.setNombreOcurrence(ocurence.getNombreOcurrence());
            ocur2= nbOccurenceRepo.save(ocur);
            nbOccurenceList.add(ocur2);
        }
        return nbOccurenceList;
    }
}
