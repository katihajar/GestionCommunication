package com.example.PFEproject.service;

import com.example.PFEproject.bean.FluxEAI;
import com.example.PFEproject.bean.HealthCheckFlamingo;
import com.example.PFEproject.repo.FluxEAIRepo;
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
public class FluxEAIService {
    @Autowired
    FluxEAIRepo fluxEAIRepo;
    public List<FluxEAI> findByHealthCheckFlamingoId(Long id) {
        return fluxEAIRepo.findByHealthCheckFlamingoId(id);
    }
    public int deleteByHealthCheckFlamingoId(Long id) {
        return fluxEAIRepo.deleteByHealthCheckFlamingoId(id);
    }
    public  List<FluxEAI> saveAll(HealthCheckFlamingo in, List<FluxEAI> fluxEAIs) {
        List<FluxEAI> fluxEAIList= new ArrayList<>();
        for (FluxEAI fluxEAI : fluxEAIs) {
            FluxEAI flux = new FluxEAI();
            flux.setHealthCheckFlamingo(in);
            flux.setProcess(fluxEAI.getProcess());
            flux.setSubProcess(fluxEAI.getSubProcess());
            flux.setCommentaire(fluxEAI.getCommentaire());
            flux.setEtat(fluxEAI.getEtat());
            FluxEAI flux2= fluxEAIRepo.save(flux);
            fluxEAIList.add(flux2);
        }
        return fluxEAIList;
    }
}
