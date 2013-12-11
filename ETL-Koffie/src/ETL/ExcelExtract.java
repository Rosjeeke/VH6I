/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ETL;

import java.io.File;
import java.io.IOException;
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

    private ArrayList<Koffie> koffie = new ArrayList<Koffie>();
//    private ArrayList<Koffie> superMarkten = new ArrayList<>();
    private ArrayList<Koffie> albert, superboer, c1000, superunie, coop, hoogvliet, jumbo, vomar, jan, deen, bonimarkt, plus;
    private String brand, apparaat, type, beleving, soort, formaat, smaak, upc,
            vorige1, vorige2, vorige3, vorige4, vorige5;
    private int aantalrijen;
//    private ExcelExtract extract = new ExcelExtract();

    public ExcelExtract() {
        
    }

    public void infoScanRead(String bestand, int begin) {
        try {
            Workbook workbook = Workbook.getWorkbook(new File(bestand));
            Sheet sheet = workbook.getSheet(0);

            int rij = begin;
            int eind = sheet.findCell("Albert Heijn").getRow() - 1;
            while (rij < eind) {
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

//                        System.out.println("UPC: " + upc);
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

    public void extractSwitch(String bestand, int begin) {
        try {
            Workbook workbook1 = Workbook.getWorkbook(new File(bestand));
            Sheet sheet = workbook1.getSheet(0);
            aantalrijen = sheet.getRows();

            while (begin < aantalrijen) {
                Cell code = sheet.getCell(1, begin);
                switch ((code.getContents()).toString()) {
                    case "Albert Heijn":
                        albert = new ArrayList<>();
                        begin = readSales(bestand, "Albert Heijn", " Super de Boer", sheet, albert);
                        break;
                    case " Super de Boer":
                        superboer = new ArrayList<>();
                        begin = readSales(bestand, " Super de Boer", "C1000", sheet, superboer);
                        break;
                    case "C1000":
                        c1000 = new ArrayList<>();
                        begin = readSales(bestand, "C1000", "Superunie", sheet, c1000);
                        break;
                    case "Superunie":
                        superunie = new ArrayList<>();
                        begin = readSales(bestand, "Superunie", " COOP Totaal", sheet, superunie);
                        break;
                    case " COOP Totaal":
                        coop = new ArrayList<>();
                        begin = readSales(bestand, " COOP Totaal", " Hoogvliet", sheet, coop);
                        break;
                    case " Hoogvliet":
                        hoogvliet = new ArrayList<>();
                        begin = readSales(bestand, " Hoogvliet", " Jumbo", sheet, hoogvliet);
                        break;
                    case " Jumbo":
                        jumbo = new ArrayList<>();
                        begin = readSales(bestand, " Jumbo", " Vomar", sheet, jumbo);
                        break;
                    case " Vomar":
                        vomar = new ArrayList<>();
                        begin = readSales(bestand, " Vomar", " Jan Linders", sheet, vomar);
                        break;
                    case " Jan Linders":
                        jan = new ArrayList<>();
                        begin = readSales(bestand, " Jan Linders", " Deen", sheet, jan);
                        break;
                    case " Deen":
                        deen = new ArrayList<>();
                        begin = readSales(bestand, " Deen", " Bonimarkt", sheet, deen);
                        break;
                    case " Bonimarkt":
                        bonimarkt = new ArrayList<>();
                        begin = readSales(bestand, " Bonimarkt", " Plus", sheet, bonimarkt);
                        break;
                    case " Plus":
                        int beginPL = sheet.findCell(" Plus").getRow();
                        int eindPL = sheet.getRows();
                        plus = new ArrayList<Koffie>();
                        for(Koffie k : koffie){
                            plus.add(k.getNewCopy(k.getMajorBrand(), k.getApparaat(), k.getType(), k.getBeleving(), k.getSoort(),
                                    k.getFormaat(), k.getSmaak(), k.getUPC(), "","","","","","","","", k.getSuperMarkt()));
                        }
                        superMarktRead(bestand, beginPL, eindPL, "Plus", sheet, plus);
                        begin = eindPL;
                        break;
                }
                begin++;
            }
        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcelExtract.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public int readSales(String bestand, String beginSupermarkt, String eindSupermarkt,Sheet sheet, ArrayList<Koffie> supermarktLijst){
        int begin = sheet.findCell(beginSupermarkt).getRow();
        int eind = sheet.findCell(eindSupermarkt).getRow() - 1;
        for(Koffie k : koffie){
            supermarktLijst.add(k.getNewCopy(k.getMajorBrand(), k.getApparaat(), k.getType(), k.getBeleving(), k.getSoort(),
                    k.getFormaat(), k.getSmaak(), k.getUPC(), "","","","","","","","", k.getSuperMarkt()));
        }
        
        superMarktRead(bestand, begin, eind, beginSupermarkt.trim(), sheet, supermarktLijst);
        return eind;
    }
    

    public void superMarktRead(String bestand, int beginSupermarkt, int eindSupermarkt, String superMarkt, Sheet sheet, ArrayList<Koffie> lijst) {
        while (beginSupermarkt < eindSupermarkt) {
            String regel = sheet.getCell(1, beginSupermarkt).getContents();
            if (regel.contains("(")) {
                Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                Matcher matcher = pattern.matcher(regel);

                if (matcher.matches()) {
                    upc = matcher.group(1);
                }

                for (Koffie a : lijst) {
                    if (upc.equals(a.getUPC())) {
                        String kwartaalEenZeven = sheet.getCell(2, beginSupermarkt).getContents();

                        if (kwartaalEenZeven.isEmpty()) {
                            boolean gaVerder = true;
                            for (int i = 1; i < 100 && gaVerder; i++) {

                                kwartaalEenZeven = sheet.getCell(2, beginSupermarkt - i).getContents();
                                if (kwartaalEenZeven.isEmpty() == false) {
                                    kwartaalEenZeven = sheet.getCell(1, beginSupermarkt - i).getContents();
                                    String kwartaalTweeZeven = sheet.getCell(2, beginSupermarkt - i).getContents();
                                    String kwartaalDrieZeven = sheet.getCell(3, beginSupermarkt - i).getContents();
                                    String kwartaalVierZeven = sheet.getCell(4, beginSupermarkt - i).getContents();
                                    String kwartaalEenAcht = sheet.getCell(5, beginSupermarkt - i).getContents();
                                    String kwartaalTweeAcht = sheet.getCell(6, beginSupermarkt - i).getContents();
                                    String kwartaalDrieAcht = sheet.getCell(7, beginSupermarkt - i).getContents();
                                    String kwartaalVierAcht = sheet.getCell(8, beginSupermarkt - i).getContents();
                                    a.setEenKwartaalZeven(kwartaalEenZeven);
                                    a.setTweeKwartaalZeven(kwartaalTweeZeven);
                                    a.setDrieKwartaalZeven(kwartaalDrieZeven);
                                    a.setVierKwartaalZeven(kwartaalVierZeven);
                                    a.setEenKwartaalAcht(kwartaalEenAcht);
                                    a.setTweeKwartaalAcht(kwartaalTweeAcht);
                                    a.setDrieKwartaalAcht(kwartaalDrieAcht);
                                    a.setVierKwartaalAcht(kwartaalVierAcht);
                                    a.setSuperMarkt(superMarkt);
//                                    System.out.println(superMarkt + " " + a.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                    gaVerder = false;
                                }
                            }
                        } else {
                            kwartaalEenZeven = sheet.getCell(1, beginSupermarkt).getContents();
                            String kwartaalTweeZeven = sheet.getCell(2, beginSupermarkt).getContents();
                            String kwartaalDrieZeven = sheet.getCell(3, beginSupermarkt).getContents();
                            String kwartaalVierZeven = sheet.getCell(4, beginSupermarkt).getContents();
                            String kwartaalEenAcht = sheet.getCell(5, beginSupermarkt).getContents();
                            String kwartaalTweeAcht = sheet.getCell(6, beginSupermarkt).getContents();
                            String kwartaalDrieAcht = sheet.getCell(7, beginSupermarkt).getContents();
                            String kwartaalVierAcht = sheet.getCell(8, beginSupermarkt).getContents();
                            a.setEenKwartaalZeven(kwartaalEenZeven);
                            a.setTweeKwartaalZeven(kwartaalTweeZeven);
                            a.setDrieKwartaalZeven(kwartaalDrieZeven);
                            a.setVierKwartaalZeven(kwartaalVierZeven);
                            a.setEenKwartaalAcht(kwartaalEenAcht);
                            a.setTweeKwartaalAcht(kwartaalTweeAcht);
                            a.setDrieKwartaalAcht(kwartaalDrieAcht);
                            a.setVierKwartaalAcht(kwartaalVierAcht);
                            a.setSuperMarkt(superMarkt);
//                            System.out.println(superMarkt + " " + a.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                        }
                    }
                }
            }
            beginSupermarkt++;
        }
        System.out.println(superMarkt);
    }
}
