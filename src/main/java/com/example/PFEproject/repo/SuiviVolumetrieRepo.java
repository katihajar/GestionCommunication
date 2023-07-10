package com.example.PFEproject.repo;

import com.example.PFEproject.bean.NbOccurence;
import com.example.PFEproject.bean.SuiviVolumetrie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SuiviVolumetrieRepo extends JpaRepository<SuiviVolumetrie, Long> {
    List<SuiviVolumetrie> findByNuitApplicativeId(Long id);
    int deleteSuiviVolumetrieById(Long id);
    int deleteByNuitApplicativeId(Long id);
}
