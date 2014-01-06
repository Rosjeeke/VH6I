
package ectl;

import ectl.Procedures.Transform;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        
        //date
        //Date datum = new Date();
        //SimpleDateFormat tijd = new SimpleDateFormat("HH:mm:ss");
        //SimpleDateFormat dag = new SimpleDateFormat("y-M-d");
        
        
        
        
        //Aanmaken van de verschillende extract klassen.
        ExcelExtract excel1 = new ExcelExtract();
        ExcelExtract excel2 = new ExcelExtract();
        AccessExtractor access1 = new AccessExtractor();
        AccessExtractor access2 = new AccessExtractor();
        CSVExtractor csv1 = new CSVExtractor();
        CSVExtractor csv2 = new CSVExtractor();
        
        //een klant toevoegen
        access1.extractor(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C1a.accdb");
        access2.extractor(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C1b.accdb");
        excel1.read(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C2a.xls");
        excel2.read(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C2b.xls");
        csv1.csvExtract(System.getProperty("user.home")+"\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C3a.txt");
        csv2.csvExtract(System.getProperty("user.home")+"\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\C3b.txt");
        
        
        //fwe.extract(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\A4.txt");
        //exce.read(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\A2.xls");
        //access.extractor(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\A1.mdb");
        //csv.csvExtract(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant2\\A3.txt");
        
//        Klant k1 = new Klant(1, "J. van drunen", "0162684569", "062584759631", "Made", "NL", 6);
//        Klant k2 = new Klant(2, "J. van drunen", "0162684569", "062584759631", "Made", "NL", 15);
//        fweklanten.add(k1);
//        fweklanten.add(k2);
        
        int i = 0;
        String query = "";
        
        ArrayList<Klant> klanten = new ArrayList<Klant>();
        ArrayList<Klant> klantenexcel1 = excel1.getKlanten();
        ArrayList<Klant> klantenexcel2 = excel2.getKlanten();
        ArrayList<Klant> klantenaccess1 = access1.getKlanten();
        ArrayList<Klant> klantenaccess2 = access2.getKlanten();
        ArrayList<Klant> klantencsv1 = csv1.getKlanten();
        ArrayList<Klant> klantencsv2 = csv2.getKlanten();
        
        klanten.addAll(klantenexcel1);
        klanten.addAll(klantenexcel2);
        klanten.addAll(klantenaccess1);
        klanten.addAll(klantenaccess2);
        klanten.addAll(klantencsv1);
        klanten.addAll(klantencsv2);
        
        Transform tf = new Transform(klanten);
        tf.Transformer();
        klanten = tf.getKlanten();
        int teller = 0;
        
        for(Klant k : klanten){
            
            
            //if(k.getKlantNummer()==181){
            //    String out = k.getKlantNummer()+" "+k.getKlantNaam()+" "+k.getTelefoon()+" "+k.getMobiel()+" "+k.getPlaats()+" "+k.getLand()+" "+k.getPercentage().toString();
            //    System.out.println(out);    
            //}
            Date datum = new Date();
            SimpleDateFormat tijd = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat dag = new SimpleDateFormat("y-M-d");
            String td = tijd.format(datum);
            String dg = dag.format(datum);
            
            if(i<1){
                query = "INSERT INTO klant VALUES ('"+k.getKlantNummer()+"', '"+k.getKlantNaam()+"', '"+k.getTelefoon()+"', '"+k.getMobiel()+"', '"+k.getPlaats()+"', '"+k.getLand()+"', '"+k.getPercentage()+"', '"+dg+"', '"+td+"')";
            //System.out.print(test);
            //db1.sqlUpdate(test);
            i++;}
            else{
                Thread.sleep(50);
                datum = new Date();
                tijd = new SimpleDateFormat("HH:mm:ss");
                dag = new SimpleDateFormat("y-M-d");
                td = tijd.format(datum);
                dg = dag.format(datum);
                System.out.println(teller);
                teller++;
                //System.out.println(td2);
                query += ",('"+k.getKlantNummer()+"', '"+k.getKlantNaam()+"', '"+k.getTelefoon()+"', '"+k.getMobiel()+"', '"+k.getPlaats()+"', '"+k.getLand()+"', '"+k.getPercentage()+"', '"+dg+"', '"+td+"') ";
            }
        }query = query + ";";
        //System.out.println(query);
        db1.sqlUpdate(query);
//        db1.sqlUpdate("INSERT INTO klant VALUES('1' , 'J. van Drunen' , '0162686655', '0628956325', 'Made', 'NL', '5' )");
//        //De gegevens in de database uitprinten

//        db1.sqlExecute("SELECT * FROM klant");
//        // De gegevens in de database verwijderen
//        db1.sqlUpdate("DELETE FROM klant WHERE KlantNummer = '1'");

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
