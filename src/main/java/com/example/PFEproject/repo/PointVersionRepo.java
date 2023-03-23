package com.example.PFEproject.repo;

import com.example.PFEproject.bean.PointVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointVersionRepo extends JpaRepository<PointVersion,Long> {
    List<PointVersion> findByCreateurPointVersionId(Long id);
    int deletePointVersionById(Long id);
}
