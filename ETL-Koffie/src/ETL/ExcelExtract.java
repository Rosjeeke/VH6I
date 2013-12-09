/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ETL;

import Rommel.Klant;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Liannie
 */
public class ExcelExtract {

    private ArrayList<Koffie> koffie;
    private String brand, apparaat, type, beleving, soort, formaat, smaak, upc,
            vorige1, vorige2, vorige3, vorige4, vorige5;

    public ExcelExtract() {
        koffie = new ArrayList<Koffie>();
    }

    public void infoScanRead(String bestand) {
        try {
            Workbook workbook = Workbook.getWorkbook(new File(bestand));
            Sheet sheet = workbook.getSheet(0);
            int aantalrijen = sheet.getRows();

            int rij = 3;
            while (rij < aantalrijen) {
                Cell code = sheet.getCell(0, rij);
                Cell string = sheet.getCell(1, rij);

                switch ((code.getContents()).toString()) {
                    case "MAJOR.BRAND":
                        brand = string.getContents().trim();
//                        System.out.println("Major brand: " + brand);
                        break;
                    case "APPARAAT":
                        apparaat = string.getContents().replace(brand, "").trim();
//                        System.out.println("Apparaat: " + apparaat);
                        break;
                    case "TYPE":
                        vorige1 = brand + " " + apparaat;
                        type = string.getContents().replace(vorige1, "").trim();
//                        System.out.println("Type: " + type);
                        break;
                    case "BELEVING":
                        vorige2 = vorige1 + " " + type;
                        beleving = string.getContents().replace(vorige2, "").trim();
//                        System.out.println("Beleving: " + beleving);
                        break;
                    case "SOORT":
                        vorige3 = vorige2 + " " + beleving;
                        soort = string.getContents().replace(vorige3, "").trim();
//                        System.out.println("Soort: " + soort);
                        break;
                    case "FORMAAT":
                        vorige4 = vorige3 + " " + soort;
                        formaat = string.getContents().replace(vorige4, "").trim();
//                        System.out.println("Formaat: " + formaat);
                        break;
                    case "SMAAK":
                        vorige5 = brand + " " + apparaat + " " + type + " " + soort + " " + formaat;
                        smaak = string.getContents().replace(vorige5, "").trim();
//                        System.out.println("Smaak: " + smaak);
                        break;
                    case "UPC":
                        Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                        Matcher matcher = pattern.matcher(string.getContents());

                        if (matcher.matches()) {
                            upc = matcher.group(1);
                        }

                        System.out.println("UPC: " + upc);
                        Koffie kf = new Koffie(brand, apparaat, type, beleving, soort, formaat, smaak, upc, "", "", "", "", "", "", "", "", "");

                        koffie.add(kf);

                        break;
                }


                rij++;
            }



        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcelExtract.class.getName()).log(Level.SEVERE, null, ex);
        }







    }

    public void superMarktRead(String bestand, int begin, int eind, String superMarkt) {
        try {
            ArrayList<Koffie> superMarkten = new ArrayList<Koffie>();
            for (Koffie kof : koffie) {
                superMarkten.add(kof);
            }
            Workbook workbook1 = Workbook.getWorkbook(new File(bestand));
            Sheet sheet = workbook1.getSheet(0);
            int aantalrijen = sheet.getRows();

            int beginRij = begin;
            while (beginRij < aantalrijen) {
                String regel = sheet.getCell(1, beginRij).getContents();
                if (regel.contains("(")) {
                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                    Matcher matcher = pattern.matcher(regel);

                    if (matcher.matches()) {
                        upc = matcher.group(1);
                    }

                    for (Koffie k : superMarkten) {
                        if (upc.equals(k.getUPC())) {
                            String kwartaalEenZeven = sheet.getCell(2, beginRij).getContents();

                            if (kwartaalEenZeven.isEmpty()) {
                                outerloop:
                                for (int i = 1; i < 100; i++) {

                                    kwartaalEenZeven = sheet.getCell(2, beginRij-i).getContents();
                                    if (kwartaalEenZeven.isEmpty() == false) {
                                        
                                        String kwartaalTweeZeven = sheet.getCell(2, beginRij - i).getContents();
                                        String kwartaalDrieZeven = sheet.getCell(3, beginRij - i).getContents();
                                        String kwartaalVierZeven = sheet.getCell(4, beginRij - i).getContents();
                                        String kwartaalEenAcht = sheet.getCell(5, beginRij - i).getContents();
                                        String kwartaalTweeAcht = sheet.getCell(6, beginRij - i).getContents();
                                        String kwartaalDrieAcht = sheet.getCell(7, beginRij - i).getContents();
                                        String kwartaalVierAcht = sheet.getCell(8, beginRij - i).getContents();
                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                        k.setSuperMarkt(superMarkt);
                                        System.out.println(k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                        break outerloop;
                                    }
                                }
                            }
                            else{
                              String kwartaalTweeZeven = sheet.getCell(2, beginRij).getContents();
                                        String kwartaalDrieZeven = sheet.getCell(3, beginRij).getContents();
                                        String kwartaalVierZeven = sheet.getCell(4, beginRij).getContents();
                                        String kwartaalEenAcht = sheet.getCell(5, beginRij).getContents();
                                        String kwartaalTweeAcht = sheet.getCell(6, beginRij).getContents();
                                        String kwartaalDrieAcht = sheet.getCell(7, beginRij).getContents();
                                        String kwartaalVierAcht = sheet.getCell(8, beginRij).getContents();
                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                        k.setSuperMarkt(superMarkt);
                                        System.out.println(k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                        
                            }
                        }
                        
                    }
                }beginRij++;
            }
//                        if (regel.contains(k.getUPC())) {

//                        }



//                switch ((code.getContents()).toString()) {
//                    case "MAJOR.BRAND":
//                        brand = string.getContents().trim();
////                        System.out.println("Major brand: " + brand);
//                        break;
//                    case "APPARAAT":
//                        apparaat = string.getContents().replace(brand, "").trim();
////                        System.out.println("Apparaat: " + apparaat);
//                        break;
//                    case "TYPE":
//                        vorige1 = brand + " " + apparaat;
//                        type = string.getContents().replace(vorige1, "").trim();
////                        System.out.println("Type: " + type);
//                        break;
//                    case "BELEVING":
//                        vorige2 = vorige1 + " " + type;
//                        beleving = string.getContents().replace(vorige2, "").trim();
////                        System.out.println("Beleving: " + beleving);
//                        break;
//                    case "SOORT":
//                        vorige3 = vorige2 + " " + beleving;
//                        soort = string.getContents().replace(vorige3, "").trim();
////                        System.out.println("Soort: " + soort);
//                        break;
//                    case "FORMAAT":
//                        vorige4 = vorige3 + " " + soort;
//                        formaat = string.getContents().replace(vorige4, "").trim();
////                        System.out.println("Formaat: " + formaat);
//                        break;
//                    case "SMAAK":
//                        vorige5 = brand + " " + apparaat + " " + type + " " + soort + " " + formaat;
//                        smaak = string.getContents().replace(vorige5, "").trim();
////                        System.out.println("Smaak: " + smaak);
//                        break;
//                    case "UPC":
//                        Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
//                        Matcher matcher = pattern.matcher(string.getContents());
//
//                        if (matcher.matches()) {
//                            upc = matcher.group(1);
//                        }
//
////                        System.out.println("UPC: " + upc);
//                        Koffie kf = new Koffie(brand, apparaat, type, beleving, soort, formaat, smaak, upc);
//
//                        koffie.add(kf);
//
//                        break;
//                }



        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcelExtract.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
         public void print(){
         int teller = 0;
         int max = klanten.size();
         while (teller<max){
         System.out.println(klanten.get(teller));
         teller++;
         }
         }
         */
//    public ArrayList<Klant> getKlanten() {
//        return klanten;
//    }
    }
}
