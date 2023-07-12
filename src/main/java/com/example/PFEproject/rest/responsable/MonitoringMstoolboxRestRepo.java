package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.MonitoringMstoolbox;
import com.example.PFEproject.service.MonitoringMstoolboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/monitoringMstoolbox")
public class MonitoringMstoolboxRestRepo {
    @Autowired
    MonitoringMstoolboxService monitoringMstoolboxService;

    @GetMapping("/lot/{lots}")
    public ResponseEntity<Page<MonitoringMstoolbox>> findByCreateurMonitoringLots(@PathVariable String lots,
                                                                                  @RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(monitoringMstoolboxService.findByCreateurMonitoringLots(lots, page, pageSize));
    }
    @GetMapping("/search")
    public Page<MonitoringMstoolbox> search(@RequestParam(required = false) String titre,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                            @RequestParam(required = true) String lot,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return monitoringMstoolboxService.search(titre, date, lot, pageable);
    }
}
