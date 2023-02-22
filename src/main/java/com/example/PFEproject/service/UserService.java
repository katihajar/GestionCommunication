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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepo roleRepo;


    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public List<User> saveAll(Iterable<User> users){
        List<User> userss=new ArrayList<>();
        for (User u: users) {
            User usr = findUserByUsername(u.getUsername());
            if (usr == null) {
                userss.add(u);
            }else {
                log.info("username existe : "+u.getUsername());
            }
        }
        userRepository.saveAll(userss);
        return userss;
    }
    public User saveUser(User user, Long id){
        User usr = findUserByUsername(user.getUsername());
        if (usr == null) {
            Set<Role> authorities = new HashSet<>();
            roleRepo.findById(id).ifPresent(authorities::add);
            user.setRoles(authorities);
            return userRepository.save(user);
        }else {
            return usr;
        }
    }
    public User setRole(String username, Long id){
        User usr = findUserByUsername(username);
        if (usr == null) {
            return null;
        }else {
            Set<Role> authorities = new HashSet<>();
            roleRepo.findById(id).ifPresent(authorities::add);
            usr.setRoles(authorities);
            return userRepository.save(usr);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}

