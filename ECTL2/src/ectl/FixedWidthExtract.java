/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ectl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Ivo
 */
public class FixedWidthExtract {

    private ArrayList<Klant> klantenfwe;

    public FixedWidthExtract() {
        klantenfwe = new ArrayList<Klant>();
    }

    public void extract(String path) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));//Het pad naar het bestand moet je aanpassen naar de locatie op je eigen PC.
            String regel;
            while ((regel = in.readLine()) != null) {
                int klantNummer = Integer.parseInt(regel.substring(0, 8).replaceAll("\\s", ""));
                String klantNaam = regel.substring(8, 40).replaceAll("\\s+", "");
                String klantTelefoon = regel.substring(40, 56).replaceAll("\\s", "");
                String klantMobiel = regel.substring(56, 72).replaceAll("\\s", "");
                String klantPlaats = regel.substring(72, 88).replaceAll("\\s", "");
                String klantLand = regel.substring(88, 94).replaceAll("\\s", "");
                BigDecimal klantPercentage = new BigDecimal((regel.substring(94, 96).replaceAll("\\s", "")));

                Klant klant = new Klant(klantNummer, klantNaam, klantTelefoon, klantMobiel, klantPlaats, klantLand, klantPercentage);

                klantenfwe.add(klant);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Kan het bestand niet vinden.");
        } catch (IOException e) {
            System.out.println("Fout bij het lezen of sluiten bestand.");
            e.printStackTrace();
        }
    }
    
    public ArrayList<Klant> getKlanten(){
        return klantenfwe;
    }
}
