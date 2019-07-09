package progetto_po;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.util.ArrayList;
        import java.util.Scanner;

public class scanner {
    public static void scannerizza(String FileName){
        File file = new File(FileName);
        try {

            Scanner inputStream = new Scanner(file);
            inputStream.next(); // ignore the first line
            ArrayList<Data_Ora.datetime> misurazioni = new ArrayList<Data_Ora.datetime>();
            int i = 0;
            while(inputStream.hasNext()){

                String data = inputStream.next(); // gets a whole line
                String[] SeparaData = data.split(" ");
                String[] Separatempo = SeparaData[1].split(",");
                Data_Ora.datetime misurazione = new Data_Ora.datetime(Separatempo[0], SeparaData[0], Separatempo[1], Separatempo[2]);
                misurazioni.add(misurazione);
                i++;
            }
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
