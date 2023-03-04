package com.example.PFEproject.rest.admin;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/application")
public class ApplicationRest {
    @Autowired
    ApplicationService applicationService;

    @DeleteMapping("/id/{id}")
    public int deleteApplicationById(@PathVariable Long id) {
        return applicationService.deleteApplicationById(id);
    }
    @GetMapping("/AllApp")
    public List<Application> findAll() {
        return applicationService.findAll();
    }
    @PostMapping("/saveApp")
    public Application saveApp(@RequestBody Application application) {
        return applicationService.saveApp(application);
    }
    @PutMapping("/updateApp")
    public Application updateApp(@RequestBody Application application) {
        return applicationService.updateApp(application);
    }
}
