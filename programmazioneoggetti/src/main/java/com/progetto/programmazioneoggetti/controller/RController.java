package com.progetto.programmazioneoggetti.controller;

import com.progetto.programmazioneoggetti.Functions;
import com.progetto.programmazioneoggetti.model.Stats;
import com.progetto.programmazioneoggetti.model.Misurazioni;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
public class RController {

    /* invece di fare un nuovo indirizzo per le stats si aggiunge
    un parametro bool che indica quale ritornare */

    @RequestMapping( value = "/obj_list", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> obj_list() throws Exception{

        return Functions.obj_list();
    }

    @RequestMapping( value = "/obj_list_stats", method = RequestMethod.GET, produces = "application/json")
    public Stats obj_list_stats() throws Exception{

        return new Stats(Functions.obj_list());
    }

    @RequestMapping( value = "/obj_data", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> obj_data(@RequestParam(name = "day", defaultValue = "-1") int param_day,
                                           @RequestParam(name = "month", defaultValue = "-1") int param_month) throws Exception{

        return Functions.obj_data(param_day, param_month);
    }

    @RequestMapping( value = "/obj_data_stats", method = RequestMethod.GET, produces = "application/json")
    public Stats obj_data_stats(@RequestParam(name = "day", defaultValue = "-1") int param_day,
                                @RequestParam(name = "month", defaultValue = "-1") int param_month) throws Exception{

        return new Stats(Functions.obj_data(param_day, param_month));
    }

    @RequestMapping( value = "/obj_date_hour", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> date_hour(@RequestParam(name = "day", defaultValue = "-1") int param_day,
                                            @RequestParam(name = "month", defaultValue = "-1") int param_month,
                                            @RequestParam(name = "hour", defaultValue = "-1") int param_hour) throws Exception {

        return Functions.obj_date_hour(param_day, param_month, param_hour);
    }

    @RequestMapping( value = "/obj_date_hour_stats", method = RequestMethod.GET, produces = "application/json")
    public Stats date_hour_stats(@RequestParam(name = "day", defaultValue = "vuoto") int param_day,
                                 @RequestParam(name = "month", defaultValue = "vuoto") int param_month,
                                 @RequestParam(name = "hour", defaultValue = "vuoto") int param_hour) throws Exception {

        return new Stats(Functions.obj_date_hour(param_day, param_month, param_hour));
    }

}
