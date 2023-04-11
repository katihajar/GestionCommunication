package com.example.PFEproject.service;
import com.example.PFEproject.bean.Role;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.repo.RoleRepo;
import com.example.PFEproject.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    @Autowired
    private JavaMailSender javaMailSender;

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

    public User saveUser(User user, Long id,String pass){
        User usr = findUserByUsername(user.getUsername());
        if (usr == null) {
            Set<Role> authorities = new HashSet<>();
            roleRepo.findById(id).ifPresent(authorities::add);
            user.setRoles(authorities);
            prepareMessage(user,pass);
            return userRepository.save(user);
        }else {
            return usr;
        }
    }

    public User updateUser(User user, Long id) throws Exception {
        User usr = findUserById(user.getId());
        User usrExist = findUserByUsername(user.getUsername());
        if (usr == null) {
            throw new Exception();
        }else {
            if(usrExist ==null ||  usrExist.getId() == usr.getId()) {
                Set<Role> authorities = new HashSet<>();
                roleRepo.findById(id).ifPresent(authorities::add);
                usr.setRoles(authorities);
                usr.setNom(user.getNom());
                usr.setLots(user.getLots());
                usr.setPrenom(user.getPrenom());
                usr.setUsername(user.getUsername());
                usr.setPassword(user.getPassword());
                return userRepository.save(usr);
            }else {
                throw new Exception();
            }
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
    public void prepareMessage(User user,String pass) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Gestion-Communication@outlook.fr");
        String to = user.getUsername()+"@cgi.com";
        message.setTo(to);
        message.setSubject("Création de compte : Gestion de Communication");
        message.setText("Votre compte a été créé, vous trouverez ci-joint le mot de passe et login :\n" +
                "login : " + user.getUsername() + "\n" +
                "password : " + pass);
        javaMailSender.send(message);
    }
    public User changePassword(User us) {

            return userRepository.save(us);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

