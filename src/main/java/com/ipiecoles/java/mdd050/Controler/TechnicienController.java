package com.ipiecoles.java.mdd050.Controler;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ipiecoles.java.mdd050.Service.TechnicienService;
import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techniciens")
public class TechnicienController {

    @Autowired
    TechnicienService technicienService;

    @RequestMapping (method = RequestMethod.GET,
                                    produces = "application/json",
                                    value = "/{id}/manager/{matricule}/add")
     private Manager ajouterManager (@PathVariable(value = "id") Long id,
                                        @PathVariable(value = "matricule") String matricule)
    {
        return technicienService.ajoutManager(id, matricule);
    }
}
