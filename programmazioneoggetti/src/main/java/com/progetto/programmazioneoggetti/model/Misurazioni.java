package com.progetto.programmazioneoggetti.model;

import java.io.Serializable;

public class Misurazioni implements Serializable {

    private double DMPS;
    private double CPC;

    private int day;
    private int month;
    private int year;

    private int hour;
    private int minute;
    private double second;

    public Misurazioni(String times, String date, String dmps, String cpc) {

        this.DMPS = Double.parseDouble(dmps);
        this.CPC = Double.parseDouble(cpc);

        String[] dmy = date.split("-");
        this.day = Integer.parseInt(dmy[2]);
        this.month = Integer.parseInt(dmy[1]);
        this.year = Integer.parseInt(dmy[0]);

        String[] hms = times.split(":");
        this.hour = Integer.parseInt(hms[0]);
        this.minute = Integer.parseInt(hms[1]);
        this.second = Double.parseDouble(hms[2]);
    }

    public Misurazioni(double dmps, double cpc, String times, String date) {

        this.DMPS = dmps;
        this.CPC = cpc;

        String[] dmy = date.split("-");
        this.day = Integer.parseInt(dmy[0]);
        this.month = Integer.parseInt(dmy[1]);
        this.year = Integer.parseInt(dmy[2]);

        String[] hms = times.split(":");
        this.hour = Integer.parseInt(hms[0]);
        this.minute = Integer.parseInt(hms[1]);
        this.second = Double.parseDouble(hms[2]);
    }

    public void setDMPS(double DMPS) {
        this.DMPS = DMPS;
    }

    public void setCPC(double CPC) {
        this.CPC = CPC;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public double getDMPS(){
        return DMPS;
    }

    public double getCPC(){
        return CPC;
    }

    public boolean equalmis(Misurazioni c) {
        if (this.DMPS == c.getDMPS() && this.CPC == c.getCPC()) return true;
        else return false;

    }
}

