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
    
    public void Extractor()
    {
        try
        {
            Database db = DatabaseBuilder.open(new File(file));
            Table table = db.getTable("Klant");
            for(Row row : table) 
            {
                int klantNummer = (int)row.get("KlantId");
                String klantNaam = row.get("KlantNaam").toString();
                String telefoon = row.get("TelNo").toString();
                String mobiel = row.get("MobNo").toString();
                String plaats = row.get("Plaats").toString();
                String land = row.get("Land").toString();
                int percentage = (int)row.get("Korting");
                
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
}