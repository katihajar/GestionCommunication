package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.FluxSalesOrder;
import com.example.PFEproject.service.FluxSalesOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/healthcheck/fluxSalesOrder")
public class FluxSalesOrderRestRespo {
    @Autowired
    FluxSalesOrderService fluxSalesOrderService;
    @GetMapping("/flux/{id}")
    public ResponseEntity<List<FluxSalesOrder>> findByHealthCheckFlamingoId(@PathVariable Long id) {
        return ResponseEntity.ok().body(fluxSalesOrderService.findByHealthCheckFlamingoId(id));
    }
}
