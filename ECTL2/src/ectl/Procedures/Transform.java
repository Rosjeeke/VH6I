/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ectl.Procedures;

import ectl.Klant;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author Rogier
 */
public class Transform {

    ArrayList<Klant> klanten;

    public Transform(ArrayList<Klant> klanten) {
        this.klanten = klanten;
        //klanten.add(new Klant(1, "R.Welten", "0162683112", "0627232007", "Made", "PO", new BigDecimal("0.15")));
    }

    public void Transformer() {

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
            if (naam.contains(".")) {
                if (naam.contains(" v.d.")) {
                    String naamNieuw = k.getKlantNaam().replace(" v.d. ", " v/d ");
                    k.setKlantNaam(naamNieuw);
                } else {
                    String naam1 = naam.substring(0, naam.indexOf('.'));
                    String naam2 = naam.substring(naam.indexOf('.') + 1);
                    if (naam1.length() == 1 && !k.getKlantNaam().contains(" en ")) {
                        k.setKlantNaam(naam1 + "." + naam2);
                        System.out.println(k.getKlantNaam());
                    }
                }
            }
            
            if (k.getKlantNaam().contains(",")){
                String naam1 = naam.substring(0, naam.indexOf(','));
                String naam2 = naam.substring(naam.indexOf(',') + 1);
                k.setKlantNaam(naam2.replace(" ", "") + " " + naam1);
                System.out.println(k.getKlantNaam());
            }
            
            if (k.getKlantNaam().contains(" en ")) {
                String naamNieuw = k.getKlantNaam().replace(" en ", " - ");
                k.setKlantNaam(naamNieuw);
                System.out.println(k.getKlantNaam());
            }

            String[] tussenvoegsels = new String[]{" van de ", " van der ", " Van De ", " Van Der ", " van den ", " Van Den "};
            for (String woord : tussenvoegsels) {
                if (k.getKlantNaam().indexOf(woord) > 0) {
                    String naamNieuw = k.getKlantNaam().replace(woord, " v/d ");
                    k.setKlantNaam(naamNieuw);
                }
            }

            char ch = k.getKlantNaam().charAt(1);
            if (Character.isLetter(ch)) {
                String naam1 = k.getKlantNaam().substring(0, k.getKlantNaam().indexOf(' '));
                String naam2 = k.getKlantNaam().substring(k.getKlantNaam().indexOf(' ') + 1);
                String naam3 = naam1.substring(0, 1);
                k.setKlantNaam(naam3 + ". " + naam2);
                System.out.println(k.getKlantNaam());
            } 
        }
    }
    
    public ArrayList<Klant> getKlanten(){
        return klanten;
    }
}
