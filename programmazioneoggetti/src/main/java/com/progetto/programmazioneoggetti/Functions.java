package com.progetto.programmazioneoggetti;

import com.progetto.programmazioneoggetti.model.Metadati;
import com.progetto.programmazioneoggetti.model.Misurazioni;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.lang.reflect.Field;

/**
 *
 * @author Sandro Materazzi
 * @author Francesco Gaudeni
 */

public class Functions {

    //Metodi

    /**
     * Stampa la lista di tutti gli oggetti di tipo Misurazioni.
     * @return lista_oggetti
     */
    public static ArrayList<Misurazioni> obj_list () throws Exception {

        ArrayList<Misurazioni> lista_oggetti = new ArrayList<>();
        try {

            URLConnection openConnection = new URL("http://data.europa.eu/euodp/data/api/3/action/package_show?id=jrc-abcis-ap-dmpspc-2016").openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream in = openConnection.getInputStream();

            String data = "";
            String line = "";
            try {
                InputStreamReader inR = new InputStreamReader(in);
                BufferedReader buf = new BufferedReader(inR);

                while ((line = buf.readLine()) != null) {
                    data += line;
                    System.out.println(line);
                }

            } finally {
                in.close();
            }

            JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
            JSONObject objI = (JSONObject) (obj.get("result"));
            JSONArray objA = (JSONArray) (objI.get("resources"));

            for (Object o : objA) {
                if (o instanceof JSONObject) {
                    JSONObject o1 = (JSONObject) o;
                    String format = (String) o1.get("format");
                    String urlA = (String) o1.get("url");
                    URL urlD = new URL(urlA);
                    System.out.println(format + " | " + urlD);

                    if (format.contains("CSV")) {
                        try {

                            URLConnection openConnection2 = new URL(urlA).openConnection();
                            BufferedReader in2 = new BufferedReader(new InputStreamReader(openConnection2.getInputStream()));
                            in2.readLine(); // salto prima riga

                            String inputLine;
                            while ((inputLine = in2.readLine()) != null) {

                                try {

                                    String[] SeparaData = inputLine.split(" ");
                                    String[] Separatempo = SeparaData[1].split(",");
                                    Misurazioni misurazione = new Misurazioni(Separatempo[0], SeparaData[0], Separatempo[1], Separatempo[2]); // passare parametri
                                    if (misurazione.getDMPS() >= 0 && misurazione.getCPC() >= 0){
                                        lista_oggetti.add(misurazione);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            in.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("OK");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista_oggetti;
    }

    /**
     * Stampa la lista di tutti gli oggetti di tipo Misurazioni, filtrati secondo una certa data e ora, gestendo eventuali parametri errati.
     * @param param_day giorno richiesto
     * @param param_month mese richiesto
     * @param param_hour ora richiesta
     * @return data
     */
    public static ArrayList<Misurazioni> obj_date_hour (int param_day, int param_month, int param_hour) throws Exception {

        ArrayList<Misurazioni> data = new ArrayList<>();

        if((param_day >= 1 && param_day <= 31) && (param_month < 1 || param_month > 12 ) && (param_hour >= 0 && param_hour < 24)){
            System.out.println("Parametro di ricerca del mese sbagliato");
            data = day_hour(param_day, param_hour);  // ritorno le misurazioni con quel giorno e quell'ora
        }

        else if((param_day < 1 || param_day > 31 ) && (param_month >= 1 && param_month <= 12) && (param_hour >= 0 && param_hour < 24)){
            System.out.println("Parametri di ricerca del giorno sbagliato");
            data = month_hour(param_month, param_hour); // ritorno le misurazioni con quel mese e quell'ora
        }

        else if ((param_day>=1 && param_day <=31) && (param_month >= 1 && param_month <= 12) && (param_hour < 0 || param_hour > 24)) {
            System.out.println("Parametro di ricerca dell'ora sbagliato");
            data = date(param_day, param_month);  //ritorno le misurazioni con quel giorno e quel mese

        }
        else if ((param_day < 1 || param_day > 31) && (param_month < 1 || param_month > 12) && (param_hour >= 0 && param_hour < 24)){
            System.out.println("Parametri di ricerca del giorno e del mese sbagliati");
            data = hour(param_hour);  //ritorno tutte le misurazioni con quell'ora
        }

        else if((param_day < 1 || param_day > 31) && (param_month >= 1 && param_month <= 12) && (param_hour < 0 || param_hour > 24)){
            System.out.println("Parametri di ricerca del giorno e dell'ora sbagliati");
            data = month(param_month);  // ritorno tutte le misurazioni con quel mese
        }

        else if((param_day >= 1 && param_day <= 31) && (param_month < 1 || param_month > 12) && (param_hour < 0 || param_hour > 24)){
            System.out.println("Parametri di ricerca del mese e dell'ora sbagliati");
            data = day(param_day); // ritorno le misurazioni con quel giorno
        }

        else if ((param_day >= 1 && param_day <= 31) && (param_month >= 1 && param_month <= 12) && (param_hour >= 0 && param_hour < 24)){
            System.out.println("Parametri di ricerca tutti giusti");
            data = date_hour(param_day, param_month, param_hour); //ritorno tutte le misurazioni con quella specifica data e ora
        }
        else {

            System.out.println("Parametri di ricerca tutti sbagliati, ripetere la ricerca");
        }

        return data;
    }


    /**
     * Stampa la lista di tutti i metadati.
     * @return meta
     */
    public static ArrayList<Metadati> obj_meta() {

        ArrayList<Metadati> meta = new ArrayList<>();
        int i = 0;

        for (Field field: Misurazioni.class.getDeclaredFields()) {

           meta.add(new Metadati(field.getName(), field.getType().toString()));
           i++;
        }
        return meta;
}

    /**
     * Stampa la lista di tutti gli oggetti di tipo Misurazioni, compresi tra una data iniziale ed una finale, gestendo eventuali parametri errati
     * @param param_day1 limite inferiore giorno
     * @param param_month1  limite inferiore mese
     * @param param_day2 limite superiore giorno
     * @param param_month2 limite superiore mese
     * @return data
     */
    public static ArrayList<Misurazioni> date_filter (int param_day1, int param_month1, int param_day2, int param_month2) throws Exception {

        ArrayList<Misurazioni> data = new ArrayList<>();

        if ((param_month2 < 1 || param_month2 > 12) && (param_month1 < 1 || param_month1 > 12)) {

            System.out.println("Impossibile ricerca alternativa, entrambi i mesi sono errati");

        }

        else if ((param_day1 < 1 || param_day1 > 31) && (param_month1 >= 1 && param_month1 <= 12) && (param_day2 >= 1 && param_day2 <= 31) && (param_month2 >= 1 && param_month2 <= 12)) {

            System.out.println("Solo il limite inferiore day è errato"); //ritorna le misurazioni dal primo mese fino al limite superiore

            for (Misurazioni i: obj_list()){

                if ( i.getMonth() >= param_month1 && ((i.getDay() <= param_day2 && i.getMonth() == param_month2) || (i.getDay() >= param_day2 && i.getMonth() < param_month2) || (i.getDay() <= param_day2 && i.getMonth() <= param_month2))){

                    data.add(i);
                }
            }
        }

        else if ((param_month1 < 1 || param_month1 > 12) && (param_day2 >= 1 && param_day2 <= 31) && (param_month2 >= 1 && param_month2 <= 12)) {

            System.out.println("Limite inferiore month errato");

            if (param_day1 < 1 || param_day1 > 31 ) System.out.println("Limite inferiore giorno errato"); //ritorna le misurazioni dal primo giorno dell'anno fino al limite superiore
            if (param_day1 >= 1 && param_day1 <= 31)  System.out.println("Limite inferiore giorno giusto");


            for (Misurazioni i : obj_list()) {

                    if ((i.getDay() <= param_day2 && i.getMonth() == param_month2) || (i.getDay() >= param_day2 && i.getMonth() < param_month2) || (i.getDay() <= param_day2 && i.getMonth() <= param_month2)) {

                        data.add(i);
                    }
                }

        }

        else if ((param_day1 >= 1 && param_day1 <= 31) && (param_month1 >= 1 && param_month1 <= 12) && (param_day2 < 1 || param_day2 > 31) && (param_month2 >= 1 && param_month2 <= 12)) {

            System.out.println("Limite superiore day errato"); //ritorna le misurazioni dopo il giorno iniziale e fino a tutto il mese finale

            for (Misurazioni i: obj_list()){

                if (((i.getDay() >= param_day1 && i.getMonth() == param_month1) || (i.getDay() <= param_day1 && i.getMonth() > param_month1) || (i.getDay() >= param_day1 && i.getMonth() >= param_month1)) && (i.getMonth() <= param_month2)) {

                    data.add(i);
                }
            }
        }

        else if ((param_day1 >= 1 && param_day1 <= 31) && (param_month1 >= 1 && param_month1 <= 12) && (param_month2 < 1 || param_month2 > 12)) {

            System.out.println("Limite superiore month errato"); //ritorna le misurazioni a partire dal limite inferiore fino alla fine dell'anno

            if (param_day2 < 1 || param_day2 > 31) System.out.println("Limite superiore giorno errato");  //
            if (param_day2 >= 1 && param_day2 <= 31)  System.out.println("Limite superiore giorno giusto"); //

            for (Misurazioni i: obj_list()){

                if ((i.getDay() >= param_day1 && i.getMonth() == param_month1) || (i.getDay() <= param_day1 && i.getMonth() > param_month1) || (i.getDay() >= param_day1 && i.getMonth() >= param_month1)) {

                    data.add(i);
                }
            }
        }

        else if ((param_day1 < 1 || param_day1 > 31) && (param_day2 < 1 || param_day2 > 31) ) {

            System.out.println("Limiti day errati");

            if ((param_month1 >= 1 && param_month1 <= 12) && (param_month2 < 1 || param_month2 > 12 )){

                System.out.println("Unico parametro giusto month1"); //ritorna le misurazioni a partire da month1

                for (Misurazioni i: obj_list()){

                    if (i.getMonth() >= param_month1){

                        data.add(i);
                    }
                }

            }

            else if ((param_month1 < 1 || param_month1 > 12) && (param_month2 >=1 && param_month2 <= 12)) {

                System.out.println("Unico parametro giusto month2"); //ritorna le misurazioni dal primo giorno dell'anno fino al secondo mese incluso

                for (Misurazioni i: obj_list()){

                    if (i.getMonth() <= param_month2){

                        data.add(i);
                    }
                }

            }

            else { //if ((param_month1 >=1 && param_month1 <=12) && (param_month2 >= 1 && param_month2 <= 12)) {

                System.out.println("Entrambi i mesi sono giusti"); //ritorn le misurazioni del primo mese e fino a tutto il mese finale

                for (Misurazioni i: obj_list()) {

                    if (i.getMonth() >= param_month1 && i.getMonth() <= param_month2) {

                    data.add(i);

                    }

                }
            }
        }

        else if ((param_day1 < 1 || param_day1 > 31) && (param_month1>= 1 && param_month1<= 12) && (param_month2 < 1 || param_month2 > 12) && (param_day2 >=1 && param_day2 <= 31 )) {

                System.out.println("Day1 e Month2 sono errati"); //ritorna le misurazioni del primo mese e fino alla fine dell'anno

                for (Misurazioni i: obj_list()) {

                    if (i.getMonth() >= param_month1) {

                        data.add(i);

                    }

                }


        }

        else if ((param_day1 >= 1 && param_day1 <= 31) && (param_month1 < 1 || param_month1 > 12) && (param_month2 >= 1 && param_month2 <= 12) && (param_day2 < 1 || param_day2 > 31 )) {

            System.out.println("Month1 e Day2 sono errati"); // ritorna le misurazioni dal primo giorno dell'anno e fino al secondo mese

            for (Misurazioni i: obj_list()) {

                if (i.getMonth() <= param_month2) {

                    data.add(i);

                }

            }
        }

        else if ((param_day1 > param_day2 && param_month1 == param_month2) || (param_month1 > param_month2)) {

            System.out.println("Limite inferiore maggiore del limite superiore");
        }

        else {

            System.out.println("Parametri tutti corretti");

            for(Misurazioni i : obj_list()){

                if (((i.getDay() >= param_day1 && i.getMonth() == param_month1) || (i.getDay() <= param_day1 && i.getMonth() > param_month1) || (i.getDay() >= param_day1 && i.getMonth() >= param_month1)) &&
                    ((i.getDay() <= param_day2 && i.getMonth() == param_month2) || (i.getDay() >= param_day2 && i.getMonth() < param_month2) || (i.getDay() <= param_day2 && i.getMonth() <= param_month2))) {

                    data.add(i);
                }
            }
        }

        return data;
    }

    /**
     * Stampa la lista di tutti gli oggetti di tipo Misurazioni, filtrati per un valore minimo e massimo di cpc e dmps, gestendo eventuali parametri errati
     * @param param_cpc_min limite inferiore cpc
     * @param param_cpc_max limite superiore cpc
     * @param param_dmps_min limite inferiore dmps
     * @param param_dmps_max limite superiore dmps
     * @return data
     */
    public static ArrayList<Misurazioni> cpc_dmps_filter (double param_cpc_min, double param_cpc_max, double param_dmps_min, double param_dmps_max) throws Exception {

        ArrayList<Misurazioni> data = new ArrayList<>();

        if ((param_cpc_min < 0 && param_cpc_max < 0) && (param_dmps_min < 0 && param_dmps_max < 0)) {

            System.out.println("Parametri tutti errati");
        }

        else if(((param_cpc_max < 0 && param_cpc_min >= 0) || (param_cpc_min < 0 && param_cpc_max >= 0) || (param_cpc_min < 0 && param_cpc_max < 0))  && (param_dmps_min >= 0 && param_dmps_max >= 0 && param_dmps_min < param_dmps_max)) {

            if (param_cpc_max < 0 && param_cpc_min >= 0) {

                System.out.println("Parametro max cpc errato"); // ritorna le misurazioni senza considerare il cpc massimo

                for (Misurazioni i : obj_list()) {

                    if ((i.getCPC() >= param_cpc_min) && (i.getDMPS() >= param_dmps_min && i.getDMPS() <= param_dmps_max)) {

                        data.add(i);
                    }
                }

            }

            if (param_cpc_min < 0 && param_cpc_max >= 0) {

                System.out.println("Parametro min cpc errato"); // ritorna le misurazioni senza considerare il cpc minimo

                for (Misurazioni i : obj_list()) {

                    if ((i.getCPC() <= param_cpc_max) && (i.getDMPS() >= param_dmps_min && i.getDMPS() <= param_dmps_max)) {

                        data.add(i);
                    }
                }
            }

            if (param_cpc_min < 0 && param_cpc_max < 0) {

                System.out.println("Parametro cpc errato"); //ritorna le misurazioni senza considerare il cpc

                for (Misurazioni i : obj_list()) {

                    if (i.getDMPS() >= param_dmps_min && i.getDMPS() <= param_dmps_max) {

                        data.add(i);
                    }
                }
            }
        }

        else if(((param_dmps_min < 0 && param_dmps_max >= 0) || (param_dmps_max < 0 && param_dmps_min >= 0) || (param_dmps_min < 0 && param_dmps_max < 0)) && (param_cpc_min >= 0 && param_cpc_max >= 0 && param_cpc_min < param_cpc_max)) {

            if (param_dmps_min < 0 && param_dmps_max >= 0) {

                System.out.println("Parametro min dmps errato"); // ritorna le misurazioni senza considerare il dmps minimo

                for (Misurazioni i : obj_list()) {

                    if ((i.getDMPS() <= param_dmps_max) && (i.getCPC() >= param_cpc_min && i.getCPC() <= param_cpc_max)) {

                        data.add(i);
                    }
                }
            }

            if (param_dmps_max < 0 && param_dmps_min >= 0) {

                System.out.println("Parametro max dmps errato"); // ritorna le misurazioni senza considerare il dmps massimo

                for (Misurazioni i : obj_list()) {

                    if ((i.getDMPS() >= param_dmps_min) && (i.getCPC() >= param_cpc_min && i.getCPC() <= param_cpc_max)) {

                        data.add(i);
                    }
                }

            }

            if (param_dmps_min < 0 && param_dmps_max < 0) {

                System.out.println("Parametro dmps errato"); //ritorna le misurazioni senza considerare il dmps

                for (Misurazioni i : obj_list()) {

                    if((i.getCPC() >= param_cpc_min) && (i.getCPC() <= param_cpc_max)) {

                        data.add(i);
                    }
                }

            }
        }

        else if((param_cpc_max < param_cpc_min) && (param_dmps_min >= 0 && param_dmps_max >= 0 && param_dmps_min < param_dmps_max)) {

            System.out.println("Max < min in cpc"); //ritorna le misurazioni senza considerare cpc

            for (Misurazioni i : obj_list()) {

                if (i.getDMPS() >= param_dmps_min && i.getDMPS() <= param_dmps_max) {

                    data.add(i);
                }
            }

        }

        else if((param_dmps_max < param_dmps_min) && (param_cpc_min >= 0 && param_cpc_max >= 0 && param_cpc_min < param_cpc_max)) {

            System.out.println("Max < min in dmps"); //ritorna le misurazioni senza considerare dmps

            for (Misurazioni i : obj_list()) {

                if (i.getCPC() >= param_cpc_min && i.getCPC() <= param_cpc_max) {

                    data.add(i);
                }
            }

        }

        else if ((param_cpc_min < 0 && param_cpc_max >= 0) && (param_dmps_min < 0 && param_dmps_max >= 0)) {

            System.out.println("Errati entrambi i valori minimi"); //ritorna le misurazioni senza considerare i valori minimi

            for (Misurazioni i : obj_list()) {

                if (i.getDMPS() <= param_dmps_max && i.getCPC() <= param_cpc_max) {

                    data.add(i);
                }
            }

        }

        else if ((param_cpc_min >= 0 && param_cpc_max < 0) && (param_dmps_min >= 0 && param_dmps_max < 0)) {

            System.out.println("Errati entrambi i valori massimi"); //ritorna le misurazioni senza considerare i valori massimi

            for (Misurazioni i : obj_list()) {

                if (i.getDMPS() >= param_dmps_min && i.getCPC() >= param_cpc_min) {

                    data.add(i);
                }
            }

        }

        else if ((param_cpc_min < 0 && param_cpc_max < 0) && (param_dmps_min < 0 && param_dmps_max >= 0)) {

            System.out.println("Solo dmps max corretto"); //ritorna le misurazioni minori di dmps_max

            for (Misurazioni i : obj_list()) {

                if (i.getDMPS() <= param_dmps_max) {

                    data.add(i);
                }
            }

        }

        else if ((param_cpc_min < 0 && param_cpc_max < 0) && (param_dmps_min >= 0 && param_dmps_max < 0)) {

            System.out.println("Solo dmps min corretto"); //ritorna le misurazioni maggiori di dmps_min

            for (Misurazioni i : obj_list()) {

                if (i.getDMPS() >= param_dmps_min) {

                    data.add(i);
                }
            }

        }

        else if ((param_cpc_min < 0 && param_cpc_max >= 0) && (param_dmps_max < 0)) {

            if (param_dmps_min < 0) {

                System.out.println("Solo cpc_max corretto"); //ritorna le misurazioni minori di cpc_max

                for (Misurazioni i : obj_list()) {

                    if (i.getCPC() <= param_cpc_max) {

                        data.add(i);
                    }
                }
            }

            else {

                System.out.println("cpc_max e dmps_min corretti"); //ritorna le misurazioni minori di cpc_max e maggiori di dmps_min

                for (Misurazioni i : obj_list()) {

                    if ((i.getDMPS() >= param_dmps_min) && (i.getCPC() <= param_cpc_max)) {

                        data.add(i);
                    }
                }
            }

        }

        else if ((param_cpc_min >= 0 && param_cpc_max < 0) && (param_dmps_min < 0 )) {

            if (param_dmps_max < 0) {

                System.out.println("Solo cpc_min corretto"); //ritorna le misurazioni maggiori di cpc_min

                for (Misurazioni i : obj_list()) {

                    if (i.getCPC() >= param_cpc_min) {

                        data.add(i);
                    }
                }
            }

            else {

                System.out.println("Solo cpc_min e dmps_max corretti"); //ritorna le misurazioni maggiori di cpc_min e minori di dmps_max

                for (Misurazioni i : obj_list()) {

                    if ((i.getCPC() >= param_cpc_min) && (i.getDMPS() <= param_dmps_max)) {

                        data.add(i);
                    }
                }
            }
        }

        else {

            System.out.println("Parametri tutti corretti");

            for (Misurazioni i : obj_list()) {

                if ((i.getCPC() >= param_cpc_min && i.getCPC() <= param_cpc_max) && (i.getDMPS() >= param_dmps_min && i.getDMPS() <= param_dmps_max)) {

                    data.add(i);
                }
            }
        }

        return data;
    }


    /* FUNZIONI BASE DI QUELLE SOPRA */

    public static ArrayList<Misurazioni> day(int param_day) throws Exception{

        ArrayList<Misurazioni> data = new ArrayList<>();

        for(Misurazioni i : obj_list()) {

            if (i.getDay() == param_day) {
                data.add(i);
            }
        }
        return data;
    }

    public static ArrayList<Misurazioni> month(int param_month) throws Exception{

        ArrayList<Misurazioni> data = new ArrayList<>();

        for(Misurazioni i : obj_list()) {

            if (i.getMonth() == param_month ) {
                data.add(i);
            }
        }
        return data;
    }

    public static ArrayList<Misurazioni> date(int param_day, int param_month) throws Exception{

        ArrayList<Misurazioni> data = new ArrayList<>();

        for(Misurazioni i : obj_list()) {

            if (i.getDay() == param_day && i.getMonth() == param_month ) {
                data.add(i);
            }
        }
        return data;
    }

    public static ArrayList<Misurazioni> day_hour(int param_day, int param_hour) throws Exception{

        ArrayList<Misurazioni> data = new ArrayList<>();

        for(Misurazioni i : obj_list()) {

            if (i.getDay() == param_day && i.getHour() == param_hour ) {

                data.add(i);
            }
        }
        return data;
    }

    public static ArrayList<Misurazioni> month_hour(int param_month, int param_hour) throws Exception{

        ArrayList<Misurazioni> data = new ArrayList<>();

        for(Misurazioni i : obj_list()) {

            if (i.getMonth() == param_month && i.getHour() == param_hour ) {

                data.add(i);
            }
        }
        return data;
    }

    public static ArrayList<Misurazioni> date_hour(int param_day, int param_month, int param_hour) throws Exception{

        ArrayList<Misurazioni> data = new ArrayList<>();

        for(Misurazioni i : obj_list()) {

            if (i.getDay() == param_day && i.getMonth() == param_month && i.getHour() == param_hour ) {

                data.add(i);
            }
        }
        return data;
    }

    public static ArrayList<Misurazioni> hour(int param_hour) throws Exception{

        ArrayList<Misurazioni> data = new ArrayList<>();

        for(Misurazioni i : obj_list()) {

            if (i.getHour() == param_hour ) {
                data.add(i);
            }
        }
        return data;
    }
}
