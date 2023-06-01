package com.example.PFEproject.repo;

import com.example.PFEproject.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);
    List<User> findByLots(String lot);
    List<User> findByRolesName(String roleName);
    User findUserById(Long id);
}
