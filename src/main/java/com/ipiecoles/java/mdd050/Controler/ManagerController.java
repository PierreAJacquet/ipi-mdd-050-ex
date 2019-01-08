package com.ipiecoles.java.mdd050.Controler;

import com.ipiecoles.java.mdd050.Service.ManagerService;
import com.ipiecoles.java.mdd050.model.Technicien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @RequestMapping ( method = RequestMethod.GET,
                                 produces = "application/json",
                                    value ="/{id}/equipe/{matricule}/add" )
    private Technicien ajoutMembreEquipe (@PathVariable(value = "id") Long id,
                                                                @PathVariable(value = "matricule") String matricule)
    {
        return managerService.AjoutMembreEquipe(id, matricule);
    }

    @RequestMapping (method = RequestMethod.GET,
            value = "/{id}/equipe/{idTech}/remove" )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    private void supprimerMembreEquipe (@PathVariable(value = "id") Long id,
                                                                @PathVariable(value = "idTech") Long idTech)
    {
        managerService.SuprimerMembreEquipe(id, idTech);
    }

}
