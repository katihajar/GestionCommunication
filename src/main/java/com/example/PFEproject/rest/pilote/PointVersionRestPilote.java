package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Application;
import com.example.PFEproject.bean.PointVersion;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.service.ApplicationService;
import com.example.PFEproject.service.PointVersionService;
import com.example.PFEproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.Multipart;
import java.net.URI;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/pointversion")
public class PointVersionRestPilote {
    @Autowired
    PointVersionService pointVersionService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    UserService userService;


    @GetMapping("/user/{id}")
    public ResponseEntity<List<PointVersion>> findByCreateurPointVersionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(pointVersionService.findByCreateurPointVersionId(id));
    }

    @DeleteMapping("/delete/{id}")
    public int deletePointVersionById(@PathVariable Long id) {
        return pointVersionService.deletePointVersionById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<PointVersion> save(@RequestParam("file") MultipartFile fileupload, @RequestParam("pointVersion") String pointVersionJson,@RequestParam("idUser") Long idUser,@RequestParam("idApp") Long idApp) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        PointVersion pointVersion = new PointVersion();
        pointVersion=objectMapper.readValue(pointVersionJson,PointVersion.class);
        User us= userService.findUserById(idUser);
        Application app= applicationService.findApplicationById(idApp);
        pointVersion.setCreateurPointVersion(us);
        pointVersion.setApplication(app);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/pointversion/save").toUriString());
        return ResponseEntity.created(uri).body(pointVersionService.save(pointVersion,fileupload));
    }
    // @PostMapping("/save")
    //    public ResponseEntity<PointVersion> save(@RequestBody PointVersion pointVersion) throws Exception {
    //        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pilote/pointversion/save").toUriString());
    //        return ResponseEntity.created(uri).body(pointVersionService.save(pointVersion));
    //    }
}
