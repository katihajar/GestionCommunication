package com.example.PFEproject.rest.admin;


import com.example.PFEproject.bean.User;
import com.example.PFEproject.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/users")
public class UserREST {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public ResponseEntity<List<User>> FindAllUsers(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> SaveUser(@RequestBody UserRole userRole){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/users/saveUser").toUriString());
        User us = userRole.getUser();
        us.setPassword(passwordEncoder.encode(us.getPassword()));
        return ResponseEntity.created(uri).body(userService.saveUser(us,userRole.getIdRole()));
    }
    @PutMapping("/UpdateUser")
    public ResponseEntity<User> updateUser(@RequestBody UserRole userRole) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/users/UpdateUser").toUriString());
        userRole.getUser().setPassword(passwordEncoder.encode(userRole.getUser().getPassword()));
        return ResponseEntity.created(uri).body(userService.updateUser(userRole.getUser(), userRole.getIdRole()));
    }

    @PutMapping("/setRole")
    public ResponseEntity<User> setRole(@RequestBody UsernameRole usernameRole) {
        return  ResponseEntity.ok().body(userService.setRole(usernameRole.getUsername(), usernameRole.getIdRole()));
    }
    @GetMapping("/username/{username}")
    public User loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }
    @GetMapping("/id/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<User>> saveAll(@RequestBody Iterable<User> users) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/users/saveAll").toUriString());
        for (User user: users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return ResponseEntity.created(uri).body(userService.saveAll(users));
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
