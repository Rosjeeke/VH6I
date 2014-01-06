package ectl;

import com.healthmarketscience.jackcess.*;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jeroen Doorn
 */

public class AccessExtractor {
    
    private static String file = System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\A1.mdb";
    public ArrayList<Klant> klanten;
    
    public AccessExtractor()
    {
        klanten = new ArrayList<Klant>();
    }
    
    public void extractor(String path)
    {
        try
        {
            Database db = DatabaseBuilder.open(new File(path));
            Table table = db.getTable("Klant");
            for(Row row : table) 
            {
                String klantNr = row.get("KlantId").toString();
                int klantNummer = Integer.parseInt(klantNr);
                String klantNaam = row.get("KlantNaam").toString();
                String telefoon = row.get("TelNo").toString();
                String mobiel = row.get("MobNo").toString();
                String plaats = row.get("Plaats").toString();
                String land = row.get("Land").toString();
                String percent = row.get("Korting").toString();
                int percentage = Integer.parseInt(percent);
                
                Klant nieuw = new Klant(klantNummer, klantNaam, telefoon, mobiel, plaats, land, percentage);
                klanten.add(nieuw);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void print()
    {
        for(Klant klant : klanten) 
        {
            System.out.println(klant);   
        }
    }
    
    public ArrayList<Klant> getKlanten(){
        return klanten;
    }
}