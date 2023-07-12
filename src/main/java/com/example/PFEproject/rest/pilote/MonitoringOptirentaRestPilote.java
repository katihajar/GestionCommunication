package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.MonitoringOptirenta;
import com.example.PFEproject.service.MonitoringOptirentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/monitoringOptirenta")
public class MonitoringOptirentaRestPilote {
    @Autowired
    MonitoringOptirentaService monitoringOptirentaService;
    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<MonitoringOptirenta>> findByCreateurMonitoringLots(@PathVariable
    String lots,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(monitoringOptirentaService.findByCreateurMonitoringLots(lots, page, pageSize));
    }
    @DeleteMapping("/delete/{id}")
    public int deleteMonitoringOptirentaById(@PathVariable Long id) {
        return monitoringOptirentaService.deleteMonitoringOptirentaById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<MonitoringOptirenta> save(@RequestBody MonitoringOptirenta entity) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/monitoringOptirenta/save").toUriString());
        return ResponseEntity.created(uri).body(monitoringOptirentaService.save(entity));
    }
    @GetMapping("/search")
    public Page<MonitoringOptirenta> search(@RequestParam(required = false) String titre,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date date,
                                            @RequestParam(required = true) String lot,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return monitoringOptirentaService.search(titre, date, lot, pageable);
    }
}
