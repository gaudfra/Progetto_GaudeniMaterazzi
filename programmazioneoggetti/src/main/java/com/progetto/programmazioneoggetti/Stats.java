package com.progetto.programmazioneoggetti;

import com.progetto.programmazioneoggetti.model.Misurazioni;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Stats implements Serializable {

    double avg_dmps;
    double avg_cpc;
    double min_dmps;
    double max_dmps;
    double devstd_dmps;
    double sum_dmps;
    double min_cpc;
    double max_cpc;
    double devstd_cpc;
    double sum_cpc;
    double count;

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

    public void setMin_dmps(double min_dmps) {
        this.min_dmps = min_dmps;
    }

    public double getMax_dmps() {
        return max_dmps;
    }

    public void setMax_dmps(double max_dmps) {
        this.max_dmps = max_dmps;
    }

    public double getDevstd_dmps() {
        return devstd_dmps;
    }

    public void setDevstd_dmps(double devstd_dmps) {
        this.devstd_dmps = devstd_dmps;
    }

    public double getSum_dmps() {
        return sum_dmps;
    }

    public void setSum_dmps(double sum_dmps) {
        this.sum_dmps = sum_dmps;
    }

    public double getMin_cpc() {
        return min_cpc;
    }

    public void setMin_cpc(double min_cpc) {
        this.min_cpc = min_cpc;
    }

    public double getMax_cpc() {
        return max_cpc;
    }

    public void setMax_cpc(double max_cpc) {
        this.max_cpc = max_cpc;
    }

    public double getDevstd_cpc() {
        return devstd_cpc;
    }

    public void setDevstd_cpc(double devstd_cpc) {
        this.devstd_cpc = devstd_cpc;
    }

    public double getSum_cpc() {
        return sum_cpc;
    }

    public void setSum_cpc(double sum_cpc) {
        this.sum_cpc = sum_cpc;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getAvg_dmps() {
        return avg_dmps;
    }

    public void setAvg_dmps(double avg_dmps) {
        this.avg_dmps = avg_dmps;
    }

    public double getAvg_cpc() {
        return avg_cpc;
    }

    public void setAvg_cpc(double avg_cpc) {
        this.avg_cpc = avg_cpc;
    }


    public double devstd_dmps(int count, double avg, ArrayList<Misurazioni> lista_oggetti){

        double sum = 0;

        for(Misurazioni i: lista_oggetti){
            sum += Math.pow(avg - i.getDMPS(),2);
        }
        return sqrt(sum/count);
    }

    public double devstd_cpc(int count, double avg, ArrayList<Misurazioni> lista_oggetti){

        double sum = 0;

        for(Misurazioni i: lista_oggetti){
            sum += Math.pow(avg - i.getCPC(),2);
        }
        return sqrt(sum/count);
    }
}
