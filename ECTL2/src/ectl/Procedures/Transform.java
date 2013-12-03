/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ectl.Procedures;

import ectl.Klant;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rogier
 */
public class Transform {

    ArrayList<Klant> klanten;

    public Transform() {
        klanten = new ArrayList<Klant>();
        klanten.add(new Klant(1, "Jeroen v.d. Doorn", "0162683112", "0627232007", "Made", "PO", new BigDecimal("0.15")));
    }

    public void Transform() {

        for (Klant k : klanten) {
            BigDecimal percentage = k.getPercentage();
            if (percentage.compareTo(BigDecimal.ONE) == -1) {
                k.setPercentage(percentage.multiply(new BigDecimal(100)).setScale(0, RoundingMode.FLOOR));
            }

            String land = k.getLand();
            switch (land) {
                case "BE":
                    k.setLand("België");
                    break;
                case "DE":
                    k.setLand("Deutschland");
                    break;
                case "FR":
                    k.setLand("France");
                    break;
                case "NL":
                    k.setLand("Nederland");
                    break;
                case "PO":
                    k.setLand("Polska");
                    break;
                case "UK":
                    k.setLand("United Kingdom");
                    break;
            }

            String naam = k.getKlantNaam();
            if (naam.contains(".")){ 
                String naam1 = naam.substring(0, naam.indexOf('.'));
                String naam2 = naam.substring(naam.indexOf('.') + 1);
                    if (naam1.length() == 1 && !k.getKlantNaam().contains(" en ")){            
                        System.out.println(naam1);
                        System.out.println(naam2);
                        k.setKlantNaam(naam1 + "." + naam2);
                        System.out.println("eerste " + k.getKlantNaam());
                    }
            }
            if (k.getKlantNaam().contains(" en ")) {
                String[] delen = k.getKlantNaam().split(" en ");
                String naam3 = delen[0];
                String naam4 = delen[1];
                k.setKlantNaam(naam3 + " - " + naam4);
                System.out.println("tweede " + k.getKlantNaam());
            }
//            if (){
//                
//            }
            
            char ch = k.getKlantNaam().charAt(2);
            if (Character.isLetter(ch)) {
                String naam1 = naam.substring(0, naam.indexOf(' '));
                String naam2 = naam.substring(naam.indexOf(' ') + 1);
                String naam3 = naam1.substring(0, 1);
                k.setKlantNaam(naam3+ ". " + naam2);
                System.out.println("derde " + k.getKlantNaam());
            }
            else{
                System.out.println("Faal");
            }




//          Pattern pattern = Pattern.compile("\\[A-Z]{1}.{1}\\p{IsWhite_Space}{1}[A-Za-z]{1,50}");
//          Matcher matcher = pattern.matcher(naam);
//          
//          if(matcher.matches()){
//              System.out.println("Goed");
//          }
//          else{System.out.println("fout");}
//        }
        }
    }
}
