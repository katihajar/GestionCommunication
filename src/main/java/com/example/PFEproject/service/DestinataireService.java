package com.example.PFEproject.service;

import com.example.PFEproject.bean.DestinataireCommunication;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.bean.NbOccurence;
import com.example.PFEproject.bean.NuitApplicative;
import com.example.PFEproject.repo.DestinataireRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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

    public Page<DestinataireCommunication> findDestinataireCommunicationByApplicationId(Long id, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return destinataireRepo.findDestinataireCommunicationByApplicationId(id, pageable);
    }

    public DestinataireCommunication save(DestinataireCommunication entity) throws Exception{
        DestinataireCommunication des = findByEmailAndApplicationNomApplication(entity.getEmail(),entity.getApplication().getNomApplication());
        if(des == null){
               entity.setStatutRespo("Valider");
        return destinataireRepo.save(entity);
        }else {
            throw new Exception();
        }
    }
    public List<DestinataireCommunication> saveAll(List<DestinataireCommunication> destinataireCommunications)  throws Exception{
        List<DestinataireCommunication> destinataireCommunicationList= new ArrayList<>();
        for (DestinataireCommunication des : destinataireCommunications) {
            DestinataireCommunication test= findByEmailAndApplicationNomApplication(des.getEmail(),des.getApplication().getNomApplication());
            if(test==null) {
                DestinataireCommunication dest = new DestinataireCommunication();
                DestinataireCommunication dest2;
                dest.setTypeDest(des.getTypeDest());
                dest.setEmail(des.getEmail());
                dest.setApplication(des.getApplication());
                dest.setStatutRespo("Valider");
                dest2 = destinataireRepo.save(dest);
                destinataireCommunicationList.add(dest2);
            }else {
                throw new Exception();
            }
        }
        return destinataireCommunicationList;
    }

    public DestinataireCommunication findByEmailAndApplicationNomApplication(String email, String nom) {
        return destinataireRepo.findByEmailAndApplicationNomApplication(email, nom);
    }

    public DestinataireCommunication findDestinataireCommunicationById(Long id) {
        return destinataireRepo.findDestinataireCommunicationById(id);
    }

    public DestinataireCommunication validationResponsable(DestinataireCommunication entity) throws Exception{
        DestinataireCommunication des = findDestinataireCommunicationById(entity.getId());
        if (des == null){
            throw new Exception();
        }else {
            des.setStatutRespo("Valider");
            return destinataireRepo.save(des);
        }
    }

    public List<DestinataireCommunication> findByApplicationNomApplication(String nom) {
        return destinataireRepo.findByApplicationNomApplication(nom);
    }
    public DestinataireCommunication RetirerDest(DestinataireCommunication entity) throws Exception{
        DestinataireCommunication des = findDestinataireCommunicationById(entity.getId());
        if (des == null){
            throw new Exception();
        }else {
            des.setStatutRespo("En Attente");
            return destinataireRepo.save(des);
        }
    }
    public Page<DestinataireCommunication> searchDest(String email,String statut, long id, String type, Pageable pageable) {
        List<DestinataireCommunication> allDest = destinataireRepo.findDestinataireCommunicationByApplicationId(id);
        List<DestinataireCommunication> filteredDest = allDest.stream()
                .filter(dest -> {
                    boolean isMatched = true;
                    if (email != null && !email.isEmpty() && dest.getEmail() != null && !dest.getEmail().contains(email)) {
                        isMatched = false;
                    }
                    if (type != null && !type.isEmpty() && dest.getTypeDest() != null && !dest.getTypeDest().equals(type)) {
                        isMatched = false;
                    }
                    if (statut != null && !statut.isEmpty() && dest.getStatutRespo() != null && !dest.getStatutRespo().equals(statut)) {
                        isMatched = false;
                    }
                    return isMatched;
                })
                .collect(Collectors.toList());
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<DestinataireCommunication> paginatedDest;
        if (filteredDest.size() < startItem) {
            paginatedDest = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, filteredDest.size());
            paginatedDest = filteredDest.subList(startItem, toIndex);
        }
        return new PageImpl<>(paginatedDest, pageable, filteredDest.size());
    }

}
