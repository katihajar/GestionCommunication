package com.example.PFEproject.service;

import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.Probleme;
import com.example.PFEproject.repo.ProblemeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ProblemeService {
    @Autowired
    ProblemeRepo problemeRepo;
    @Autowired
    AvancementActionProblemeService avancementActionProblemeService;

    public List<Probleme> findByApplicationLot(String lots) {
        return problemeRepo.findByApplicationLot(lots);
    }

    public Page<Probleme> findByApplicationLot(String lots, int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateAjout");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return problemeRepo.findByApplicationLot(lots, pageable);
    }

    public int deleteProblemeById(Long id) {
        int r1 = avancementActionProblemeService.deleteByProblemeId(id);
        int r2 = problemeRepo.deleteProblemeById(id);
        return r1+ r2;
    }

    public List<Probleme> findByApplicationResponsableId(Long id) {
        return problemeRepo.findByApplicationResponsableId(id);
    }
    public Probleme save(Probleme prbl) throws Exception{
        if(prbl != null) {
            Probleme prblSave= new Probleme();
            prblSave.setAnanlyse(prbl.getAnanlyse());
            prblSave.setApplication(prbl.getApplication());
            prblSave.setDescription(prbl.getDescription());
            prblSave.setTopic(prbl.getTopic());
            prblSave.setDescription(prbl.getDescription());
            prblSave.setStatut(prbl.getStatut());
            prblSave.setDateAjout(prbl.getDateAjout());
            prblSave.setCreateurProbleme(prbl.getCreateurProbleme());
            Probleme prbl1 = problemeRepo.save(prblSave);

            if (prbl.getAvancementActionProbleme() != null) {
                avancementActionProblemeService.saveAllAction(prbl1, prbl.getAvancementActionProbleme());
            }
            return prbl1;
        }else {
            throw new Exception();
        }
    }
    public Page<Probleme> searchProb(String topic, Date dateAjout, String statut, long id, String desc, String lot, Pageable pageable) {
        List<Probleme> allProblemes = problemeRepo.findByApplicationLot(lot);
        List<Probleme> filteredProblemes = allProblemes.stream()
                .filter(probl -> {
                    boolean isMatched = true;

                    if (topic != null && !topic.isEmpty() && probl.getTopic() != null && !probl.getTopic().contains(topic)) {
                        isMatched = false;
                    }

                    if (desc != null && !desc.isEmpty() && probl.getDescription() != null && !probl.getDescription().contains(desc)) {
                        isMatched = false;
                    }

                    if (dateAjout != null && probl.getDateAjout() != null) {
                        LocalDate probldate = probl.getDateAjout().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate inputdate = dateAjout.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        if (!probldate.isEqual(inputdate)) {
                            isMatched = false;
                        }
                    }else if (dateAjout != null && probl.getDateAjout() == null) {
                        isMatched = false;
                    }


                    if (statut != null && !statut.isEmpty() && probl.getStatut() != null && !probl.getStatut().equals(statut)) {
                        isMatched = false;
                    }

                    if (id != 0 && probl.getApplication() != null && !probl.getApplication().getId().equals(id)) {
                        isMatched = false;
                    }

                    return isMatched;
                })
                .sorted(Comparator.comparing(Probleme::getDateAjout).reversed())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Probleme> paginatedProbl;

        if (filteredProblemes.size() < startItem) {
            paginatedProbl = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, filteredProblemes.size());
            paginatedProbl = filteredProblemes.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedProbl, pageable, filteredProblemes.size());
    }

}
