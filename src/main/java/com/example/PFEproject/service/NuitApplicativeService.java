package com.example.PFEproject.service;

import com.example.PFEproject.bean.NuitApplicative;
import com.example.PFEproject.repo.NuitApplicativeRepo;
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
public class NuitApplicativeService {
    @Autowired
    NuitApplicativeRepo nuitApplicativeRepo;
    @Autowired
    NbOccurenceService nbOccurenceService;
    @Autowired
    SuiviVolumetrieService suiviVolumetrieService;
    public List<NuitApplicative> findByCreateurNuitApplicativeLots(String lots) {
        return nuitApplicativeRepo.findByCreateurNuitApplicativeLots(lots);
    }

    public Page<NuitApplicative> findByCreateurNuitApplicativeLots(String lots, int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return nuitApplicativeRepo.findByCreateurNuitApplicativeLots(lots, pageable);
    }

    public int deleteNuitApplicativeById(Long id) {
        int r1= nbOccurenceService.deleteByNuitApplicativeId(id);
        int r2= suiviVolumetrieService.deleteByNuitApplicativeId(id);
        int r3= nuitApplicativeRepo.deleteNuitApplicativeById(id);
        return r1+r2+r3;
    }


    public NuitApplicative save(NuitApplicative entity) throws Exception{
        if(entity != null) {
            NuitApplicative nuit = new NuitApplicative();
            nuit.setTitre(entity.getTitre());
            nuit.setCreateurNuitApplicative(entity.getCreateurNuitApplicative());
            nuit.setDate(entity.getDate());
            nuit.setStatut(entity.getStatut());

            nuit.setDescChainesFacturation(entity.getDescChainesFacturation());
            nuit.setDescChainesPCOM(entity.getDescChainesPCOM());
            nuit.setDescChainesGRanalytics(entity.getDescChainesGRanalytics());
            nuit.setDescFocusEmail(entity.getDescFocusEmail());
            nuit.setDescChainesReferentiels(entity.getDescChainesReferentiels());
            nuit.setDescChainesTransactions(entity.getDescChainesTransactions());
            nuit.setDescChainesFacturation(entity.getDescChainesFacturation());

            nuit.setTypeChainesFacturation(entity.getTypeChainesFacturation());
            nuit.setTypeChainesPCOM(entity.getTypeChainesPCOM());
            nuit.setTypeChainesGRanalytics(entity.getTypeChainesGRanalytics());
            nuit.setTypeFocusEmail(entity.getTypeFocusEmail());
            nuit.setTypeChainesReferentiels(entity.getTypeChainesReferentiels());
            nuit.setTypeChainesTransactions(entity.getTypeChainesTransactions());
            nuit.setTypeChainesFacturation(entity.getTypeChainesFacturation());
            NuitApplicative nuit1 = nuitApplicativeRepo.save(nuit);
            if(entity.getNbOccurenceList() !=null){
                nbOccurenceService.saveAll(nuit1,entity.getNbOccurenceList());
            }
            if(entity.getSuiviVolumetrieList() !=null){
                suiviVolumetrieService.saveAll(nuit1,entity.getSuiviVolumetrieList());
            }

            return nuit1;
        }else {
            throw new Exception();
        }
    }
    public Page<NuitApplicative> searchNuit(String titre,String statut, Date date, String lot, Pageable pageable) {
        List<NuitApplicative> allnight = nuitApplicativeRepo.findByCreateurNuitApplicativeLots(lot);
        List<NuitApplicative> filteredNuit = allnight.stream()
                .filter(health -> {
                    boolean isMatched = true;
                    if (titre != null && !titre.isEmpty() && health.getTitre()!=null && !health.getTitre().contains(titre)) {
                        isMatched = false;
                    }

                    if (date != null && health.getDate() != null) {
                        LocalDate changeDateFin = health.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate inputDateFin = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        if (!changeDateFin.isEqual(inputDateFin)) {
                            isMatched = false;
                        }
                    }else if (date != null && health.getDate() == null) {
                        isMatched = false;
                    }

                    if (statut != null && !statut.isEmpty() && !health.getStatut().equals(statut)) {
                        isMatched = false;
                    }
                    return isMatched;
                })
                .sorted(Comparator.comparing(NuitApplicative::getDate).reversed())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<NuitApplicative> paginatedNuit;

        if (filteredNuit.size() < startItem) {
            paginatedNuit = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, filteredNuit.size());
            paginatedNuit = filteredNuit.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedNuit, pageable, filteredNuit.size());
    }

}
