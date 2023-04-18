package com.example.PFEproject.service;

import com.example.PFEproject.bean.HealthChekPreprodProd;
import com.example.PFEproject.dto.HealthChekPreprodProdDTO;
import com.example.PFEproject.repo.HealthChekPreprodProdRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
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


    public List<HealthChekPreprodProdDTO> getLast10Added() {
        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        List<HealthChekPreprodProd> healthChekPreprodProdList = healthChekPreprodProdRepo.findFirst10ByDateAjoutBeforeOrderByDateAjoutDesc(date);
        return healthChekPreprodProdList.stream()
                .map(h -> new HealthChekPreprodProdDTO(h.getId(), h.getTitre(), h.getDateAjout(), h.getType(), h.getEtatProcessusMetierList()))
                .collect(Collectors.toList());
    }

    public List<HealthChekPreprodProd> findByCreateurHealthChekPreprodProdId(Long id) {
        return healthChekPreprodProdRepo.findByCreateurHealthChekPreprodProdId(id);
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
}
