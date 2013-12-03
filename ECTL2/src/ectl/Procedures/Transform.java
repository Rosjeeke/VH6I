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
        klanten.add(new Klant(1, "Welten R.", "0162683112", "0627232007", "Made", "PO", new BigDecimal("0.15")));
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
            String naam1 = naam.substring(0, naam.indexOf('.'));
            String naam2 = naam.substring(naam.indexOf('.') + 1);
            System.out.println(naam1);
            System.out.println(naam2);
            if(naam1.length() == 1){
                k.setKlantNaam(naam1 + "." + naam2);
            }
            if(naam2.length()){}
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
