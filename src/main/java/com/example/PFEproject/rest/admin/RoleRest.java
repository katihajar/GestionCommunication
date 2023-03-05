package com.example.PFEproject.rest.admin;

import com.example.PFEproject.bean.Role;
import com.example.PFEproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/roles")
public class RoleRest {
    @Autowired
    RoleService roleService;

    @PostMapping("/saveRole")
    public ResponseEntity<Role> SaveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles/saveRole").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }
    @PostMapping("/FindRoleByName/{name}")
    public ResponseEntity<Role> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(roleService.findByName(name));
    }
    @GetMapping("/")
    public List<Role> findAll() {
        return roleService.findAll();
    }
}
