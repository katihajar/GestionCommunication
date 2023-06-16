package com.example.PFEproject.service;

import com.example.PFEproject.bean.ChangementPlanifier;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.repo.ChangementPlanifierRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;
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
public class ChangementPlanifierService {
    @Autowired
    ChangementPlanifierRepo changementPlanifierRepo;

    @Autowired
    ContenuChangementService contenuChangementService;

    public List<ChangementPlanifier> findByCreateurChangementId(Long id) {
        return changementPlanifierRepo.findByCreateurChangementId(id);
    }

    public List<ChangementPlanifier> findByApplicationLot(String lots) {
        return changementPlanifierRepo.findByApplicationLot(lots);
    }

    public List<ChangementPlanifier> findByApplicationResponsableId(Long id) {
        return changementPlanifierRepo.findByApplicationResponsableId(id);
    }

    public int deleteChangementPlanifierById(Long id) {
        int r1= contenuChangementService.deletePlanActionByChangementPlanifierId(id);
        int r2 = changementPlanifierRepo.deleteChangementPlanifierById(id);
        return r1 + r2;
    }

    public List<ChangementPlanifier> findAll() {
        return changementPlanifierRepo.findAll();
    }

    public  ChangementPlanifier save(ChangementPlanifier entity) throws Exception{
        if(entity != null) {
            ChangementPlanifier chg = new ChangementPlanifier();
            chg.setStatut(entity.getStatut());
            chg.setApplication(entity.getApplication());
            chg.setCreateurChangement(entity.getCreateurChangement());
            chg.setDateFin(entity.getDateFin());
            chg.setImpactMetier(entity.getImpactMetier());
            chg.setVersion(entity.getVersion());
            chg.setTitre(entity.getTitre());
            chg.setDateDebut(entity.getDateDebut());
            chg.setDetail(entity.getDetail());
            chg.setDateAjout(entity.getDateAjout());
            chg.setType(entity.getType());
            ChangementPlanifier chg1 = changementPlanifierRepo.save(chg);
            if(entity.getContenuChangementList() !=null){
                contenuChangementService.saveAllContent(chg1,entity.getContenuChangementList());
            }
            return chg1;
        }else {
            throw new Exception();
        }
    }
    public Page<ChangementPlanifier> findByApplicationLot(String lots, int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateAjout");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return changementPlanifierRepo.findByApplicationLot(lots, pageable);
    }
    public Page<ChangementPlanifier> searchChanges(String titre, Date dateDebut, Date dateFin, String statut, long id, String vers, String lot, Pageable pageable) {
        List<ChangementPlanifier> allChange = changementPlanifierRepo.findByApplicationLot(lot);
        System.out.println("deb "+dateDebut);
        System.out.println("fin "+dateFin);
        List<ChangementPlanifier> filteredChanges = allChange.stream()
                .filter(change -> {
                    boolean isMatched = true;
                    if (titre != null && !titre.isEmpty() && !change.getTitre().contains(titre)) {
                        isMatched = false;
                    }

                    if (vers != null && !vers.isEmpty() && !change.getVersion().equals(vers)) {
                        isMatched = false;
                    }

                    if (dateDebut != null) {
                        LocalDate changedateDebut= change.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate inputdateDebut = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        if (!changedateDebut.isEqual(inputdateDebut)) {
                            isMatched = false;
                        }
                    }
                    if (dateFin != null) {
                        LocalDate changeDateFin = change.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate inputDateFin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        if (!changeDateFin.isEqual(inputDateFin)) {
                            isMatched = false;
                        }
                    }

                    if (statut != null && !statut.isEmpty() && !change.getStatut().equals(statut)) {
                        isMatched = false;
                    }

                    if (id != 0 && !change.getApplication().getId().equals(id)) {
                        isMatched = false;
                    }

                    return isMatched;
                })
                .sorted(Comparator.comparing(ChangementPlanifier::getDateAjout).reversed())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ChangementPlanifier> paginatedChanges;

        if (filteredChanges.size() < startItem) {
            paginatedChanges = Collections.emptyList(); // Return an empty list if startItem exceeds the filteredChanges size
        } else {
            int toIndex = Math.min(startItem + pageSize, filteredChanges.size());
            paginatedChanges = filteredChanges.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedChanges, pageable, filteredChanges.size());
    }
}
