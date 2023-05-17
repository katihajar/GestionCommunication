package com.example.PFEproject.service;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.repo.IncidentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class IncidentService {
    @Autowired
    IncidentRepo incidentRepo;

    @Autowired
    PlanActionService planActionService;
    @Autowired
    private JavaMailSender javaMailSender;

    public List<Incident> findAll() {
        return incidentRepo.findAll();
    }

    public void sendEmailWithDelay(Date date, String to, String subject, String text) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -30);
        Date delayDate = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("Gestion-Communication@outlook.fr");
                    message.setTo(to);
                    message.setSubject(subject);
                    message.setText(text);
                    message.setSentDate(date);
                    javaMailSender.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delayDate);
    }

    public List<Incident> findByApplicationLot(String lots) {
        return incidentRepo.findByApplicationLot(lots);
    }

    public List<Incident> findByCreateurIncidentUsername(String username) {
        return incidentRepo.findByCreateurIncidentUsername(username);
    }

    public List<Incident> findByApplicationResponsableId(Long id) {
        return incidentRepo.findByApplicationResponsableId(id);
    }

    public int deleteIncidentById(Long id) {
        int r2=planActionService.deletePlanActionByIncidentId(id);
        int r=incidentRepo.deleteIncidentById(id);
        return r + r2;
    }

    public Incident save(Incident incident) throws Exception{
        if(incident != null) {
            Incident incidentSave= new Incident();
            incidentSave.setCreateurIncident(incident.getCreateurIncident());
            incidentSave.setApplication(incident.getApplication());
            incidentSave.setNumeroIncident(incident.getNumeroIncident());
            incidentSave.setDescription(incident.getDescription());
            incidentSave.setTitreIncident(incident.getTitreIncident());
            incidentSave.setDateDebut(incident.getDateDebut());
            incidentSave.setDateFin(incident.getDateFin());
            incidentSave.setCausePrincipale(incident.getCausePrincipale());
            incidentSave.setDescription(incident.getCausePrincipale());
            incidentSave.setStatut(incident.getStatut());
            incidentSave.setSituationActuelle(incident.getSituationActuelle());
            incidentSave.setImpact(incident.getImpact());
            incidentSave.setSolutionContournement(incident.getSolutionContournement());
            incidentSave.setProchaineCommunication(incident.getProchaineCommunication());
            incidentSave.setDateAjout(incident.getDateAjout());
            incidentSave.setType(incident.getType());
            incidentSave.setActionPrise(incident.getActionPrise());
            incidentSave.setDetailResolution(incident.getDetailResolution());
            Incident incident1 = incidentRepo.save(incidentSave);
            if(incident1.getProchaineCommunication()!=null){
                String email = incident1.getCreateurIncident().getUsername()+"@cgi.com";
                String text = " Il vous reste 15min pour la prochaine Comunication de l'incident : "+incident1.getTitreIncident()+".";
                sendEmailWithDelay(incident1.getProchaineCommunication(),email,"Rappel Incident",text);
            }
            if (incident.getPlanActionList() != null) {
                planActionService.saveAllAction(incident1, incident.getPlanActionList());
            }
            return incident1;
        }else {
            throw new Exception();
        }
    }
}
