package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Implants;
import com.example.PFEproject.bean.NbOccurence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImplantsRepo extends JpaRepository<Implants, Long> {
    List<Implants> findByMstoolboxId(Long id);
    int deleteByMstoolboxId(Long id);
}
