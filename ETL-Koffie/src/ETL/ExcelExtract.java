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

    private ArrayList<Koffie> koffie, superMarkten, albert, superboer, c1000, superunie, coop, hoogvliet, jumbo, vomar, jan, deen, bonimarkt, plus;
    private String brand, apparaat, type, beleving, soort, formaat, smaak, upc,
            vorige1, vorige2, vorige3, vorige4, vorige5;

    public ExcelExtract() {
        koffie = new ArrayList<>();
        superMarkten = new ArrayList<>();
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
            albert = new ArrayList<>();
            superboer = new ArrayList<>();
            c1000 = new ArrayList<>();
            superunie = new ArrayList<>();
            coop = new ArrayList<>();
            hoogvliet = new ArrayList<>();
            jumbo = new ArrayList<>();
            vomar = new ArrayList<>();
            jan = new ArrayList<>();
            deen = new ArrayList<>();
            bonimarkt = new ArrayList<>();
            plus = new ArrayList<>();
            for (Koffie kof : koffie) {
                albert.add(kof);
                superboer.add(kof);
                c1000.add(kof);
                superunie.add(kof);
                coop.add(kof);
                hoogvliet.add(kof);
                jumbo.add(kof);
                vomar.add(kof);
                jan.add(kof);
                deen.add(kof);
                bonimarkt.add(kof);
                plus.add(kof);
            }
            Workbook workbook1 = Workbook.getWorkbook(new File(bestand));
            Sheet sheet = workbook1.getSheet(0);
            int aantalrijen = sheet.getRows();


            while (begin < aantalrijen) {
                Cell code = sheet.getCell(1, begin);
                switch ((code.getContents()).toString()) {
                    case "Albert Heijn":
                        int beginExtractAH = sheet.findCell("Albert Heijn").getRow();
                        int eindAH = 6375;
                        String superMarktAH = "Albert Heijn";


                            while (beginExtractAH < eindAH) {
                                String regel = sheet.getCell(1, beginExtractAH).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie b : albert) {
                                        if (upc.equals(b.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractAH).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                //outerloop:
                                                boolean doContinue = true;
                                                for (int i = 1; i < 100 && doContinue; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractAH - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractAH - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractAH - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractAH - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractAH - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractAH - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractAH - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractAH - i).getContents();
                                                        b.setEenKwartaalZeven(kwartaalEenZeven);
                                                        b.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        b.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        b.setVierKwartaalZeven(kwartaalVierZeven);
                                                        b.setEenKwartaalAcht(kwartaalEenAcht);
                                                        b.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        b.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        b.setVierKwartaalAcht(kwartaalVierAcht);
                                                        b.setSuperMarkt(superMarktAH);
                                                        System.out.println(superMarktAH + " " + b.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        //break outerloop;
                                                         doContinue = false;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractAH).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractAH).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractAH).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractAH).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractAH).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractAH).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractAH).getContents();
                                                b.setEenKwartaalZeven(kwartaalEenZeven);
                                                b.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                b.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                b.setVierKwartaalZeven(kwartaalVierZeven);
                                                b.setEenKwartaalAcht(kwartaalEenAcht);
                                                b.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                b.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                b.setVierKwartaalAcht(kwartaalVierAcht);
                                                b.setSuperMarkt(superMarktAH);
                                                System.out.println(superMarktAH + " " + b.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractAH++;
                            }  

                            break;
                        case " Super de Boer":
                        int beginExtractSB = sheet.findCell(" Super de Boer").getRow();
                        int eindSB = sheet.findCell("C1000").getRow() - 1;
                        String superMarktSB = "Super de Boer";

                            while (beginExtractSB < eindSB) {
                                String regel = sheet.getCell(1, beginExtractSB).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : superboer) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractSB).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                //outerloop:
                                                boolean doContinue = true;
                                                for (int i = 1; i < 100 && doContinue; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractSB - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractSB - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractSB - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractSB - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractSB - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractSB - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractSB - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractSB - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktSB);
                                                        System.out.println(superMarktSB + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        //break outerloop;
                                                        doContinue = false;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractSB).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractSB).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractSB).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractSB).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractSB).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractSB).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractSB).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktSB);
                                                System.out.println(superMarktSB + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractSB++;
                            }  
                            break;
                        case "C1000":
                        int beginExtractC1 = sheet.findCell("C1000").getRow();
                        int eindC1 = sheet.findCell("Superunie").getRow() - 1;
                        String superMarktC1 = "C1000";

                            while (beginExtractC1 < eindC1) {
                                String regel = sheet.getCell(1, beginExtractC1).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : c1000) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractC1).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractC1 - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractC1 - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractC1 - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractC1 - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractC1 - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractC1 - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractC1 - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractC1 - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktC1);
                                                        System.out.println(superMarktC1 + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractC1).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractC1).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractC1).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractC1).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractC1).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractC1).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractC1).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktC1);
                                                System.out.println(superMarktC1 + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractC1++;
                            }
                            break;
                        case "Superunie":
                        int beginExtractSU = 12756;
                        int eindSU = 15944;
                        String superMarktSU = "Superunie";

                            while (beginExtractSU < eindSU) {
                                String regel = sheet.getCell(1, beginExtractSU).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : superunie) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractSU).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractSU - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractSU - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractSU - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractSU - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractSU - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractSU - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractSU - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractSU - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktSU);
                                                        System.out.println(superMarktSU + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractSU).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractSU).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractSU).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractSU).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractSU).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractSU).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractSU).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktSU);
                                                System.out.println(superMarktSU + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractSU++;
                            }
                            break;
                        case " COOP Totaal":
                        int beginExtractCO = 15945;
                        int eindCO = 19135;
                        String superMarktCO = "COOP Totaal";

                            while (beginExtractCO < eindCO) {
                                String regel = sheet.getCell(1, beginExtractCO).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : coop) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractCO).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractCO - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractCO - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractCO - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractCO - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractCO - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractCO - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractCO - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractCO - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktCO);
                                                        System.out.println(superMarktCO + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractCO).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractCO).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractCO).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractCO).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractCO).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractCO).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractCO).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktCO);
                                                System.out.println(superMarktCO + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractCO++;
                            }
                            break;
                        case " Hoogvliet":
                        int beginExtractHV = sheet.findCell(" Hoogvliet").getRow();
                        int eindHV = sheet.findCell(" Jumbo").getRow() - 1;
                        String superMarktHV = "Hoogvliet";

                            while (beginExtractHV < eindHV) {
                                String regel = sheet.getCell(1, beginExtractHV).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : hoogvliet) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractHV).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractHV - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractHV - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractHV - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractHV - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractHV - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractHV - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractHV - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractHV - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktHV);
                                                        System.out.println(superMarktHV + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractHV).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractHV).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractHV).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractHV).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractHV).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractHV).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractHV).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktHV);
                                                System.out.println(superMarktHV + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractHV++;
                            }
                            break;
                        case " Jumbo":
                        int beginExtractJU = sheet.findCell(" Jumbo").getRow();
                        int eindJU = sheet.findCell(" Vomar").getRow() - 2;
                        String superMarktJU = "Jumbo";

                            while (beginExtractJU < eindJU) {
                                String regel = sheet.getCell(1, beginExtractJU).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : jumbo) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractJU).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractJU - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractJU - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractJU - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractJU - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractJU - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractJU - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractJU - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractJU - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktJU);
                                                        System.out.println(superMarktJU + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractJU).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractJU).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractJU).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractJU).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractJU).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractJU).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractJU).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktJU);
                                                System.out.println(superMarktJU + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractJU++;
                            }
                            break;
                        case " Vomar":
                        int beginExtractVO = sheet.findCell(" Vomar").getRow();
                        int eindVO = sheet.findCell(" Jan Linders").getRow() - 2;
                        String superMarktVO = "Vomar";

                            while (beginExtractVO < eindVO) {
                                String regel = sheet.getCell(1, beginExtractVO).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : vomar) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractVO).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractVO - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractVO - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractVO - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractVO - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractVO - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractVO - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractVO - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractVO - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktVO);
                                                        System.out.println(superMarktVO + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractVO).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractVO).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractVO).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractVO).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractVO).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractVO).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractVO).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktVO);
                                                System.out.println(superMarktVO + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractVO++;
                            }
                            break;
                        case " Jan Linders":
                        int beginExtractJL = sheet.findCell(" Jan Linders").getRow();
                        int eindJL = sheet.findCell(" Deen").getRow() - 2;
                        String superMarktJL = "Jan Linders";

                            while (beginExtractJL < eindJL) {
                                String regel = sheet.getCell(1, beginExtractJL).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : jan) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractJL).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractJL - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractJL - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractJL - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractJL - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractJL - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractJL - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractJL - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractJL - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktJL);
                                                        System.out.println(superMarktJL + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractJL).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractJL).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractJL).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractJL).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractJL).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractJL).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractJL).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktJL);
                                                System.out.println(superMarktJL + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractJL++;
                            }
                            break;
                        case " Deen":
                        int beginExtractDE = sheet.findCell(" Deen").getRow();
                        int eindDE = sheet.findCell(" Bonimarkt").getRow() - 2;
                        String superMarktDE = "Deen";

                            while (beginExtractDE < eindDE) {
                                String regel = sheet.getCell(1, beginExtractDE).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : deen) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractDE).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractDE - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractDE - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractDE - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractDE - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractDE - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractDE - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractDE - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractDE - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktDE);
                                                        System.out.println(superMarktDE + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractDE).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractDE).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractDE).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractDE).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractDE).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractDE).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractDE).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktDE);
                                                System.out.println(superMarktDE + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractDE++;
                            }
                            break;
                        case " Bonimarkt":
                        int beginExtractBM = sheet.findCell(" Bonimarkt").getRow();
                        int eindBM = sheet.findCell(" Plus").getRow() - 2;
                        String superMarktBM = "Bonimarkt";

                            while (beginExtractBM < eindBM) {
                                String regel = sheet.getCell(1, beginExtractBM).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie k : bonimarkt) {
                                        if (upc.equals(k.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractBM).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractBM - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractBM - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractBM - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractBM - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractBM - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractBM - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractBM - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractBM - i).getContents();
                                                        k.setEenKwartaalZeven(kwartaalEenZeven);
                                                        k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        k.setVierKwartaalZeven(kwartaalVierZeven);
                                                        k.setEenKwartaalAcht(kwartaalEenAcht);
                                                        k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        k.setVierKwartaalAcht(kwartaalVierAcht);
                                                        k.setSuperMarkt(superMarktBM);
                                                        System.out.println(superMarktBM + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractBM).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractBM).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractBM).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractBM).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractBM).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractBM).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractBM).getContents();
                                                k.setEenKwartaalZeven(kwartaalEenZeven);
                                                k.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                k.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                k.setVierKwartaalZeven(kwartaalVierZeven);
                                                k.setEenKwartaalAcht(kwartaalEenAcht);
                                                k.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                k.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                k.setVierKwartaalAcht(kwartaalVierAcht);
                                                k.setSuperMarkt(superMarktBM);
                                                System.out.println(superMarktBM + " " + k.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractBM++;
                            }
                            break;
                        case " Plus":
                        int beginExtractPL = sheet.findCell(" Plus").getRow();
                        int eindPL = sheet.getRows();;
                        String superMarktPL = "Plus";

                            while (beginExtractPL < eindPL) {
                                String regel = sheet.getCell(1, beginExtractPL).getContents();
                                if (regel.contains("(")) {
                                    Pattern pattern = Pattern.compile(".* \\((\\d+)\\)");
                                    Matcher matcher = pattern.matcher(regel);

                                    if (matcher.matches()) {
                                        upc = matcher.group(1);
                                    }

                                    for (Koffie a : plus) {
                                        if (upc.equals(a.getUPC())) {
                                            String kwartaalEenZeven = sheet.getCell(2, beginExtractPL).getContents();

                                            if (kwartaalEenZeven.isEmpty()) {
                                                outerloop:
                                                for (int i = 1; i < 100; i++) {

                                                    kwartaalEenZeven = sheet.getCell(2, beginExtractPL - i).getContents();
                                                    if (kwartaalEenZeven.isEmpty() == false) {

                                                        String kwartaalTweeZeven = sheet.getCell(2, beginExtractPL - i).getContents();
                                                        String kwartaalDrieZeven = sheet.getCell(3, beginExtractPL - i).getContents();
                                                        String kwartaalVierZeven = sheet.getCell(4, beginExtractPL - i).getContents();
                                                        String kwartaalEenAcht = sheet.getCell(5, beginExtractPL - i).getContents();
                                                        String kwartaalTweeAcht = sheet.getCell(6, beginExtractPL - i).getContents();
                                                        String kwartaalDrieAcht = sheet.getCell(7, beginExtractPL - i).getContents();
                                                        String kwartaalVierAcht = sheet.getCell(8, beginExtractPL - i).getContents();
                                                        a.setEenKwartaalZeven(kwartaalEenZeven);
                                                        a.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                        a.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                        a.setVierKwartaalZeven(kwartaalVierZeven);
                                                        a.setEenKwartaalAcht(kwartaalEenAcht);
                                                        a.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                        a.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                        a.setVierKwartaalAcht(kwartaalVierAcht);
                                                        a.setSuperMarkt("henk");
                                                        System.out.println(superMarktPL + " " + a.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);
                                                        break outerloop;
                                                    }
                                                }
                                            } else {
                                                String kwartaalTweeZeven = sheet.getCell(2, beginExtractPL).getContents();
                                                String kwartaalDrieZeven = sheet.getCell(3, beginExtractPL).getContents();
                                                String kwartaalVierZeven = sheet.getCell(4, beginExtractPL).getContents();
                                                String kwartaalEenAcht = sheet.getCell(5, beginExtractPL).getContents();
                                                String kwartaalTweeAcht = sheet.getCell(6, beginExtractPL).getContents();
                                                String kwartaalDrieAcht = sheet.getCell(7, beginExtractPL).getContents();
                                                String kwartaalVierAcht = sheet.getCell(8, beginExtractPL).getContents();
                                                a.setEenKwartaalZeven(kwartaalEenZeven);
                                                a.setTweeKwartaalZeven(kwartaalTweeZeven);
                                                a.setDrieKwartaalZeven(kwartaalDrieZeven);
                                                a.setVierKwartaalZeven(kwartaalVierZeven);
                                                a.setEenKwartaalAcht(kwartaalEenAcht);
                                                a.setTweeKwartaalAcht(kwartaalTweeAcht);
                                                a.setDrieKwartaalAcht(kwartaalDrieAcht);
                                                a.setVierKwartaalAcht(kwartaalVierAcht);
                                                a.setSuperMarkt("henk");
                                                System.out.println(superMarktPL + " " + a.getUPC() + " " + kwartaalEenZeven + " " + kwartaalTweeZeven + " " + kwartaalDrieZeven + " " + kwartaalVierZeven + " " + kwartaalEenAcht + " " + kwartaalTweeAcht + " " + kwartaalDrieAcht + " " + kwartaalVierAcht);

                                            }
                                        }
                                    }
                                }
                                beginExtractPL++;
                            }
                            break;
                    }
                begin++;
            }
            System.out.println("hoi");
            }
        
        catch (IOException | BiffException ex) {
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

                                    kwartaalEenZeven = sheet.getCell(2, beginRij - i).getContents();
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
                            } else {
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
                }
                beginRij++;


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
            Logger.getLogger(ExcelExtract.class
                    .getName()).log(Level.SEVERE, null, ex);
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
