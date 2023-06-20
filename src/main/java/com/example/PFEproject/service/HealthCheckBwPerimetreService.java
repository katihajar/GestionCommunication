package com.example.PFEproject.service;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.repo.HealthCheckBwPerimetreRepo;
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
public class HealthCheckBwPerimetreService {
    @Autowired
    HealthCheckBwPerimetreRepo healthCheckBwPerimetreRepo;
    @Autowired
    HealthCheckBwPerimetreDetailService healthCheckBwPerimetreDetailService;


    public List<HealthCheckBwPerimetre> findAll() {
        return healthCheckBwPerimetreRepo.findAll();
    }

    public List<HealthCheckBwPerimetre> findByCreateurHealthCheckBwPerimetreId(Long id) {
        return healthCheckBwPerimetreRepo.findByCreateurHealthCheckBwPerimetreId(id);
    }



    public int deleteHealthCheckBwPerimetreById(Long id) {
        int r1= healthCheckBwPerimetreDetailService.deleteByHealthCheckBwPerimetreId(id);
        int r2 =healthCheckBwPerimetreRepo.deleteHealthCheckBwPerimetreById(id);
        return r1+r2;
    }

    public List<HealthCheckBwPerimetre> findByCreateurHealthCheckBwPerimetreLot(String lot) {
        return healthCheckBwPerimetreRepo.findByCreateurHealthCheckBwPerimetreLots(lot);
    }

    public HealthCheckBwPerimetre save(HealthCheckBwPerimetre entity) throws Exception{
        if(entity != null) {
            HealthCheckBwPerimetre health = new HealthCheckBwPerimetre();
            health.setTitre(entity.getTitre());
            health.setCreateurHealthCheckBwPerimetre(entity.getCreateurHealthCheckBwPerimetre());
            health.setDateAjout(entity.getDateAjout());
            HealthCheckBwPerimetre health1 = healthCheckBwPerimetreRepo.save(health);
            if(entity.getHealthCheckBwPerimetreDetailList() !=null){
                healthCheckBwPerimetreDetailService.saveAll(health1,entity.getHealthCheckBwPerimetreDetailList());
            }
            return health1;
        }else {
            throw new Exception();
        }
    }
    public Page<HealthCheckBwPerimetre> findByCreateurHealthCheckBwPerimetreLots(String lots, int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateAjout");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return healthCheckBwPerimetreRepo.findByCreateurHealthCheckBwPerimetreLots(lots, pageable);
    }
    public Page<HealthCheckBwPerimetre> searchhealthBI(String titre, Date dateAjout, String lot, Pageable pageable) {
        List<HealthCheckBwPerimetre> allHealth = healthCheckBwPerimetreRepo.findByCreateurHealthCheckBwPerimetreLots(lot);
        List<HealthCheckBwPerimetre> filteredHealth = allHealth.stream()
                .filter(health -> {
                    boolean isMatched = true;
                    if (titre != null && !titre.isEmpty() && health.getTitre()!=null && !health.getTitre().contains(titre)) {
                        isMatched = false;
                    }

                    if (dateAjout != null && health.getDateAjout() != null) {
                        LocalDate changeDateFin = health.getDateAjout().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate inputDateFin = dateAjout.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        if (!changeDateFin.isEqual(inputDateFin)) {
                            isMatched = false;
                        }
                    }else if (dateAjout != null && health.getDateAjout() == null) {
                        isMatched = false;
                    }

                    return isMatched;
                })
                .sorted(Comparator.comparing(HealthCheckBwPerimetre::getDateAjout).reversed())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<HealthCheckBwPerimetre> paginatedHealth;

        if (filteredHealth.size() < startItem) {
            paginatedHealth = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, filteredHealth.size());
            paginatedHealth = filteredHealth.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedHealth, pageable, filteredHealth.size());
    }
}
