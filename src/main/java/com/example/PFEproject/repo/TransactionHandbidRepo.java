package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Implants;
import com.example.PFEproject.bean.TransactionHandbid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHandbidRepo extends JpaRepository<TransactionHandbid, Long> {
    List<TransactionHandbid> findByMstoolboxId(Long id);
    int deleteByMstoolboxId(Long id);
}
