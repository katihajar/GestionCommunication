package com.example.PFEproject.repo;

import com.example.PFEproject.bean.AvancementActionProbleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvancementActionProblemeRepo extends JpaRepository<AvancementActionProbleme, Long> {
    List<AvancementActionProbleme> findByProblemeId(Long id);
    int deleteAvancementActionProblemeById(Long id);
    int deleteByProblemeId(Long id);
}
