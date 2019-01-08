package com.ipiecoles.java.mdd050.Controler;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.ipiecoles.java.mdd050.Service.CommercialService;
import com.ipiecoles.java.mdd050.Service.EmployeService;
import com.ipiecoles.java.mdd050.model.Commercial;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.CommercialRepository;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/employes")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private CommercialService commercialService;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private CommercialRepository commercialRepository;

    @RequestMapping("/count")
    public Long countEmploye2() throws Exception {
        return employeService.countEmploye();
    }

    @RequestMapping("/{id}")
    public Employe trouverEmploye2(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (employeRepository.exists(id)){
            return employeService.trouverEmploye(id);
        }
        else {
            throw new EntityNotFoundException("L'employé d'identifiant : " + id + " n'as pas été trouvé");
        }
    }

    @RequestMapping(params = "matricule")
    public Employe trouverEmployeMat(@RequestParam(value = "matricule") String matricule) throws EntityNotFoundException{
        if (employeService.trouverEmploye2(matricule) != null){
            return employeService.trouverEmploye2(matricule);
        }
        else {
            throw new EntityNotFoundException("L'employé de matricule : " + matricule + " n'as pas été trouvé");
        }
    }

    @RequestMapping()
    public Page<Employe> listeEmployes(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                           @RequestParam(value = "sortDirection") String sortDirection,
                                                           @RequestParam(value = "sortProperty") String sortProperty)
        {
            return employeService.findAllEmployesPaging(page, size, sortDirection, sortProperty);
         }

    @RequestMapping(method = RequestMethod.POST,
                                consumes = "application/json",
                                produces = "application/json",
                                value ="" )
    public Employe creerCommercial2(@RequestBody Employe employe){
        return employeService.creerCommercial(employe);
    }

    @RequestMapping(method = RequestMethod.PUT,
            produces = "application/json",
            value ="/{id}" )
    public Employe modifCommercial3(@PathVariable(value = "id") Long id,
                                                        @RequestBody Employe employe){
        return employeService.modifCommercial(id ,employe);
    }

    @RequestMapping(method = RequestMethod.DELETE,
           value = "/{id}" )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delCommercial3(@PathVariable("id") Long id){
        employeService.delCommercial(id);
    }


}
