package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.PointVersion;
import com.example.PFEproject.service.PointVersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/pointversion")
public class PointVersionRestRespo {
    @Autowired
    PointVersionService pointVersionService;

    @GetMapping("/findAll")
    public List<PointVersion> findAll() {
        return pointVersionService.findAll();
    }
}
