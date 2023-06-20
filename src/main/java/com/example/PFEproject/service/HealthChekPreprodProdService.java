package com.example.PFEproject.service;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.bean.HealthCheckFlamingo;
import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.dto.HealthChekPreprodProdDTO;
import com.example.PFEproject.repo.HealthChekPreprodProdRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class HealthChekPreprodProdService {
    @Autowired
    HealthChekPreprodProdRepo healthChekPreprodProdRepo;
    @Autowired
    HealthChekPreprodProdDetailService healthChekPreprodProdDetailService;
    @Autowired
    EtatProcessusMetierService etatProcessusMetierService;
    @Autowired
    StatutApplicationService statutApplicationService;
    public List<HealthChekPreprodProd> findAll() {
        return healthChekPreprodProdRepo.findAll();
    }


    public List<HealthChekPreprodProdDTO> getLast10Added(String lot) {
        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        List<HealthChekPreprodProd> healthChekPreprodProdList = healthChekPreprodProdRepo.findFirst10ByCreateurHealthChekPreprodProdLotsAndDateAjoutBeforeOrderByDateAjoutDesc(lot,date);
        return healthChekPreprodProdList.stream()
                .map(h -> new HealthChekPreprodProdDTO(h.getId(), h.getTitre(), h.getDateAjout(), h.getType(), h.getEtatProcessusMetierList()))
                .collect(Collectors.toList());
    }
    public List<HealthChekPreprodProdDTO> getHistorique(String lot) {
        // Calculate the date 10 days before today
        LocalDate today = LocalDate.now();
        LocalDate tenDaysBefore = today.minusDays(10);

        // Convert LocalDate to Date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date tenDaysBeforeDate = Date.from(tenDaysBefore.atStartOfDay(defaultZoneId).toInstant());
        Date todayDate = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
        // Fetch the health checks within the specified date range
        List<HealthChekPreprodProd> healthChecks = healthChekPreprodProdRepo.findByCreateurHealthChekPreprodProdLotsAndDateAjoutBetweenOrderByDateAjoutDesc(lot, tenDaysBeforeDate, todayDate);
        // Return the retrieved health checks
        return healthChecks.stream()
                .map(h -> new HealthChekPreprodProdDTO(h.getId(), h.getTitre(), h.getDateAjout(), h.getType(), h.getEtatProcessusMetierList()))
                .collect(Collectors.toList());
    }
    public List<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdId(Long id) {
        return healthChekPreprodProdRepo.findByCreateurHealthChekPreprodProdId(id);
    }


    public List<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdLot(String lot) {
        return healthChekPreprodProdRepo.findByCreateurHealthChekPreprodProdLots(lot);
    }

    public int deleteHealthChekPreprodProdById(Long id) {
        int r1 = etatProcessusMetierService.deleteByHealthChekPreprodProdId(id);
        int r2 = healthChekPreprodProdDetailService.deleteByHealthChekPreprodProdId(id);
        int r4 =statutApplicationService.deleteByHealthChekPreprodProdId(id);
        int r3 =healthChekPreprodProdRepo.deleteHealthChekPreprodProdById(id);
        return r1+r2+r4+r3;
    }

    public HealthChekPreprodProd save(HealthChekPreprodProd entity) throws Exception{
        if(entity != null) {
            HealthChekPreprodProd health = new HealthChekPreprodProd();
            health.setTitre(entity.getTitre());
            health.setType(entity.getType());
            health.setDateAjout(entity.getDateAjout());
            health.setCreateurHealthChekPreprodProd(entity.getCreateurHealthChekPreprodProd());
            HealthChekPreprodProd health1 = healthChekPreprodProdRepo.save(health);
            if(entity.getHealthChekPreprodProdDetailList() !=null){
                healthChekPreprodProdDetailService.saveAll(health1,entity.getHealthChekPreprodProdDetailList());
            }
            if(entity.getEtatProcessusMetierList() !=null){
                etatProcessusMetierService.saveAllEtatProcessus(health1,entity.getEtatProcessusMetierList());
            }
            if(entity.getStatutApplicationList() !=null){
                statutApplicationService.saveAllStatutApp(health1,entity.getStatutApplicationList());
            }
            return health1;
        }else {
            throw new Exception();
        }
    }
    public Page<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdLots(String lots, int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateAjout");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return healthChekPreprodProdRepo.findByCreateurHealthChekPreprodProdLots(lots, pageable);
    }
    public Page<HealthChekPreprodProd> searchhealthMonetics(String titre,String type,Date dateAjout, String lot, Pageable pageable) {
        List<HealthChekPreprodProd> allHealth = healthChekPreprodProdRepo.findByCreateurHealthChekPreprodProdLots(lot);
        List<HealthChekPreprodProd> filteredHealth = allHealth.stream()
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
                    if (type != null && !type.isEmpty() && !health.getType().equals(type)) {
                        isMatched = false;
                    }

                    return isMatched;
                })
                .sorted(Comparator.comparing(HealthChekPreprodProd::getDateAjout).reversed())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<HealthChekPreprodProd> paginatedHealth;

        if (filteredHealth.size() < startItem) {
            paginatedHealth = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, filteredHealth.size());
            paginatedHealth = filteredHealth.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedHealth, pageable, filteredHealth.size());
    }
}
