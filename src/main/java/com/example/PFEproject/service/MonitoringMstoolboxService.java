package com.example.PFEproject.service;

import com.example.PFEproject.bean.MonitoringMstoolbox;
import com.example.PFEproject.bean.MonitoringOptirenta;
import com.example.PFEproject.bean.NuitApplicative;
import com.example.PFEproject.repo.MonitoringMstoolboxRepo;
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
public class MonitoringMstoolboxService {
    @Autowired
    MonitoringMstoolboxRepo monitoringMstoolboxRepo;
    @Autowired
    ImplantsService implantsService;
    @Autowired
    TransactionHandbidService transactionHandbidService;
    @Autowired
    TransactionSmileService transactionSmileService;
    public List<MonitoringMstoolbox> findByCreateurMonitoringLots(String lots) {
        return monitoringMstoolboxRepo.findByCreateurMonitoringLots(lots);
    }

    public Page<MonitoringMstoolbox> findByCreateurMonitoringLots(String lots, int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateAjout");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return monitoringMstoolboxRepo.findByCreateurMonitoringLots(lots, pageable);
    }

    public int deleteMonitoringMstoolboxById(Long id) {
        int r1= implantsService.deleteByMstoolboxId(id);
        int r2= transactionHandbidService.deleteByMstoolboxId(id);
        int r3= transactionSmileService.deleteByMstoolboxId(id);
        int r4= monitoringMstoolboxRepo.deleteMonitoringMstoolboxById(id);
        return r1+r2+r3+r4;
    }

    public MonitoringMstoolbox save(MonitoringMstoolbox entity) throws Exception{
        if(entity != null) {
            MonitoringMstoolbox monitoring = new MonitoringMstoolbox();
            monitoring.setTitre(entity.getTitre());
            monitoring.setCreateurMonitoring(entity.getCreateurMonitoring());
            monitoring.setDateImplants(entity.getDateImplants());
            monitoring.setDateAjout(entity.getDateAjout());
            MonitoringMstoolbox monitoring1 = monitoringMstoolboxRepo.save(monitoring);
            if(entity.getImplantsList() !=null){
                implantsService.saveAll(monitoring1,entity.getImplantsList());
            }
            if(entity.getTransactionHandbidList() !=null){
                transactionHandbidService.saveAll(monitoring1,entity.getTransactionHandbidList());
            }
            if(entity.getTransactionSmileList() !=null){
                transactionSmileService.saveAll(monitoring1,entity.getTransactionSmileList());
            }
            return monitoring1;
        }else {
            throw new Exception();
        }
    }
    public Page<MonitoringMstoolbox> search(String titre, Date date, String lot, Pageable pageable) {
        List<MonitoringMstoolbox> allmonitoring = monitoringMstoolboxRepo.findByCreateurMonitoringLots(lot);
        List<MonitoringMstoolbox> filteredMonitorig = allmonitoring.stream()
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
                .sorted(Comparator.comparing(MonitoringMstoolbox::getDateAjout).reversed())
                .collect(Collectors.toList());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<MonitoringMstoolbox> paginatedMonitoring;

        if (filteredMonitorig.size() < startItem) {
            paginatedMonitoring = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, filteredMonitorig.size());
            paginatedMonitoring = filteredMonitorig.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedMonitoring, pageable, filteredMonitorig.size());
    }
}
