package com.example.PFEproject.service;

import com.example.PFEproject.bean.PointVersion;
import com.example.PFEproject.bean.Ticket;
import com.example.PFEproject.repo.TicketRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class TicketService {
    @Autowired
    TicketRepo ticketRepo;

    public List<Ticket> findByPointVersionId(Long id) {
        return ticketRepo.findByPointVersionId(id);
    }

    public int deleteTicketById(Long id) {
        return ticketRepo.deleteTicketById(id);
    }

    public int deleteByPointVersionId(Long id) {
        return ticketRepo.deleteByPointVersionId(id);
    }
    public  List<Ticket> saveAll(PointVersion in, List<Ticket> tickets) {
        List<Ticket> ticketList= new ArrayList<>();
        for (Ticket ticket : tickets) {
            Ticket tick = new Ticket();
            tick.setPointVersion(in);
            tick.setDescription(ticket.getDescription());
            tick.setType(ticket.getType());
            tick.setNumero(ticket.getNumero());
            Ticket tick2= ticketRepo.save(tick);
            ticketList.add(tick2);
        }
        return ticketList;
    }
}
