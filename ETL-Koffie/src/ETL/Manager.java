/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ETL;

import java.util.ArrayList;

/**
 *
 * @author Rogier
 */
public class Manager {
    private MysqlDatabase db;
    private ArrayList<Koffie> infoscan, albert, superboer, c1000, superunie, coop, hoogvliet, jumbo, vomar, jan, deen, bonimarkt, plus;
    
    public Manager(){
                    ExcelExtract transform = new ExcelExtract();
                    transform.infoScanRead(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Opdracht 6 BCG Matrix\\datadump1.xls", 3);
                    transform.extractSwitch(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Opdracht 6 BCG Matrix\\datadump1.xls", 2);
                    infoscan = transform.getKoffie();
                    albert = transform.getAlbert();
                    superboer = transform.getSuperboer();
                    c1000 = transform.getC1000();
                    superunie = transform.getSuperunie();
                    coop = transform.getCoop();
                    hoogvliet = transform.getHoogvliet();
                    jumbo = transform.getJumbo();
                    vomar = transform.getVomar();
                    jan = transform.getJan();
                    deen = transform.getDeen();
                    bonimarkt = transform.getBonimarkt();
                    plus = transform.getPlus();
                    db = new MysqlDatabase("koffie");
        vulKoffieTijdVerkoper();
        vulVerkoopDatabase(albert, "Albert Heijn", db);
        vulVerkoopDatabase(superboer, "Super de Boer", db);
        vulVerkoopDatabase(c1000, "C1000", db);
        vulVerkoopDatabase(superunie, "Superunie", db);
        vulVerkoopDatabase(coop, "COOP Totaal", db);
        vulVerkoopDatabase(hoogvliet, "Hoogvliet", db);
        vulVerkoopDatabase(jumbo, "Jumbo", db);
        vulVerkoopDatabase(vomar, "Vomar", db);
        vulVerkoopDatabase(jan, "Jan Linders", db);
        vulVerkoopDatabase(deen, "Deen", db);
        vulVerkoopDatabase(bonimarkt, "Bonimarkt", db);
        vulVerkoopDatabase(plus, "Plus", db);
//        String albertQuery ="";
//        int a = 1;
//        for(Koffie kof : albert){
//             
//            if(a<2){
//                System.out.println("begin albertQuery");
//                System.out.println(kof.getEenKwartaalZeven());
//            albertQuery = "INSERT INTO Verkoop VALUES('1','1','"+a+"','"+kof.getEenKwartaalZeven()+"'),('2','1','"+a+"','"+kof.getTweeKwartaalZeven()+"'),('3','1','"+a+"','"+kof.getDrieKwartaalZeven()+"'),('4','1','"+a+"','"+kof.getVierKwartaalZeven()+"')"
//                    + ",('5','1','"+a+"','"+kof.getEenKwartaalAcht()+"'),('6','1','"+a+"','"+kof.getTweeKwartaalAcht()+"'),('7','1','"+a+"','"+kof.getDrieKwartaalAcht()+"'),('8','1','"+a+"','"+kof.getVierKwartaalAcht()+"')";
//            a++;}
//            else{
//                System.out.println("Toevoegen extra albertquery nummer "+a+" ");
//                albertQuery += ",('1','1','"+a+"','"+kof.getEenKwartaalZeven()+"'),('2','1','"+a+"','"+kof.getTweeKwartaalZeven()+"'),('3','1','"+a+"','"+kof.getDrieKwartaalZeven()+"'),('4','1','"+a+"','"+kof.getVierKwartaalZeven()+"')"
//                    + ",('5','1','"+a+"','"+kof.getEenKwartaalAcht()+"'),('6','1','"+a+"','"+kof.getTweeKwartaalAcht()+"'),('7','1','"+a+"','"+kof.getDrieKwartaalAcht()+"'),('8','1','"+a+"','"+kof.getVierKwartaalAcht()+"')";
//                a++;}   
//        }
//        //db.sqlUpdate(koffieQuery);
//        db.sqlUpdate(albertQuery);
        //db.sqlUpdate("INSERT INTO Tijd_Jaar Values('1', '2007'),('2','2008')");
        
    }
    private void vulKoffieTijdVerkoper(){
        db.sqlUpdate("INSERT INTO Tijd Values('1', '1', '2007'),('2', '2', '2007'),('3', '3', '2007'),('4', '4', '2007'),('5', '1', '2008'),('6', '2', '2008'),('7', '3', '2008'),('8', '4', '2008')");
        db.sqlUpdate("INSERT INTO Verkoper Values('1','Albert Heijn'),('2','Super de Boer'),('3','C1000'),('4','Superunie'),('5','COOP Totaal'),('6','Hoogvliet'),('7','Jumbo'),('8','Vomar'),('9','Jan Linders'),('10','Deen'),('11','Bonimarkt'),('12','Plus')");
        String koffieQuery = "";
        int i = 1;
        for(Koffie k : infoscan){
            
            if(i<2){
                System.out.println("begin koffieQuery");
            koffieQuery = "INSERT INTO koffie VALUES('"+i+"', '"+k.getMajorBrand()+"', '"+k.getApparaat()+"', '"+k.getType()+"', '"+k.getBeleving()+"', '"+k.getSoort()+"', '"+k.getFormaat()+"', '"+k.getSmaak()+"', '"+k.getUPC()+"' )";
            i++;}
            else{
                System.out.println("Toevoegen extra query nummer "+i+" ");
                koffieQuery += ",('"+i+"', '"+k.getMajorBrand()+"', '"+k.getApparaat()+"', '"+k.getType()+"', '"+k.getBeleving()+"', '"+k.getSoort()+"', '"+k.getFormaat()+"', '"+k.getSmaak()+"', '"+k.getUPC()+"' )";
            i++;}
        }
        db.sqlUpdate(koffieQuery);
    }
    private void vulVerkoopDatabase(ArrayList<Koffie> koffie, String superMarkt, MysqlDatabase db){
        int i = 1;
        String query = "";
        int markt = 0;
        
        switch (superMarkt) {
                    case "Albert Heijn":
                        markt = 1;
                        break;
                        case "Super de Boer":
                        markt = 2;
                        break;
                        case "C1000":
                        markt = 3;
                        break;
                        case "Superunie":
                        markt = 4;
                        break;
                        case "COOP Totaal":
                        markt = 5;
                        break;
                        case "Hoogvliet":
                        markt = 6;
                        break;
                        case "Jumbo":
                        markt = 7;
                        break;
                        case "Vomar":
                        markt = 8;
                        break;
                        case "Jan Linders":
                        markt = 9;
                        break;
                        case "Deen":
                        markt = 10;
                        break;
                        case "Bonimarkt":
                        markt = 11;
                        break;
                        case "Plus":
                        markt = 12;
                        break;}
        for(Koffie kof : koffie){
             
            if(i<2){
            query = "INSERT INTO Verkoop VALUES('1','"+markt+"','"+i+"','"+kof.getEenKwartaalZeven()+"'),('2','"+markt+"','"+i+"','"+kof.getTweeKwartaalZeven()+"'),('3','"+markt+"','"+i+"','"+kof.getDrieKwartaalZeven()+"'),('4','"+markt+"','"+i+"','"+kof.getVierKwartaalZeven()+"')"
                    + ",('5','"+markt+"','"+i+"','"+kof.getEenKwartaalAcht()+"'),('6','"+markt+"','"+i+"','"+kof.getTweeKwartaalAcht()+"'),('7','"+markt+"','"+i+"','"+kof.getDrieKwartaalAcht()+"'),('8','"+markt+"','"+i+"','"+kof.getVierKwartaalAcht()+"')";
            i++;}
            else{
                query += ",('1','"+markt+"','"+i+"','"+kof.getEenKwartaalZeven()+"'),('2','"+markt+"','"+i+"','"+kof.getTweeKwartaalZeven()+"'),('3','"+markt+"','"+i+"','"+kof.getDrieKwartaalZeven()+"'),('4','"+markt+"','"+i+"','"+kof.getVierKwartaalZeven()+"')"
                    + ",('5','"+markt+"','"+i+"','"+kof.getEenKwartaalAcht()+"'),('6','"+markt+"','"+i+"','"+kof.getTweeKwartaalAcht()+"'),('7','"+markt+"','"+i+"','"+kof.getDrieKwartaalAcht()+"'),('8','"+markt+"','"+i+"','"+kof.getVierKwartaalAcht()+"')";
                i++;}   
        }
        //db.sqlUpdate(koffieQuery);
        System.out.println(query);
        db.sqlUpdate(query);
        
    
    }
    
    public void sluitDatabaseVerbinding(){
        boolean y = false;
        db.Sluitconnectie();
        if (y) {
            System.out.println("Database verbinding gesloten!");
        } else {
            System.out.println("Database verbinding niet gesloten!");
        }
    }
}
