package com.example.PFEproject.service;

import com.example.PFEproject.bean.AvancementActionProbleme;
import com.example.PFEproject.bean.Probleme;
import com.example.PFEproject.repo.AvancementActionProblemeRepo;
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
public class AvancementActionProblemeService {

    @Autowired
    AvancementActionProblemeRepo avancementActionProblemeRepo;

    public List<AvancementActionProbleme> findByProblemeId(Long id) {
        return avancementActionProblemeRepo.findByProblemeId(id);
    }

    public int deleteAvancementActionProblemeById(Long id) {
        return avancementActionProblemeRepo.deleteAvancementActionProblemeById(id);
    }

    public int deleteByProblemeId(Long id) {
        return avancementActionProblemeRepo.deleteByProblemeId(id);
    }

    public List<AvancementActionProbleme> saveAllAction(Probleme in, List<AvancementActionProbleme> avancementActionProblemes) {
        List<AvancementActionProbleme> avancementActionProblemeList= new ArrayList<>();
        for (AvancementActionProbleme action : avancementActionProblemes) {
            AvancementActionProbleme act = new AvancementActionProbleme();
            AvancementActionProbleme action2 ;
            act.setProbleme(in);
            act.setTopic(action.getTopic());
            act.setUpdate(action.getUpdate());
            action2= avancementActionProblemeRepo.save(act);
            avancementActionProblemeList.add(action2);
        }
        return avancementActionProblemeList;
    }
}
