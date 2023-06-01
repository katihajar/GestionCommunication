package com.example.PFEproject.rest.superAdmin;

import com.example.PFEproject.bean.User;
import com.example.PFEproject.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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
@RequestMapping("/api/superadmin/users")
public class UserRestSuperAdmin {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/")
    public ResponseEntity<List<User>> findByRolesName(){
        return ResponseEntity.ok().body(userService.findByRolesName());
    }
    @PostMapping("/saveUser")
    public ResponseEntity<User> SaveUser(@RequestBody UserRole2 userRole){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/users/saveUser").toUriString());
        User us = userRole.getUser();
        String pass = RandomStringUtils.randomAlphanumeric(10);
        System.out.println(pass);
        us.setPassword(passwordEncoder.encode(pass));
        return ResponseEntity.created(uri).body(userService.saveUser(us,userRole.getIdRole(),pass));
    }
    @PutMapping("/UpdateUser")
    public ResponseEntity<User> updateUser(@RequestBody UserRole2 userRole) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/users/UpdateUser").toUriString());
        return ResponseEntity.created(uri).body(userService.updateUser(userRole.getUser(), userRole.getIdRole()));
    }
}
@Data
class UserRole2{
    private User user;
    private Long idRole;

}