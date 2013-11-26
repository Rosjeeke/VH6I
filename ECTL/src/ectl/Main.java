
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
        Database db1 = new Database("myfirstdwh");
        FixedWidthExtract fwe = new FixedWidthExtract();
        //een klant toevoegen
        ArrayList<Klant> fweklanten = new ArrayList<Klant>();
        Klant k1 = new Klant(1, "J. van drunen", "0162684569", "062584759631", "Made", "NL", 6);
        Klant k2 = new Klant(2, "J. van drunen", "0162684569", "062584759631", "Made", "NL", 15);
        fweklanten.add(k1);
        fweklanten.add(k2);
        int i = 0;
        String query = "";
        for(Klant k : fweklanten){
            
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
