package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import progetto_po.Data_Ora;
import progetto_po.Misurazioni;

import static org.apache.commons.io.FileUtils.copyURLToFile;

@Controller
public class SRController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFile() throws Exception {

        File f = new File("file.csv");

        if (!f.exists() && !f.isDirectory()) {

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

                for(Object o: objA){
                    if (o instanceof JSONObject) {
                        JSONObject o1 = (JSONObject)o;
                        String format = (String)o1.get("format");
                        String urlA = (String)o1.get("url");
                        URL urlD = new URL (urlA);
                        System.out.println(format + " | " + urlD);
                        if(format.equals("http://publications.europa.eu/resource/authority/file-type/CSV")) {
                            File fname = new File ("file.csv");
                            download(urlD, fname);
                        }
                    }
                }
                System.out.println( "OK" );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Impossibile scaricare, File presente");

            File o = new File("FileDiOggetti.dat");
            if(!o.exists() && !o.isDirectory()){
                try {
                    Scanner csv = new Scanner(new BufferedReader(new FileReader ("file.csv")));
                    ArrayList<Misurazioni> lista_oggetti = new ArrayList<Misurazioni>();
                    String linea = csv.nextLine();

                    while (csv.hasNextLine()) {
                        linea = csv.nextLine();
                        String[] SeparaData = linea.split(" ");
                        String[] Separatempo = SeparaData[1].split(",");
                        Misurazioni misurazione = new Data_Ora.datetime(Separatempo[0], SeparaData[0], Separatempo[1], Separatempo[2]); // passare parametri)
                        lista_oggetti.add(misurazione);
                        System.out.println(misurazione.toString());
                    }
                    csv.close();

                    ObjectOutputStream OutputFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("FileDiOggetti.dat")));
                    OutputFile.writeObject(lista_oggetti);
                    OutputFile.close();
                }
                catch (Exception e) {
                    System.out.println("Errore di lettura" + e);
                }
            }
        }
        return "index";
    }

    public static void download(URL url, File fileName) {
        try {
            copyURLToFile(url, fileName);
        } catch (IOException e) {
            System.out.println("Errore di Input/Output" + e);
        }
    }
}