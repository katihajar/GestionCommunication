package com.example.PFEproject.rest;


import com.example.PFEproject.bean.Role;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.repo.UserRepository;
import com.example.PFEproject.service.RoleService;
import com.example.PFEproject.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserREST {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public ResponseEntity<List<User>> FindAllUsers(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> SaveUser(@RequestBody UserRole userRole){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/saveUser").toUriString());
        User us = userRole.getUser();
        us.setPassword(passwordEncoder.encode(us.getPassword()));
        return ResponseEntity.created(uri).body(userService.saveUser(us,userRole.getIdRole()));
    }
    @PostMapping("/saveRole")
    public ResponseEntity<Role> SaveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/saveRole").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }


    @PutMapping("/setRole")
    public ResponseEntity<User> setRole(@RequestBody UsernameRole usernameRole) {
        return  ResponseEntity.ok().body(userService.setRole(usernameRole.getUsername(), usernameRole.getIdRole()));
    }
}


@Data
class UserRole{
    private User user;
    private Long idRole;

}

@Data
class  UsernameRole{
    private String username;
    private Long idRole;
}
