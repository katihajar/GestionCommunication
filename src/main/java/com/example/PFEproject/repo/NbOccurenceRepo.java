package com.example.PFEproject.repo;

import com.example.PFEproject.bean.NbOccurence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NbOccurenceRepo extends JpaRepository<NbOccurence, Long> {
    List<NbOccurence> findByNuitApplicativeId(Long id);
    int deleteNbOccurenceById(Long id);
    int deleteByNuitApplicativeId(Long id);
}
