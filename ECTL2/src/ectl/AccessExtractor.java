package ectl;

import com.healthmarketscience.jackcess.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Jeroen Doorn
 */

public class AccessExtractor {
    
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
                String klantnr = row.get("KlantId").toString();
                int klantNummer = Integer.parseInt(klantnr);
                String klantNaam = row.get("KlantNaam").toString();
                String telefoon = row.get("Telefoon").toString();
                String mobiel = row.get("Mobiel").toString();
                String plaats = row.get("Plaats").toString();
                String land = row.get("Land").toString();
                String perc = row.get("Korting").toString();
                BigDecimal percentage = new BigDecimal(perc);
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