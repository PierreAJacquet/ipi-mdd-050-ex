package com.ipiecoles.java.mdd050.Service;

import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TechnicienService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
     TechnicienRepository technicienRepository;

     public Manager ajoutManager(Long id, String matricule) {
         Manager manager = managerRepository.findByMatricule(matricule);
         Technicien technicien = technicienRepository.findOne(id);

         if (technicien == null){
             throw new EntityNotFoundException("Il n'existe aucun Technicien avec cette id");
         }
         if  (manager == null){
             throw new EntityNotFoundException(("Il n'existe aucun manager avec cette id"));
         }
         else {
             technicien.setManager(manager);
             technicienRepository.save(technicien);

             managerRepository.save(manager);
             return manager;
         }

     }
}
