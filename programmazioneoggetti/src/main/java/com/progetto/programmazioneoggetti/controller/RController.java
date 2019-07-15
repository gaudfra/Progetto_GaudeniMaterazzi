package com.progetto.programmazioneoggetti.controller;

import com.progetto.programmazioneoggetti.Functions;
import com.progetto.programmazioneoggetti.model.Stats;
import com.progetto.programmazioneoggetti.model.Misurazioni;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.progetto.programmazioneoggetti.model.Metadati;


@RestController
public class RController {

    /* invece di fare un nuovo indirizzo per le stats si aggiunge
    un parametro bool che indica quale ritornare */

    @RequestMapping( value = "/", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> obj_list() throws Exception{

        return Functions.obj_list();
    }

    @RequestMapping( value = "/meta", method = RequestMethod.GET, produces = "application/json" )
    public static ArrayList<Metadati> meta_list () throws Exception {

        return Functions.obj_meta(); // finire di implementare
    }

    @RequestMapping( value = "/stats", method = RequestMethod.GET, produces = "application/json")
    public Stats obj_list_stats() throws Exception{

        return new Stats(Functions.obj_list());
    }


    @RequestMapping( value = "/obj_date_hour", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> date_hour(@RequestParam(name = "day", defaultValue = "-1") int param_day,
                                            @RequestParam(name = "month", defaultValue = "-1") int param_month,
                                            @RequestParam(name = "hour", defaultValue = "-1") int param_hour) throws Exception {

        return Functions.obj_date_hour(param_day, param_month, param_hour);
    }

    @RequestMapping( value = "/obj_date_hour_stats", method = RequestMethod.GET, produces = "application/json")
    public Stats date_hour_stats(@RequestParam(name = "day", defaultValue = "-1") int param_day,
                                 @RequestParam(name = "month", defaultValue = "-1") int param_month,
                                 @RequestParam(name = "hour", defaultValue = "-1") int param_hour) throws Exception {

        return new Stats(Functions.obj_date_hour(param_day, param_month, param_hour));
    }

}
