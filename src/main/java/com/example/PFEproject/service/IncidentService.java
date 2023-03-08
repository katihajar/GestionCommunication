package com.example.PFEproject.service;
import com.example.PFEproject.bean.Incident;
import com.example.PFEproject.repo.IncidentRepo;
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
public class IncidentService {
    @Autowired
    IncidentRepo incidentRepo;

    @Autowired
    PlanActionService planActionService;

    public List<Incident> findByCreateurIncidentUsername(String username) {
        return incidentRepo.findByCreateurIncidentUsername(username);
    }

    public Incident findByTitreIncident(String title) {
        return incidentRepo.findByTitreIncident(title);
    }

    public int deleteIncidentById(Long id) {
        int r2=planActionService.deletePlanActionByIncidentId(id);
        int r=incidentRepo.deleteIncidentById(id);
        return r + r2;
    }

    public Incident save(Incident incident) throws Exception{
        Incident inc = findByTitreIncident(incident.getTitreIncident());
        if(inc == null) {
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
            incidentSave.setSolutionContournement(incident.getSolutionContournement());
            incidentSave.setProchaineCommunication(incident.getProchaineCommunication());
            incidentRepo.save(incident);
            Incident incident1 = findByTitreIncident(incident.getTitreIncident());
            if (incident.getPlanActionList() != null) {
                System.out.println("Plan test");
                planActionService.saveAllAction(incident1, incident.getPlanActionList());
            }
            return incident1;
        }else {
            throw new Exception();
        }
    }
}
