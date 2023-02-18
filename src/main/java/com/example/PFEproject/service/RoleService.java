package com.example.PFEproject.service;

import com.example.PFEproject.bean.Role;
import com.example.PFEproject.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }

    public Role saveRole(Role role){
        Role r = findByName(role.getName());
        if (r == null) {
            return roleRepo.save(role);
        }else {
            return null;
        }
    }

}
