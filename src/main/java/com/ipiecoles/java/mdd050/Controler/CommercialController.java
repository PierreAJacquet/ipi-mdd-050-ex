package com.ipiecoles.java.mdd050.Controler;

import com.ipiecoles.java.mdd050.Service.CommercialService;
import com.ipiecoles.java.mdd050.model.Commercial;
import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController(value = "/commercial")
public class CommercialController {

    @Autowired
    CommercialService commercialService;

}
