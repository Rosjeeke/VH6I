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

    public Transform() {
        klanten = new ArrayList<Klant>();
        klanten.add(new Klant(1, "Barry", "0162683112", "0627232007", "Made", "PO", new BigDecimal("0.15")));
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
            
        }
    }
}
