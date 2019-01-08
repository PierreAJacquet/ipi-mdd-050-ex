package com.ipiecoles.java.mdd050.Service;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    TechnicienRepository technicienRepository;

    public Technicien AjoutMembreEquipe(Long id, String matricule){
        Manager manager = managerRepository.findOneWithEquipeById(id);
        Technicien technicien = technicienRepository.findByMatricule(matricule)  ;

        if (technicien == null){
            throw new EntityNotFoundException("il n'existe aucun technicien avec cette id");
        }
        if (manager == null){
            throw new EntityNotFoundException("Il n'existe aucun manager avec cette id");
        }
        if (technicien.getMatricule().equals(technicien.getMatricule())){
            throw new IllegalArgumentException("Le technicien de matricule " + matricule + " est déja présent dans l'équipe");
        }
        else {
            manager.ajoutTechnicienEquipe(technicien);
            managerRepository.save(manager);

            technicien.setManager(manager);
            technicienRepository.save(technicien);
            return technicien;
        }
    }

    public void SuprimerMembreEquipe(Long id, Long idTech){
        Technicien technicien = technicienRepository.findOne(idTech);
        Manager manager = managerRepository.findOneWithEquipeById(id);

        if (technicien == null){
            throw new EntityNotFoundException("Il n'existe aucun Technicien avec cette id");
        }
        if  (manager == null){
            throw new EntityNotFoundException(("Il n'existe aucun manager avec cette id"));
            }
         if (technicien.getManager().getId().equals(manager.getId())){
             manager.getEquipe().remove(technicien);
             managerRepository.save(manager);

             technicien.setManager(null);
             technicienRepository.save(technicien);
        }
        else {
            throw new IllegalArgumentException("Le technicien d'id " + idTech + " n'as pas le manager d'id " + id);
         }

    }
}
