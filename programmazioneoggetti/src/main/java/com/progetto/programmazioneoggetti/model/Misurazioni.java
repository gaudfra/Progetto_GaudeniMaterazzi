package com.progetto.programmazioneoggetti.model;

import java.io.Serializable;

/**
 *
 * @author Sandro Materazzi
 * @author Francesco Gaudeni
 */

public class Misurazioni implements Serializable {

    //Attributi

    private double DMPS;
    private double CPC;

    private int day;
    private int month;
    private int year;

    private int hour;
    private int minute;
    private double second;

    //Metodi

    /**
     * Crea una misurazione con orario e data corrispondenti
     * @param times stringa dell'ora
     * @param date stringa della data
     * @param dmps misurazione di dmps
     * @param cpc misurazione di cpc
     */

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

    /**
     * Imposta il DMPS di una certa misurazione.
     * @param DMPS
     */
    public void setDMPS(double DMPS) {
        this.DMPS = DMPS;
    }

    /**
     * Imposta il CPC di una certa misurazione.
     * @param CPC
     */
    public void setCPC(double CPC) {
        this.CPC = CPC;
    }

    /**
     * Fornisce il giorno di una certa data.
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Imposta il giorno di una certa data.
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Fornisce il mese di una certa data.
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Imposta il mese di una certa data.
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Fornisce l'anno di una certa data.
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Imposta l'anno di una certa data.
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Fornisce l'ora di un certo orario.
     * @return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Imposta l'ora di un certo orario.
     * @param hour
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Fornisce i minuti di un certo orario.
     * @return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Imposta i minuti di un certo orario.
     * @param minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Fornisce i secondi di un certo orario.
     * @return second
     */
    public double getSecond() {
        return second;
    }

    /**
     * Imposta i secondi di un certo orario.
     * @param second
     */
    public void setSecond(double second) {
        this.second = second;
    }

    /**
     * Fornisce la misurazione di DMPS.
     * @return il valore di DMPS
     */
    public double getDMPS(){
        return DMPS;
    }

    /**
     * Fornisce la misurazione di CPC.
     * @return il valore di CPC
     */
    public double getCPC(){
        return CPC;
    }

    public boolean equalmis(Misurazioni c) {
        if (this.DMPS == c.getDMPS() && this.CPC == c.getCPC()) return true;
        else return false;

    }
}

