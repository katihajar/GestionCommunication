package com.example.PFEproject.service;

import com.example.PFEproject.bean.HealthCheckFlamingo;
import com.example.PFEproject.bean.PointVersion;
import com.example.PFEproject.repo.HealthCheckFlamingoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class HealthCheckFlamingoService {
    @Autowired
    HealthCheckFlamingoRepo healthCheckFlamingoRepo;
    @Autowired
    FluxEAIService fluxEAIService;
    @Autowired
    FluxSalesOrderService fluxSalesOrderService;
    @Autowired
    FluxSapEuropeService fluxSapEuropeService;
    @Autowired
    FluxSapHarmonieService fluxSapHarmonieService;
    public List<HealthCheckFlamingo> findByCreateurHealthCheckFlamingoLots(String lot) {
        return healthCheckFlamingoRepo.findByCreateurHealthCheckFlamingoLots(lot);
    }

    public int deleteHealthCheckFlamingoById(Long id) {
        int r1= fluxEAIService.deleteByHealthCheckFlamingoId(id);
        int r2= fluxSalesOrderService.deleteByHealthCheckFlamingoId(id);
        int r3= fluxSapEuropeService.deleteByHealthCheckFlamingoId(id);
        int r4= fluxSapHarmonieService.deleteByHealthCheckFlamingoId(id);
        int r5=healthCheckFlamingoRepo.deleteHealthCheckFlamingoById(id);
        return r1+r2+r3+r4+r5;
    }
    public HealthCheckFlamingo save(HealthCheckFlamingo healthCheckFlamingo) throws Exception{
        if(healthCheckFlamingo != null) {
            HealthCheckFlamingo health = new HealthCheckFlamingo();
            health.setCreateurHealthCheckFlamingo(healthCheckFlamingo.getCreateurHealthCheckFlamingo());
            health.setRemarque(healthCheckFlamingo.getRemarque());
            health.setDateAjout(healthCheckFlamingo.getDateAjout());
            health.setDateFlux(healthCheckFlamingo.getDateFlux());
            health.setTitre(healthCheckFlamingo.getTitre());

            HealthCheckFlamingo healthCheck = healthCheckFlamingoRepo.save(health);
            if(healthCheckFlamingo.getFluxEAIList() !=null){
                fluxEAIService.saveAll(healthCheck,healthCheckFlamingo.getFluxEAIList());
            }else{
                System.out.println("aucun flux ajouter");
            }
            if(healthCheckFlamingo.getFluxSalesOrderList() !=null){
                fluxSalesOrderService.saveAll(healthCheck,healthCheckFlamingo.getFluxSalesOrderList());
            }else{
                System.out.println("aucun Ticket Ajouter");
            }
            if(healthCheckFlamingo.getFluxSapEuropeList() !=null){
                fluxSapEuropeService.saveAll(healthCheck,healthCheckFlamingo.getFluxSapEuropeList());
            }else{
                System.out.println("aucun planning Ajouter");
            }
            if(healthCheckFlamingo.getFluxSapHarmonies() !=null){
                fluxSapHarmonieService.saveAll(healthCheck,healthCheckFlamingo.getFluxSapHarmonies());
            }else{
                System.out.println("aucun planning Ajouter");
            }
            return healthCheck;
        }else {
            throw new Exception();
        }
    }

}
