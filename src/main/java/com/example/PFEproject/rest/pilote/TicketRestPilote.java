package com.example.PFEproject.rest.pilote;

import com.example.PFEproject.bean.Ticket;
import com.example.PFEproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pilote/ticket")
public class TicketRestPilote {
    @Autowired
    TicketService ticketService;
    @GetMapping("/pointversion/{id}")
    public ResponseEntity<List<Ticket>> findByPointVersionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(ticketService.findByPointVersionId(id));
    }
    @DeleteMapping("/delete/{id}")
    public int deleteTicketById(@PathVariable Long id) {
        return ticketService.deleteTicketById(id);
    }
}
