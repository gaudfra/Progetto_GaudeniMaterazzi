package com.progetto.programmazioneoggetti.controller;

import com.progetto.programmazioneoggetti.Functions;
import com.progetto.programmazioneoggetti.model.Misurazioni;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
public class RController {

    @RequestMapping( value = "/obj", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> method(@RequestParam(name = "param1", defaultValue = "capra") String param1) throws Exception{

        ArrayList<Misurazioni> lista_oggetti = new ArrayList<>();
        Functions.obj(lista_oggetti);

        return lista_oggetti;
    }
}
