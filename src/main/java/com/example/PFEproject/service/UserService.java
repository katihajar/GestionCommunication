package com.example.PFEproject.service;
import com.example.PFEproject.bean.Role;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.repo.RoleRepo;
import com.example.PFEproject.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepo roleRepo;
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user id not found"));
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }


    public User saveUser(User user){
        User usr = findUserByUsername(user.getUsername());
        if (usr == null) {
            return userRepository.save(user);
        }else {
            return null;
        }
    }

    public void addRoleToUser(String username, String roleName){
        User us = userRepository.findUserByUsername(username);
        Role role = roleRepo.findByName(roleName);
        us.getRoles().add(role);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

