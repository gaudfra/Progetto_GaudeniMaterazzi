package com.progetto.programmazioneoggetti.controller;

import com.progetto.programmazioneoggetti.Functions;
import com.progetto.programmazioneoggetti.Stats;
import com.progetto.programmazioneoggetti.model.Misurazioni;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
public class RController {

    @RequestMapping( value = "/obj_list", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> obj_list() throws Exception{

        return Functions.obj_list();
    }

    @RequestMapping( value = "/obj_list_stats", method = RequestMethod.GET, produces = "application/json")
    public Stats obj_list_stats() throws Exception{

        return new Stats(Functions.obj_list());
    }

    @RequestMapping( value = "/obj_data", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> obj_data(@RequestParam(name = "param_day", defaultValue = "vuoto") String param_day,
                                           @RequestParam(name = "param_month", defaultValue = "vuoto") String param_month) throws Exception{

        return Functions.obj_data(param_day, param_month);
    }

    @RequestMapping( value = "/obj_data_stats", method = RequestMethod.GET, produces = "application/json")
    public Stats obj_data_stats(@RequestParam(name = "param_day", defaultValue = "vuoto") String param_day,
                                @RequestParam(name = "param_month", defaultValue = "vuoto") String param_month) throws Exception{

        return new Stats(Functions.obj_data(param_day, param_month));
    }

    //aggiungi ricerca per orario su data
}

