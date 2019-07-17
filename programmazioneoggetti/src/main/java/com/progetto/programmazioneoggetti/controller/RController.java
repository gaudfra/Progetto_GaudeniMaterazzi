package com.progetto.programmazioneoggetti.controller;

import com.progetto.programmazioneoggetti.Functions;
import com.progetto.programmazioneoggetti.model.Stats;
import com.progetto.programmazioneoggetti.model.Misurazioni;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.progetto.programmazioneoggetti.model.Metadati;

@RestController
public class RController {

    @RequestMapping( value = "/", method = RequestMethod.GET, produces = "application/json")
    public ArrayList obj_list() throws Exception{

        return Functions.obj_list();
    }

    @RequestMapping( value = "/meta", method = RequestMethod.GET, produces = "application/json" )
    public static ArrayList meta_list () throws Exception {

        return Functions.obj_meta(); // finire di implementare
    }

    @RequestMapping( value = "/stats", method = RequestMethod.GET, produces = "application/json")
    public Stats obj_list_stats() throws Exception{

        return new Stats(Functions.obj_list());
    }

    @RequestMapping( value = "/date_hour", method = RequestMethod.GET, produces = "application/json")
    public ArrayList date_hour(@RequestParam(name = "day", defaultValue = "-1") int param_day,
                               @RequestParam(name = "month", defaultValue = "-1") int param_month,
                               @RequestParam(name = "hour", defaultValue = "-1") int param_hour) throws Exception {

        return Functions.obj_date_hour(param_day, param_month, param_hour);
    }

    @RequestMapping( value = "/date_hour_stats", method = RequestMethod.GET, produces = "application/json")
    public Stats date_hour_stats(@RequestParam(name = "day", defaultValue = "-1") int param_day,
                                 @RequestParam(name = "month", defaultValue = "-1") int param_month,
                                 @RequestParam(name = "hour", defaultValue = "-1") int param_hour) throws Exception {
//if ((param_day1 <  || param_day > 31) && (param_month < 1 || param_month > 12) && (param_hour < 0 || param_hour > 24) return -1;
        return new Stats(Functions.obj_date_hour(param_day, param_month, param_hour));
    }

    @RequestMapping( value = "/date_filter", method = RequestMethod.GET, produces = "application/json")
    public ArrayList date_filter(@RequestParam(name = "day", defaultValue = "-1") int param_day1,
                                 @RequestParam(name = "month", defaultValue = "-1") int param_month1,
                                 @RequestParam(name = "day2", defaultValue = "-1") int param_day2,
                                 @RequestParam(name = "month2", defaultValue = "-1") int param_month2) throws Exception {

        return Functions.date_filter(param_day1,param_month1, param_day2, param_month2);
    }

    @RequestMapping( value = "/date_filter_stats", method = RequestMethod.GET, produces = "application/json")
    public Stats date_filter_stats(@RequestParam(name = "day", defaultValue = "-1") int param_day1,
                                   @RequestParam(name = "month", defaultValue = "-1") int param_month1,
                                   @RequestParam(name = "day2", defaultValue = "-1") int param_day2,
                                   @RequestParam(name = "month2", defaultValue = "-1") int param_month2) throws Exception {
//if ((param_day1 < 1 || param_day1 > 31) && (param_day2 < 1 || param_day2 > 31) && (param_month1 < 1 || param_month1 > 12) && (param_month2 < 1 || param_month2 > 12)) return -1;
        return new Stats(Functions.date_filter(param_day1,param_month1, param_day2, param_month2));
    }

    @RequestMapping( value = "/values_filter", method = RequestMethod.GET, produces = "application/json")
    public ArrayList cpc_dmps_filter (@RequestParam(name = "min_cpc", defaultValue = "-1") double param_cpc_min,
                                      @RequestParam(name = "max_cpc", defaultValue = "-1") double param_cpc_max,
                                      @RequestParam(name = "min_dmps", defaultValue = "-1") double param_dmps_min,
                                      @RequestParam(name = "max_dmps", defaultValue = "-1") double param_dmps_max) throws Exception {

        return Functions.cpc_dmps_filter(param_cpc_min, param_cpc_max, param_dmps_min, param_dmps_max);
    }
}
