package progetto_po;

public class Misurazioni {
    private double DMPS;
    private double CPC;

    Misurazioni(String dmps, String cps) {

        this.DMPS = Double.parseDouble(dmps);
        this.CPC = Double.parseDouble(cps);
    }
}

class date extends Misurazioni {
    private int day;
    private int month;
    private int year;

    date(String date, String dmps, String cpc) {

        super(dmps, cpc);
        String[] dmy = date.split("-");
        this.day = Integer.parseInt(dmy[0]);
        this.month = Integer.parseInt(dmy[1]);
        this.year = Integer.parseInt(dmy[2]);
    }
}

class time extends date {
    private int hour;
    private int minute;
    private double second;

    time(String times, String date, String dmps, String cpc) {

        super(date, dmps, cpc);
        String[] hms = times.split(":");
        this.hour = Integer.parseInt(hms[0]);
        this.minute = Integer.parseInt(hms[1]);
        this.second = Double.parseDouble(hms[2]);
    }
}