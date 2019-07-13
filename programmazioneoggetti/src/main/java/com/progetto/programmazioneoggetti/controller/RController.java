package com.progetto.programmazioneoggetti.controller;

import com.progetto.programmazioneoggetti.Functions;
import com.progetto.programmazioneoggetti.model.Misurazioni;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
public class RController {

    @RequestMapping( value = "/obj_list", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> obj_list() throws Exception{

        return Functions.obj_list();
    }

    @RequestMapping( value = "/obj_day", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> obj_day (@RequestParam(name = "param_day", defaultValue = "1") String param_day) throws Exception{

        return Functions.obj_day(param_day);
    }

    @RequestMapping( value = "/obj_date", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> obj_date (@RequestParam(name = "param_date", defaultValue = "2016-1-1") String param_date) throws Exception{

        return Functions.obj_date(param_date);
    }
}
