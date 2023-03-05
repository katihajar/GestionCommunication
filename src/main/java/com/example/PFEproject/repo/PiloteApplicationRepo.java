package com.example.PFEproject.repo;

import com.example.PFEproject.bean.PiloteApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PiloteApplicationRepo extends JpaRepository<PiloteApplication, Long> {
    PiloteApplication findByPiloteUsernameAndApplicationNomApplication(String username,String nomApp);
    List<PiloteApplication> findByApplicationNomApplication(String nomApp);
    List<PiloteApplication> findByPiloteUsername(String username);

}
