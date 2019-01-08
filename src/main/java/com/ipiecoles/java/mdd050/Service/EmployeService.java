package com.ipiecoles.java.mdd050.Service;


import com.ipiecoles.java.mdd050.model.Commercial;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    TechnicienRepository technicienRepository;

    public Long countEmploye() {
        return employeRepository.count();
    }

    public Employe trouverEmploye(Long id) {
        return employeRepository.findOne(id);
    }

    public Employe trouverEmploye2(String matricule) {
        return employeRepository.findByMatricule(matricule);
    }

    public Page<Employe> findAllEmployesPaging(Integer page, Integer size, String sortDirection, String sortProperty){

        if (page < 0){
            throw new IllegalArgumentException ("La valeur page doit être supérieur à 0 ");
        }
        else if (page > countEmploye() / size){
            throw new IllegalArgumentException("La valeur page doit être inférieur aux max : " + countEmploye());
        }
        else {
            PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
            return employeRepository.findAll(pageRequest);
        }


    }

     public Employe creerCommercial(Employe employe){
        if(employeRepository.findByMatricule(employe.getMatricule()) == null){
            return employeRepository.save(employe);
        }
        else {
            throw new EntityExistsException("L'Employé ne peut être créer, il existe déja");
        }
    }

    public Employe modifCommercial(Long id, Employe employe){
        return employeRepository.save(employe);
    }

    public void delCommercial(Long id){
        employeRepository.delete(id);
    }

}
