package ectl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVExtractor {
	
    private ArrayList<Klant> klanten;
    
    public CSVExtractor(){
        klanten = new ArrayList<Klant>();
    }
  
    public void extract(String path){

            try{
                 BufferedReader in = new BufferedReader(new FileReader(path));
                 String line;
                 while ((line = in.readLine()) != null){
                    String[] splitedString = line.split(",");
                    
                        int klantNummer = Integer.parseInt(splitedString[0]);
                        String klantNaam = splitedString[1];
                        String klantTelefoon = splitedString[2];
                        String klantMobiel = splitedString[3];
                        String klantPlaats = splitedString[4];
                        String klantLand = splitedString[5];
                        int klantPercentage = Integer.parseInt(splitedString[6]);
                        
                    Klant klant = new Klant(klantNummer, klantNaam, klantTelefoon, klantMobiel, klantPlaats, klantLand, klantPercentage);

                    klanten.add(klant);
                 }
                 in.close();
            }
        catch(FileNotFoundException e1){
            System.out.println("Het bestand is niet gevonden.");
        }
        catch(IOException e){
            System.out.println("Er is een fout opgetreden.");
            e.printStackTrace();
        }
    }
    
    public ArrayList<Klant> getKlanten(){
        return klanten;
    }
}