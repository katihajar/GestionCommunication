package com.example.PFEproject.service;

import com.example.PFEproject.bean.HealthCheckBwPerimetre;
import com.example.PFEproject.repo.HealthCheckBwPerimetreRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
