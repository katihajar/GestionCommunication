package com.example.PFEproject.service;

import com.example.PFEproject.bean.LivraisonCARM;
import com.example.PFEproject.bean.PointVersion;
import com.example.PFEproject.repo.LivraisonCARMRepo;
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
public class LivraisonCARMService {
    @Autowired
    LivraisonCARMRepo livraisonCARMRepo;

    public List<LivraisonCARM> findByPointVersionId(Long id) {
        return livraisonCARMRepo.findByPointVersionId(id);
    }

    public int deleteLivraisonCARMById(Long id) {
        return livraisonCARMRepo.deleteLivraisonCARMById(id);
    }

    public int deleteByPointVersionId(Long id) {
        return livraisonCARMRepo.deleteByPointVersionId(id);
    }
    public  List<LivraisonCARM> saveAll(PointVersion in, List<LivraisonCARM> livraisonCARMs) {
        List<LivraisonCARM> livraisonCARMList= new ArrayList<>();
        for (LivraisonCARM livr : livraisonCARMs) {
            LivraisonCARM livraisonCARM = new LivraisonCARM();
            livraisonCARM.setPointVersion(in);
            livraisonCARM.setDateMEI(livr.getDateMEI());
            livraisonCARM.setNombreTicket(livr.getNombreTicket());
            livraisonCARM.setStatutMEI(livr.getStatutMEI());
            LivraisonCARM livraisonCARM2= livraisonCARMRepo.save(livraisonCARM);
            livraisonCARMList.add(livraisonCARM2);
        }
        return livraisonCARMList;
    }
}
