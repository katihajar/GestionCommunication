package com.example.PFEproject.service;

import com.example.PFEproject.bean.MonitoringOptirenta;
import com.example.PFEproject.repo.MonitoringOptirentaRepo;
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
public class MonitoringOptirentaService {
    @Autowired
    MonitoringOptirentaRepo monitoringOptirentaRepo;
    @Autowired
    FluxOptirentaService fluxOptirentaService;

    public List<MonitoringOptirenta> findByCreateurMonitoringLots(String lots) {
        return monitoringOptirentaRepo.findByCreateurMonitoringLots(lots);
    }

    public Page<MonitoringOptirenta> findByCreateurMonitoringLots(String lots, int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateAjout");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return monitoringOptirentaRepo.findByCreateurMonitoringLots(lots, pageable);
    }

    public int deleteMonitoringOptirentaById(Long id) {
        int r1= fluxOptirentaService.deleteByOptirentaId(id);
        int r2= monitoringOptirentaRepo.deleteMonitoringOptirentaById(id);
        return r1+r2;
    }
    public MonitoringOptirenta save(MonitoringOptirenta entity) throws Exception{
        if(entity != null) {
            MonitoringOptirenta monitoring = new MonitoringOptirenta();
            monitoring.setTitre(entity.getTitre());
            monitoring.setCreateurMonitoring(entity.getCreateurMonitoring());
            monitoring.setDateAjout(entity.getDateAjout());
            MonitoringOptirenta monitoring1 = monitoringOptirentaRepo.save(monitoring);
            if(entity.getFluxOptirentaList() !=null){
                fluxOptirentaService.saveAll(monitoring1,entity.getFluxOptirentaList());
            }
            return monitoring1;
        }else {
            throw new Exception();
        }
    }
    public Page<MonitoringOptirenta> search(String titre, Date date, String lot, Pageable pageable) {
        List<MonitoringOptirenta> allmonitoring = monitoringOptirentaRepo.findByCreateurMonitoringLots(lot);
        List<MonitoringOptirenta> filteredMonitorig = allmonitoring.stream()
                .filter(monitoring -> {
                    boolean isMatched = true;
                    if (titre != null && !titre.isEmpty() && monitoring.getTitre()!=null && !monitoring.getTitre().contains(titre)) {
                        isMatched = false;
                    }

                    if (date != null && monitoring.getDateAjout() != null) {
                        LocalDate changeDateFin = monitoring.getDateAjout().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate inputDateFin = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        if (!changeDateFin.isEqual(inputDateFin)) {
                            isMatched = false;
                        }
                    }else if (date != null && monitoring.getDateAjout() == null) {
                        isMatched = false;
                    }


                    return isMatched;
                })
                .sorted(Comparator.comparing(MonitoringOptirenta::getDateAjout).reversed())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<MonitoringOptirenta> paginatedMonitoring;

        if (filteredMonitorig.size() < startItem) {
            paginatedMonitoring = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, filteredMonitorig.size());
            paginatedMonitoring = filteredMonitorig.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedMonitoring, pageable, filteredMonitorig.size());
    }
}
