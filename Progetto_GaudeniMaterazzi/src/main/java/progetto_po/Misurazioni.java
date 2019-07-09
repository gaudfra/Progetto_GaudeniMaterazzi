package progetto_po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;

public class Misurazioni {
    private double DMPS;
    private double CPC;

    Misurazioni(String dmps, String cpc) {

        this.DMPS = Double.parseDouble(dmps);
        this.CPC = Double.parseDouble(cpc);
    }

    @JsonIgnore
    public int getDMPS(){
        return DMPS;
    }

    @JsonIgnore
    public int getCPC(){
        return CPC;
    }

    @JsonIgnore
    public boolean equalmis(Misurazioni c) {
        if (this.DMPS == c.getDMPS() && this.CPC == c.getCPC()) return true;
        else return false;

    }
}

class datetime extends Misurazioni {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private double second;

    datetime(String times, String date, String dmps, String cpc) {

        super(dmps, cpc);
        String[] dmy = datetime.split("-");
        this.day = Integer.parseInt(dmy[0]);
        this.month = Integer.parseInt(dmy[1]);
        this.year = Integer.parseInt(dmy[2]);
        String[] hms = datetime.split(":");
        this.hour = Integer.parseInt(hms[0]);
        this.minute = Integer.parseInt(hms[1]);
        this.second = Double.parseDouble(hms[2]);
    }


    @JsonIgnore
    public int getDay(){
        return day;
    }
    @JsonIgnore
    public int getMonth(){
        return month;
    }

    @JsonIgnore
    public int getYear(){
        return year;
    }


    @JsonIgnore
    public int getHour(){
        return hour;
    }
    @JsonIgnore
    public int getMinute(){
        return minute;
    }
    @JsonIgnore
    public double getSecond(){
        return second;
    }

    @JsonIgnore
    public boolean equaldatetime(datetime c) {
        if (this.day == c.getDay() && this.month == c.getMonth() && this.year == c.getYear() && this.hour == c.getHour() && this.minute == c.getMinute() && this.second == getSecond()) return true;
        else return false;

    }
}






}


