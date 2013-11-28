
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
        //Aanmaken nieuwe Database met de naam van de databank 
        MysqlDatabase db1 = new MysqlDatabase("myfirstdwh");
        //Aanmaken van de verschillende extract klassen.
        FixedWidthExtract fwe = new FixedWidthExtract();
        ExtractA2 exce = new ExtractA2();
        //een klant toevoegen
        fwe.extract("C:\\Users\\Ivo\\Documents\\School\\vh6\\ECTL\\A4.txt");
        exce.read("C:\\Users\\Ivo\\Documents\\School\\vh6\\ECTL\\A2.xls");
        
//        Klant k1 = new Klant(1, "J. van drunen", "0162684569", "062584759631", "Made", "NL", 6);
//        Klant k2 = new Klant(2, "J. van drunen", "0162684569", "062584759631", "Made", "NL", 15);
//        fweklanten.add(k1);
//        fweklanten.add(k2);
        
        int i = 0;
        String query = "";
        ArrayList<Klant> klanten = new ArrayList<Klant>();
        ArrayList<Klant> klantenfwe = fwe.getKlanten();
        ArrayList<Klant> klantenexce = exce.getKlanten();
        klanten.addAll(klantenfwe);
        klanten.addAll(klantenexce);
        for(Klant k : klanten){
            
            if(i<1){
            query = "INSERT INTO klant VALUES('"+k.getKlantNummer()+"', '"+k.getKlantNaam()+"', '"+k.getTelefoon()+"',"
                        + " '"+k.getMobiel()+"', '"+k.getPlaats()+"', '"+k.getLand()+"', '"+k.getPercentage()+"' )";
            i++;}
            else{
                query += ",('"+k.getKlantNummer()+"', '"+k.getKlantNaam()+"', '"+k.getTelefoon()+"'," + " '"+k.getMobiel()+"', '"+k.getPlaats()+"', '"+k.getLand()+"', '"+k.getPercentage()+"' )";
            }
        }
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
