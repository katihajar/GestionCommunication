package com.example.PFEproject.rest.responsable;

import com.example.PFEproject.bean.Ticket;
import com.example.PFEproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsable/ticket")
public class TicketRestRespo {
    @Autowired
    TicketService ticketService;
    @GetMapping("/pointversion/{id}")
    public ResponseEntity<List<Ticket>> findByPointVersionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(ticketService.findByPointVersionId(id));
    }
}
