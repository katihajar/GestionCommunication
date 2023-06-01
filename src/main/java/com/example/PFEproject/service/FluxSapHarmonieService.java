package com.example.PFEproject.service;

import com.example.PFEproject.bean.FluxSapEurope;
import com.example.PFEproject.bean.FluxSapHarmonie;
import com.example.PFEproject.bean.HealthCheckFlamingo;
import com.example.PFEproject.repo.FluxSapHarmonieRepo;
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
public class FluxSapHarmonieService {

@Autowired
    FluxSapHarmonieRepo fluxSapHarmonieRepo;

    public List<FluxSapHarmonie> findByHealthCheckFlamingoId(Long id) {
        return fluxSapHarmonieRepo.findByHealthCheckFlamingoId(id);
    }

    public int deleteByHealthCheckFlamingoId(Long id) {
        return fluxSapHarmonieRepo.deleteByHealthCheckFlamingoId(id);
    }

    public List<FluxSapHarmonie> saveAll(HealthCheckFlamingo in, List<FluxSapHarmonie> fluxSapHarmonies) {
        List<FluxSapHarmonie> fluxSapHarmonieList= new ArrayList<>();
        for (FluxSapHarmonie fluxSapHarmonie : fluxSapHarmonies) {
            FluxSapHarmonie flux = new FluxSapHarmonie();
            flux.setHealthCheckFlamingo(in);
            flux.setSysteme(fluxSapHarmonie.getSysteme());
            flux.setCommentaire(fluxSapHarmonie.getCommentaire());
            flux.setEtat(fluxSapHarmonie.getEtat());
            FluxSapHarmonie flux2= fluxSapHarmonieRepo.save(flux);
            fluxSapHarmonieList.add(flux2);
        }
        return fluxSapHarmonieList;
    }
}
