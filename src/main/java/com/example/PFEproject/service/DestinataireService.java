package com.example.PFEproject.service;

import com.example.PFEproject.bean.DestinataireCommunication;
import com.example.PFEproject.repo.DestinataireRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class DestinataireService {
    @Autowired
    DestinataireRepo destinataireRepo;

    public int deleteDestinataireCommunicationById(Long id) {
        return destinataireRepo.deleteDestinataireCommunicationById(id);
    }

    public List<DestinataireCommunication> findDestinataireCommunicationByApplicationId(Long id) {
        return destinataireRepo.findDestinataireCommunicationByApplicationId(id);
    }

    public List<DestinataireCommunication> findAll() {
        return destinataireRepo.findAll();
    }

    public DestinataireCommunication findByEmail(String email) {
        return destinataireRepo.findByEmail(email);
    }

    public DestinataireCommunication save(DestinataireCommunication entity) throws Exception{
        DestinataireCommunication des = findByEmail(entity.getEmail());
        if(des == null){
               entity.setStatutRespo("En Attente");
        return destinataireRepo.save(entity);
        }else {
            throw new Exception();
        }
    }

    public DestinataireCommunication validationResponsable(DestinataireCommunication entity) throws Exception{
        DestinataireCommunication des = findByEmail(entity.getEmail());
        if (des == null){
            throw new Exception();
        }else {
            des.setStatutRespo("Valider");
            return destinataireRepo.save(des);
        }
    }

}