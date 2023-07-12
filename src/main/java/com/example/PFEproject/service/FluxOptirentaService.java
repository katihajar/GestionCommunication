package com.example.PFEproject.service;

import com.example.PFEproject.bean.FluxOptirenta;
import com.example.PFEproject.bean.MonitoringMstoolbox;
import com.example.PFEproject.bean.MonitoringOptirenta;
import com.example.PFEproject.bean.TransactionHandbid;
import com.example.PFEproject.repo.FluxOptirentaRepo;
import com.example.PFEproject.repo.TransactionHandbidRepo;
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
public class FluxOptirentaService {
    @Autowired
    FluxOptirentaRepo fluxOptirentaRepo;

    public List<FluxOptirenta> findByOptirentaId(Long id) {
        return fluxOptirentaRepo.findByOptirentaId(id);
    }

    public int deleteByOptirentaId(Long id) {
        return fluxOptirentaRepo.deleteByOptirentaId(id);
    }

    public List<FluxOptirenta> saveAll(MonitoringOptirenta monitoring, List<FluxOptirenta> fluxOptirentas) {
        List<FluxOptirenta> fluxOptirentaList= new ArrayList<>();
        for (FluxOptirenta flux : fluxOptirentas) {
            FluxOptirenta fluxOptirenta = new FluxOptirenta();
            FluxOptirenta fluxOptirenta2 ;
            fluxOptirenta.setOptirenta(monitoring);
            fluxOptirenta.setNomFlux(flux.getNomFlux());
            fluxOptirenta.setEtat(flux.getEtat());
            fluxOptirenta.setCommentaire(flux.getCommentaire());
            fluxOptirenta2= fluxOptirentaRepo.save(fluxOptirenta);
            fluxOptirentaList.add(fluxOptirenta2);
        }
        return fluxOptirentaList;
    }
}
