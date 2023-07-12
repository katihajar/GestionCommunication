package com.example.PFEproject.repo;

import com.example.PFEproject.bean.TransactionHandbid;
import com.example.PFEproject.bean.TransactionSmile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionSmileRepo extends JpaRepository<TransactionSmile, Long> {
    List<TransactionSmile> findByMstoolboxId(Long id);
    int deleteByMstoolboxId(Long id);
}
