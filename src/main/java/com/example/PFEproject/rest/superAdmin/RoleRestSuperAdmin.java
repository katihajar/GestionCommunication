package com.example.PFEproject.rest.superAdmin;

import com.example.PFEproject.bean.Role;
import com.example.PFEproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/superadmin/roles")
public class RoleRestSuperAdmin {
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public List<Role> findAll() {
        return roleService.findAll();
    }
    @GetMapping("/adminRole")
    public ResponseEntity<Role> findByName() {
        return ResponseEntity.ok().body(roleService.findAdmin());
    }
}
