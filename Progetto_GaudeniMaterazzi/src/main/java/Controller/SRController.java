package Controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import progetto_po.Data_Ora;
import progetto_po.Misurazioni;

import static org.apache.commons.io.FileUtils.copyURLToFile;

@Controller
public class SRController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSteamingFile() throws Exception {

        File f = new File("file.csv");

        if (!f.exists() && !f.isDirectory()) {

            try {

                URLConnection openConnection = new URL("{\"help\": \"Return the metadata of a dataset (package) and its resources.\\n    This overrides core package_show to deal with DCAT-AP data\\n\\n    :param str uri: the uri  of the dataset\\n\\n    :rtype: dictionary\\n\\n    \", \"success\": true, \"result\": {\"maintainer\": null, \"private\": false, \"maintainer_email\": null, \"revision_timestamp\": null, \"keywords\": [{\"display_name\": \"Science and technology\", \"name\": \"Science and technology\"}, {\"display_name\": \"Environment\", \"name\": \"Environment\"}], \"metadata_modified\": \"2019-03-27T13:23:53\", \"temporal_granularity\": \"\", \"concepts_eurovoc\": [], \"license_id\": null, \"type\": \"dataset\", \"resources\": [{\"mimetype\": null, \"mimetype_inner\": \"\", \"hash\": \"\", \"description\": \"Unvalidated 10 min averages in UTC time-base of particle number concentration [cm-3] . Particle number concentration measured with a DMPS - Differential mobility particle spectrometer, (CPC Total) from 4 nm till 10 \\u00b5m and (DMPS Total) from 10 nm till 800 nm.\", \"format\": \"http://publications.europa.eu/resource/authority/file-type/CSV\", \"url\": \"https://cidportal.jrc.ec.europa.eu/ftp/jrc-opendata/ABCIS/AtmosphericParticles/Ver2016-01-01/DMPS_Particle_Concentration_2016.csv\", \"created\": \"\", \"tracking_summary\": null, \"name\": \"Atmospheric Particles\", \"state\": \"\", \"last_modified\": \"\", \"download_total_resource\": \"1\", \"url_type\": null, \"position\": null, \"iframe_code\": \"\", \"datastore_active\": false, \"id\": \"http://data.europa.eu/88u/distribution/700f7308-7310-49d9-bc98-a83b729bd1ea\", \"resource_type\": \"http://publications.europa.eu/resource/authority/distribution-type/DOWNLOADABLE_FILE\", \"size\": \"\"}, {\"mimetype\": null, \"mimetype_inner\": null, \"hash\": \"\", \"description\": \"\", \"format\": \"http://publications.europa.eu/resource/authority/file-type/HTML\", \"url\": \"http://abc-is.jrc.ec.europa.eu/\", \"created\": null, \"tracking_summary\": null, \"name\": \"Landing page\", \"state\": null, \"last_modified\": null, \"download_total_resource\": null, \"url_type\": null, \"position\": null, \"iframe_code\": null, \"datastore_active\": false, \"id\": \"http://data.europa.eu/88u/document/163000bd-6d21-433f-85c8-61a949436c2e\", \"resource_type\": \"http://publications.europa.eu/resource/authority/documentation-type/DOCUMENTATION_MAIN\", \"size\": null}, {\"mimetype\": null, \"mimetype_inner\": null, \"hash\": \"\", \"description\": \"Putaud J, Cavalli F, Martins Dos Santos S, Dell`Acqua A. Long term trends in aerosol optical characteristics in the Po Valley, Italy. ATMOSPHERIC CHEMISTRY AND PHYSICS 14 (17); 2014. p. 9129 - 9136. JRC87490\", \"format\": \"http://publications.europa.eu/resource/authority/file-type/OP_DATPRO\", \"url\": \"https://doi.org/10.5194/acp-14-9129-2014\", \"created\": null, \"tracking_summary\": null, \"name\": \"Long term trends in aerosol optical characteristics in the Po Valley, Italy\", \"state\": null, \"last_modified\": null, \"download_total_resource\": null, \"url_type\": null, \"position\": null, \"iframe_code\": null, \"datastore_active\": false, \"id\": \"http://data.europa.eu/88u/document/49774d7e-bf9a-4c51-bd5a-31a89f7ece90\", \"resource_type\": \"http://publications.europa.eu/resource/authority/documentation-type/DOCUMENTATION_RELATED\", \"size\": null}], \"interoperability_level\": null, \"name\": \"jrc-abcis-ap-dmpspc-2016\", \"isopen\": true, \"accrual_periodicity\": \"\", \"owner_org\": \"f54d2193-d1b8-44f3-b31d-33d4d2f998b5\", \"geographical_coverage\": [\"http://publications.europa.eu/resource/authority/country/SMR\", \"http://publications.europa.eu/resource/authority/country/MNE\", \"http://publications.europa.eu/resource/authority/country/TUN\", \"http://publications.europa.eu/resource/authority/country/SVN\", \"http://publications.europa.eu/resource/authority/country/VAT\", \"http://publications.europa.eu/resource/authority/country/BIH\", \"http://publications.europa.eu/resource/authority/country/AUT\", \"http://publications.europa.eu/resource/authority/country/DZA\", \"http://publications.europa.eu/resource/authority/country/CHE\", \"http://publications.europa.eu/resource/authority/country/HRV\", \"http://publications.europa.eu/resource/authority/country/FRA\", \"http://publications.europa.eu/resource/authority/country/ITA\", \"http://publications.europa.eu/resource/authority/country/HUN\", \"http://publications.europa.eu/resource/authority/country/MCO\", \"http://publications.europa.eu/resource/authority/country/LIE\"], \"revision_id\": null, \"identifier\": \"jrc-abcis-ap-dmpspc-2016\", \"version_description\": \"\", \"extras\": [], \"relationships_as_object\": [], \"tag_string\": null, \"temporal_coverage_to\": \"\", \"num_tags\": null, \"views_total\": \"75\", \"id\": \"http://data.europa.eu/88u/dataset/jrc-abcis-ap-dmpspc-2016\", \"metadata_created\": \"2017-02-13 13:16:18.192085\", \"modified_date\": \"\", \"capacity\": \"public\", \"author\": null, \"author_email\": null, \"type_of_dataset\": [], \"state\": null, \"version\": \"\", \"creator_user_id\": null, \"status\": \"http://data.europa.eu/euodp/kos/dataset-status/Completed\", \"num_resources\": 1, \"description\": \"Measurements of particle number concentration in Ispra, Italy.\", \"title\": \"Atmospheric Particles-DMPS Particle Concentration (2016)\", \"temporal_coverage_from\": \"\", \"tracking_summary\": \"75\", \"groups\": [{\"title\": \"http://publications.europa.eu/resource/authority/data-theme/TECH\"}, {\"title\": \"http://publications.europa.eu/resource/authority/data-theme/ENVI\"}], \"relationships_as_subject\": [], \"language\": [\"http://publications.europa.eu/resource/authority/language/ENG\"], \"alternative_title\": \"\", \"url\": \"http://data.europa.eu/89h/jrc-abcis-ap-dmpspc-2016\", \"release_date\": \"2016-01-01\", \"license_title\": null, \"rdf\": null, \"license_url\": \"http://data.europa.eu/euodp/kos/licence/EuropeanCommission\", \"organization\": {\"name\": \"jrc\", \"title\": \"Joint Research Centre\"}}}").openConnection();
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
                    Scanner var = new Scanner(new BufferedReader(new FileReader ("file.csv")));
                    ArrayList<Misurazioni> obj = new ArrayList<Misurazioni>();
                    String data = var.nextLine();
                    String data2 = null;
                    String data3 = null;
                    String full = null;

                    while (var.hasNextLine()) {
                        data = var.nextLine();
                        data2 = var.nextLine();
                        if (data2.endsWith("--") && (var.nextLine()).startsWith("V:")) {
                            data2+=data3;
                        }
                        full = data + data2;

                        Matcher m = Pattern.compile("\"[^\"]*\"").matcher(full);
                        StringBuffer sb = new StringBuffer();
                        while(m.find()) {
                            m.appendReplacement(sb, m.group().replaceAll(",", "."));
                        }
                        m.appendTail(sb);

                        full = sb.toString();
                        full = full.replaceAll("\"","");
                        System.out.println(full);

                        ArrayList<String> parts = new ArrayList<String>();
                        String[] parti = full.split(",");
                        System.out.println(Arrays.toString(parti)); //prova stampa

                        for (int i = 0; i<12; i++) {
                            parts.add(parti[i]);
                        }
                        Misurazioni foo = new Data_Ora.datetime(); // passare parametri)
                        obj.add(foo);
                        System.out.println(foo.toString());
                    }
                    var.close();

                    ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("FileDiOggetti.dat")));
                    out.writeObject(obj);
                    out.close();
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