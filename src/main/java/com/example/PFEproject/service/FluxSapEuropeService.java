package com.example.PFEproject.service;

import com.example.PFEproject.bean.FluxSalesOrder;
import com.example.PFEproject.bean.FluxSapEurope;
import com.example.PFEproject.bean.HealthCheckFlamingo;
import com.example.PFEproject.repo.FluxSalesOrderRepo;
import com.example.PFEproject.repo.FluxSapEuropeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class FluxSapEuropeService {
    @Autowired
    FluxSapEuropeRepo fluxSapEuropeRepo;

    public List<FluxSapEurope> findByHealthCheckFlamingoId(Long id) {
        return fluxSapEuropeRepo.findByHealthCheckFlamingoId(id);
    }

    public int deleteByHealthCheckFlamingoId(Long id) {
        return fluxSapEuropeRepo.deleteByHealthCheckFlamingoId(id);
    }
    public  List<FluxSapEurope> saveAll(HealthCheckFlamingo in, List<FluxSapEurope> fluxSapEuropes) {
        List<FluxSapEurope> fluxSapEuropeList= new ArrayList<>();
        for (FluxSapEurope fluxSapEurope : fluxSapEuropes) {
            FluxSapEurope flux = new FluxSapEurope();
            flux.setHealthCheckFlamingo(in);
            flux.setSysteme(fluxSapEurope.getSysteme());
            flux.setCommentaire(fluxSapEurope.getCommentaire());
            flux.setEtat(fluxSapEurope.getEtat());
            FluxSapEurope flux2= fluxSapEuropeRepo.save(flux);
            fluxSapEuropeList.add(flux2);
        }
        return fluxSapEuropeList;
    }
}
