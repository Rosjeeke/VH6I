
package ectl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rogier
 */
public class Main {
    
    private ExcelExtract excel1;
    private ExcelExtract excel2;
    private AccessExtractor access1;
    private AccessExtractor access2;
    private CSVExtractor csv1;
    private CSVExtractor csv2;
       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        //Aanmaken nieuwe Database met de naam van de databank 
        MysqlDatabase db1 = new MysqlDatabase("myseconddwh");
        
        //Aanmaken van de verschillende extract klassen.
        ExcelExtract excel1 = new ExcelExtract();
        ExcelExtract excel2 = new ExcelExtract();
        AccessExtractor access1 = new AccessExtractor();
        AccessExtractor access2 = new AccessExtractor();
        CSVExtractor csv1 = new CSVExtractor();
        CSVExtractor csv2 = new CSVExtractor();
        
        //Bestanden extracten.
        access1.extractor(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C1a.accdb");
        access2.extractor(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C1b.accdb");
        excel1.read(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C2a.xls");
        excel2.read(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C2b.xls");
        csv1.csvExtract(System.getProperty("user.home")+"\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C3a.txt");
        csv2.csvExtract(System.getProperty("user.home")+"\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C3b.txt");
        
        //Ophalen van de extracte gegevens
        ArrayList<Klant> klantenexcel1 = excel1.getKlanten();
        ArrayList<Klant> klantenexcel2 = excel2.getKlanten();
        ArrayList<Klant> klantenaccess1 = access1.getKlanten();
        ArrayList<Klant> klantenaccess2 = access2.getKlanten();
        ArrayList<Klant> klantencsv1 = csv1.getKlanten();
        ArrayList<Klant> klantencsv2 = csv2.getKlanten();
        
        //Extracte gegevens samenvoegen tot 1 ArrayList.
        ArrayList<Klant> klanten = new ArrayList<Klant>();
        klanten.addAll(klantenexcel1);
        klanten.addAll(klantenexcel2);
        klanten.addAll(klantenaccess1);
        klanten.addAll(klantenaccess2);
        klanten.addAll(klantencsv1);
        klanten.addAll(klantencsv2);
        
        //Extracte gegevens transformen
        Transform tf = new Transform(klanten);
        tf.Transformer();
        klanten = tf.getKlanten();
        
        //Arraylist met de gegevens om zetten naar een grote query om toe te voegen in de Database.
        int i = 0;
        String query = "";
        
        for(Klant k : klanten){            
            Date datum = new Date();
            SimpleDateFormat tijd = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat dag = new SimpleDateFormat("y-M-d");
            String td = tijd.format(datum);
            String dg = dag.format(datum);
            
            if(i<1){
                query = "INSERT INTO klant VALUES ('"+k.getKlantNummer()+"', '"+k.getKlantNaam()+"', '"+k.getTelefoon()+"', '"+k.getMobiel()+"', '"+k.getPlaats()+"', '"+k.getLand()+"', '"+k.getPercentage()+"', '"+dg+"', '"+td+"')";
            i++;}
            else{
                Thread.sleep(50);
                datum = new Date();
                td = tijd.format(datum);
                dg = dag.format(datum);
                query += ",('"+k.getKlantNummer()+"', '"+k.getKlantNaam()+"', '"+k.getTelefoon()+"', '"+k.getMobiel()+"', '"+k.getPlaats()+"', '"+k.getLand()+"', '"+k.getPercentage()+"', '"+dg+"', '"+td+"') ";
            }
        }query = query + ";";        
        db1.sqlUpdate(query);

        //Sluiten van de verbinding
        boolean y = false;
        y = db1.Sluitconnectie();
        if (y) {
            System.out.println("Database verbinding gesloten!");
        } else {
            System.out.println("Database verbinding niet gesloten!");
        }
    }
}
