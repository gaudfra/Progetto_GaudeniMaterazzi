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

    @RequestMapping( value = "/date_filters", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> filtridate(@RequestParam(name = "day", defaultValue = "-1") int param_day1,
                                         @RequestParam(name = "month", defaultValue = "-1") int param_month1,
                                         @RequestParam(name = "day2", defaultValue = "-1") int param_day2,
                                         @RequestParam(name = "month2", defaultValue = "-1") int param_month2) throws Exception {
        return Functions.filtersdate(param_day1,param_month1, param_day2, param_month2);
    }

    @RequestMapping( value = "/filterscpc", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> filtrimisurecpc (@RequestParam(name = "cpc", defaultValue = "-1") double param_cpc,
                                                @RequestParam(name = "dmps", defaultValue = "-1") double param_dmps) throws Exception {
        return Functions.filterscpc(param_cpc);
    }
    @RequestMapping( value = "filtersdmps", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Misurazioni> filtrimisuredmps ( @RequestParam(name = "dmps", defaultValue = "-1") double param_dmps) throws Exception {
       return Functions.filtersdmps(param_dmps);
    }

}
