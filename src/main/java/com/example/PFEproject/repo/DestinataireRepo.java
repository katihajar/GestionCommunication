package com.example.PFEproject.repo;

import com.example.PFEproject.bean.DestinataireCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinataireRepo extends JpaRepository<DestinataireCommunication,Long> {
    int deleteDestinataireCommunicationById(Long id);

    List<DestinataireCommunication> findDestinataireCommunicationByApplicationId(Long id);
    DestinataireCommunication findByEmail(String email);
    DestinataireCommunication findDestinataireCommunicationById(Long id);
    DestinataireCommunication findByEmailAndApplicationNomApplication(String email,String nom);

}
