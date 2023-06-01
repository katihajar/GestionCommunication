package com.example.PFEproject.service;

import com.example.PFEproject.bean.FluxEAI;
import com.example.PFEproject.bean.FluxSalesOrder;
import com.example.PFEproject.bean.HealthCheckFlamingo;
import com.example.PFEproject.repo.FluxEAIRepo;
import com.example.PFEproject.repo.FluxSalesOrderRepo;
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
public class FluxSalesOrderService {
    @Autowired
    FluxSalesOrderRepo fluxSalesOrderRepo;

    public List<FluxSalesOrder> findByHealthCheckFlamingoId(Long id) {
        return fluxSalesOrderRepo.findByHealthCheckFlamingoId(id);
    }

    public int deleteByHealthCheckFlamingoId(Long id) {
        return fluxSalesOrderRepo.deleteByHealthCheckFlamingoId(id);
    }
    public  List<FluxSalesOrder> saveAll(HealthCheckFlamingo in, List<FluxSalesOrder> fluxSalesOrders) {
        List<FluxSalesOrder> fluxSalesOrderList= new ArrayList<>();
        for (FluxSalesOrder fluxSalesOrder : fluxSalesOrders) {
            FluxSalesOrder flux = new FluxSalesOrder();
            flux.setHealthCheckFlamingo(in);
            flux.setSysteme(fluxSalesOrder.getSysteme());
            flux.setCommentaire(fluxSalesOrder.getCommentaire());
            flux.setEtat(fluxSalesOrder.getEtat());
            FluxSalesOrder flux2= fluxSalesOrderRepo.save(flux);
            fluxSalesOrderList.add(flux2);
        }
        return fluxSalesOrderList;
    }
}
