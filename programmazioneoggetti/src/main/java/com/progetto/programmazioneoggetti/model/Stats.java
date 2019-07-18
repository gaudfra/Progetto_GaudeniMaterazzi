package com.progetto.programmazioneoggetti.model;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

/**
 *
 * @author Sandro Materazzi
 * @author Francesco Gaudeni
 */


public class Stats implements Serializable {

    //Attributi

    private double avg_dmps;
    private double avg_cpc;
    private double min_dmps;
    private double max_dmps;
    private double devstd_dmps;
    private double sum_dmps;
    private double min_cpc;
    private double max_cpc;
    private double devstd_cpc;
    private double sum_cpc;
    private double count;

    //Metodi


    /**
     * Calcola tutte le statistiche per la lista di oggetti che gli viene passata.
     * @param lista_oggetti lista delle misurazioni su cui fare le statistiche
     */
    public Stats(ArrayList<Misurazioni> lista_oggetti){

        double cpc = 0;
        double dmps = 0;
        double max_dmps = 0;
        double max_cpc = 0;

        Misurazioni primo_obj = lista_oggetti.get(0);

       double min_cpc = primo_obj.getCPC();
       double min_dmps = primo_obj.getDMPS();

        int j = 0;

        for( Misurazioni i: lista_oggetti){

            if(i.getDMPS() > max_dmps){

                max_dmps = i.getDMPS();
            }

            if(i.getCPC() > max_cpc){

                max_cpc = i.getCPC();
            }

            if(i.getDMPS() < min_dmps){

                min_dmps = i.getDMPS();
            }

            if(i.getCPC() < min_cpc){

                min_cpc = i.getCPC();
            }

            cpc += i.getCPC();
            dmps += i.getDMPS();
            j++;
        }

        this.avg_dmps = dmps/j;
        this.avg_cpc = cpc/j;
        this.sum_dmps = dmps;
        this.sum_cpc = cpc;
        this.min_cpc = min_cpc;
        this.min_dmps = min_dmps;
        this.max_cpc = max_cpc;
        this.max_dmps = max_dmps;
        this.devstd_cpc = devstd_cpc(j, avg_cpc, lista_oggetti);
        this.devstd_dmps = devstd_dmps(j, avg_dmps, lista_oggetti);
        this.count = j;
    }

    public double getMin_dmps() {
        return min_dmps;
    }

    /**
     * Imposta il minimo valore di dmps.
     * @param min_dmps minimo di dmps
     */
    public void setMin_dmps(double min_dmps) {
        this.min_dmps = min_dmps;
    }

    /**
     * Fornisce il massimo di dmps.
     * @return max_dmps
     */
    public double getMax_dmps() {
        return max_dmps;
    }

    /**
     * Imposta il massimo valore di dmps.
     * @param max_dmps massimo di dmps
     */
    public void setMax_dmps(double max_dmps) {
        this.max_dmps = max_dmps;
    }

    /**
     * Fornisce la deviazione standard di dmps.
     * @return devstd_dmps
     */
    public double getDevstd_dmps() {
        return devstd_dmps;
    }

    /**
     * Imposta la deviazione standard di dmps.
     * @param devstd_dmps deviazione standard di dmps
     */
    public void setDevstd_dmps(double devstd_dmps) {
        this.devstd_dmps = devstd_dmps;
    }

    /**
     * Fornisce la somma dei dmps.
     * @return sum_dmps
     */
    public double getSum_dmps() {
        return sum_dmps;
    }

    /**
     * Imposta la somma dei valori di dmps.
     * @param sum_dmps somma dei valori di dmps
     */
    public void setSum_dmps(double sum_dmps) {
        this.sum_dmps = sum_dmps;
    }

    /**
     * Fornisce il minimo di cpc.
     * @return min_cpc
     */
    public double getMin_cpc() {
        return min_cpc;
    }

    /**
     * Imposta il minimo di cpc.
     * @param min_cpc minimo di cpc
     */
    public void setMin_cpc(double min_cpc) {
        this.min_cpc = min_cpc;
    }

    /**
     * Fornisce il massimo di cpc.
     * @return max_cpc
     */
    public double getMax_cpc() {
        return max_cpc;
    }

    /**
     * Imposta il massimo di cpc.
     * @param max_cpc massimo di cpc
     */
    public void setMax_cpc(double max_cpc) {
        this.max_cpc = max_cpc;
    }

    /**
     * Fornisce la deviazione standard di cpc.
     * @return devstd_cpc
     */
    public double getDevstd_cpc() {
        return devstd_cpc;
    }

    /**
     * Imposta la deviazione standard di cpc.
     * @param devstd_cpc deviazione standard di cpc
     */
    public void setDevstd_cpc(double devstd_cpc) {
        this.devstd_cpc = devstd_cpc;
    }

    /**
     * Fornisce la somma dei cpc.
     * @return sum_cpc
     */
    public double getSum_cpc() { return sum_cpc; }

    /**
     * Imposta la somma dei valori di cpc.
     * @param sum_cpc somma dei valori di cpc
     */
    public void setSum_cpc(double sum_cpc) {
        this.sum_cpc = sum_cpc;
    }

    /**
     * Fornisce il contatore.
     * @return count
     */
    public double getCount() {
        return count;
    }

    /**
     * Imposta il contatore.
     * @param count contatore
     */
    public void setCount(double count) {
        this.count = count;
    }

    /**
     * Fornisce la media dei dmps.
     * @return avg_dmps
     */
    public double getAvg_dmps() {
        return avg_dmps;
    }

    /**
     * Imposta la media del parametro dmps
     * @param avg_dmps media di dmps
     */
    public void setAvg_dmps(double avg_dmps) {
        this.avg_dmps = avg_dmps;
    }

    /**
     * Fornisce la media dei cpc.
     * @return avg_cpc
     */
    public double getAvg_cpc() {
        return avg_cpc;
    }

    /**
     * Imposta la media del parametro cpc.
     * @param avg_cpc media di cpc
     */
    public void setAvg_cpc(double avg_cpc) {
        this.avg_cpc = avg_cpc;
    }


    /**
     * Calcola la deviazione standard di dmps.
     * @param count contatore
     * @param avg   media
     * @param lista_oggetti lista delle misurazioni
     * @return sqrt(sum/count)
     */
    public double devstd_dmps(int count, double avg, ArrayList<Misurazioni> lista_oggetti){

        double sum = 0;

        for(Misurazioni i: lista_oggetti){
            sum += Math.pow(avg - i.getDMPS(),2);
        }
        return sqrt(sum/count);
    }

    /**
     * Calcola la deviazione standard di cpc.
     * @param count    contatore
     * @param avg      media
     * @param lista_oggetti lista delle misurazioni
     * @return sqrt(sum/count)
     */
    public double devstd_cpc(int count, double avg, ArrayList<Misurazioni> lista_oggetti){

        double sum = 0;

        for(Misurazioni i: lista_oggetti){
            sum += Math.pow(avg - i.getCPC(),2);
        }
        return sqrt(sum/count);
    }
}
