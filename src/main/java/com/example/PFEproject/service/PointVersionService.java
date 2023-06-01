package com.example.PFEproject.service;

import com.example.PFEproject.bean.PointVersion;
import com.example.PFEproject.repo.PointVersionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class PointVersionService {
    @Autowired
    PointVersionRepo pointVersionRepo;
    @Autowired
    LivraisonCARMService livraisonCARMService;
    @Autowired
    TicketService ticketService;
    @Autowired
    PlanningPointVersionService planningPointVersionService;

    public List<PointVersion> findAll() {
        return pointVersionRepo.findAll();
    }

    public List<PointVersion> findByCreateurPointVersionId(Long id) {
        return pointVersionRepo.findByCreateurPointVersionId(id);
    }

    public int deletePointVersionById(Long id) {
        int r1= livraisonCARMService.deleteByPointVersionId(id);
        int r2= ticketService.deleteByPointVersionId(id);
        int r3= planningPointVersionService.deleteByPointVersionId(id);
        int r4= pointVersionRepo.deletePointVersionById(id);
        return r1+r2+r3+r4;
    }

    public PointVersion save(PointVersion pointVersion,MultipartFile upload) throws Exception{
        if(pointVersion != null) {
            PointVersion point = new PointVersion();
            point.setTitre(pointVersion.getTitre());
            point.setVersion(pointVersion.getVersion());
            point.setApplication(pointVersion.getApplication());
            point.setGoNoGoMEP(pointVersion.getGoNoGoMEP());
            point.setGoNoGoTNR(pointVersion.getGoNoGoTNR());
            point.setRemarque(pointVersion.getRemarque());
            point.setLienComment(pointVersion.getLienComment());
            point.setCreateurPointVersion(pointVersion.getCreateurPointVersion());
            point.setDateAjout(pointVersion.getDateAjout());
            point.setTicketConfirmer(pointVersion.getTicketConfirmer());
            point.setImageName(upload.getOriginalFilename());
            point.setImageSize(upload.getSize());
            point.setImageType(upload.getContentType());
            point.setImage(upload.getBytes());
            PointVersion point1 = pointVersionRepo.save(point);
            if(pointVersion.getLivraisonCARMList() !=null){
                livraisonCARMService.saveAll(point1,pointVersion.getLivraisonCARMList());
            }else{
                System.out.println("aucune livraison Ajouter");
            }
            if(pointVersion.getTicketList() !=null){
                ticketService.saveAll(point1,pointVersion.getTicketList());
            }else{
                System.out.println("aucun Ticket Ajouter");
            }
            if(pointVersion.getPlanningPointVersionList() !=null){
                planningPointVersionService.saveAll(point1,pointVersion.getPlanningPointVersionList());
            }else{
                System.out.println("aucun planning Ajouter");
            }
            return point1;
        }else {
            throw new Exception();
        }
    }

}
