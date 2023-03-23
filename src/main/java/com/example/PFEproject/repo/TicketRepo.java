package com.example.PFEproject.repo;

import com.example.PFEproject.bean.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Long> {
    List<Ticket> findByPointVersionId(Long id);
    int deleteTicketById(Long id);
    int deleteByPointVersionId(Long id);
}