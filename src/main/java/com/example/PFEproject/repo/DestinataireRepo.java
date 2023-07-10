package com.example.PFEproject.repo;

import com.example.PFEproject.bean.DestinataireCommunication;
import com.example.PFEproject.bean.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinataireRepo extends JpaRepository<DestinataireCommunication,Long> {
    int deleteDestinataireCommunicationById(Long id);
    Page<DestinataireCommunication> findDestinataireCommunicationByApplicationId(Long id, Pageable pageable);
    List<DestinataireCommunication> findDestinataireCommunicationByApplicationId(Long id);
    List<DestinataireCommunication> findByApplicationNomApplication(String nom);
    DestinataireCommunication findByEmail(String email);
    DestinataireCommunication findDestinataireCommunicationById(Long id);
    DestinataireCommunication findByEmailAndApplicationNomApplication(String email,String nom);

}
