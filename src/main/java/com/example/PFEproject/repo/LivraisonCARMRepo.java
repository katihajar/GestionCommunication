package com.example.PFEproject.repo;

import com.example.PFEproject.bean.LivraisonCARM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivraisonCARMRepo extends JpaRepository<LivraisonCARM,Long> {
    List<LivraisonCARM> findByPointVersionId(Long id);
    int deleteLivraisonCARMById(Long id);
    int deleteByPointVersionId(Long id);

}