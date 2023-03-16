package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operation,Long> {
    int deleteOperationById(Long id);
    List<Operation> findByCreateurOperationId(Long id);
    List<Operation> findByApplicationResponsableId(Long id);
}
