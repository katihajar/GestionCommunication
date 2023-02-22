package com.example.PFEproject.repo;


import com.example.PFEproject.bean.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByOwner_Id(Long id);
    void deleteAll();
}
