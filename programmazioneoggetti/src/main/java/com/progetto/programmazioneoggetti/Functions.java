package com.progetto.programmazioneoggetti;

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

public class Functions {

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
                            in2.readLine();

                            String inputLine;
                            while ((inputLine = in2.readLine()) != null) {

                                try {

                                    String[] SeparaData = inputLine.split(" ");
                                    String[] Separatempo = SeparaData[1].split(",");
                                    Misurazioni misurazione = new Misurazioni(Separatempo[0], SeparaData[0], Separatempo[1], Separatempo[2]); // passare parametri)
                                    lista_oggetti.add(misurazione);

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

    public static ArrayList<Misurazioni> obj_day (String param_day) throws Exception {

        ArrayList<Misurazioni> giorno = new ArrayList<>();
        int DAY = Integer.parseInt(param_day);

        for(Misurazioni i : obj_list()) {

            if (i.getDay() == DAY) {
                giorno.add(i);
            }
        }

        return giorno;
    }

    public static ArrayList<Misurazioni> obj_date (String param_date) throws Exception{

        ArrayList<Misurazioni> giorni = new ArrayList<>();
        String[] dmy = param_date.split("-");
        int DAY = Integer.parseInt(dmy[2]);
        int MONTH = Integer.parseInt(dmy[1]);
        int YEAR = Integer.parseInt(dmy[0]);

        for(Misurazioni i : obj_list()) {

            if (i.getDay() == DAY && i.getMonth() == MONTH && i.getYear() == YEAR) {
                giorni.add(i);
            }
        }

        return giorni;
    }
}
