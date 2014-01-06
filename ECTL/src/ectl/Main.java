
package ectl;

import java.util.ArrayList;

/**
 *
 * @author Rogier
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Aanmaken nieuwe Database met de naam van de databank .
        MysqlDatabase db1 = new MysqlDatabase("myfirstdwh");
        
        //Aanmaken van de verschillende extract klassen.
        FixedWidthExtractor fwe = new FixedWidthExtractor();
        ExcelExtractor exc = new ExcelExtractor();
        AccessExtractor acc = new AccessExtractor();
        CSVExtractor csv = new CSVExtractor();
        
        //Extracten van de bestanden.
        fwe.extract(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant\\A4.txt");
        exc.extract(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant\\A2.xls");
        acc.extract(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant\\A1.mdb");
        csv.extract(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant\\A3.txt");
        
        //Ophalen van de extracte gegevens.
        ArrayList<Klant> klantenfwe = fwe.getKlanten();
        ArrayList<Klant> klantenexc = exc.getKlanten();
        ArrayList<Klant> klantenacc = acc.getKlanten();
        ArrayList<Klant> klantencsv = csv.getKlanten();
         
        //Alle extracte gegevens in een algemene arraylist stoppen.
        ArrayList<Klant> klanten = new ArrayList<Klant>();
        klanten.addAll(klantenfwe);
        klanten.addAll(klantenexc);
        klanten.addAll(klantenacc);
        klanten.addAll(klantencsv);
        
        //Gegevens in een grote query stoppen voor in de database.
        int i = 0;
        String query = "";
        
        for(Klant k : klanten){
            
            if(i<1){
            query = "INSERT INTO klant VALUES(\""+k.getKlantNummer()+"\", \""+k.getKlantNaam()+"\", \""+k.getTelefoon()+"\","
                        + " \""+k.getMobiel()+"\", \""+k.getPlaats()+"\", \""+k.getLand()+"\", \""+k.getPercentage()+"\" )";
            i++;}
            else{
                query += ",(\""+k.getKlantNummer()+"\", \""+k.getKlantNaam()+"\", \""+k.getTelefoon()+"\"," + " \""+k.getMobiel()+"\", \""+k.getPlaats()+"\", \""+k.getLand()+"\", \""+k.getPercentage()+"\" )";
            }
        }
        query = query + ";";
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
