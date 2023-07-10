package com.example.PFEproject.repo;

import com.example.PFEproject.bean.NuitApplicative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NuitApplicativeRepo extends JpaRepository<NuitApplicative, Long> {
    List<NuitApplicative> findByCreateurNuitApplicativeLots(String lots);
    Page<NuitApplicative> findByCreateurNuitApplicativeLots(String lots, Pageable pageable);
    int deleteNuitApplicativeById(Long id);
}
