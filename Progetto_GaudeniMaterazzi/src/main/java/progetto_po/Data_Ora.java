package progetto_po;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Data_Ora {

    public static class datetime extends Misurazioni implements Serializable {
        private int day;
        private int month;
        private int year;
        private int hour;
        private int minute;
        private double second;

        public datetime(String times, String date, String dmps, String cpc) {

            super(dmps, cpc);

            String[] dmy = date.split("-");
            this.day = Integer.parseInt(dmy[0]);
            this.month = Integer.parseInt(dmy[1]);
            this.year = Integer.parseInt(dmy[2]);

            String[] hms = times.split(":");
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
