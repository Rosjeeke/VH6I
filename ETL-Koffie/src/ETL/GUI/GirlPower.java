/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ETL.GUI;

import ETL.MysqlDatabase;

/**
 *
 * @author Liannie
 */
public class GirlPower {
    private MysqlDatabase db;
    
    public GirlPower(){
    //Database aanmaken
    db = new MysqlDatabase("Koffie");
    
    
    //Eenmalig deze uitvoeren. Kan via PHPadmin van te voren in gevoerd zijn of via de code. Hetzelfde verhaal voor  de supermarkten! -Die hebben we even weggelaten-
    String sqlTijd = "INSERT INTO tijd VALUES (1,1,2007), (2,2,2007), (3,3,2007),(4,4,2007),(5,1,2008),(6,2,2008),(7,3,2008),(8,4,2008);";
    db.sqlUpdate(sqlTijd);
    
    String sqlTijd_Jaar = "INSERT INTO tijd_jaar VALUES (1,2007), (2,2008);";
    db.sqlUpdate(sqlTijd_Jaar);
    
    //Alle koffiesoorten ophalen. Arraylist Koffies wordt door lopen en elke koffie wordt behandeld.
    for(koffie:koffies){
        if(koffie.getSupermarkt = "Infoscan Supermarkten"){
            String sqlKoffie = "INSERT INTO koffie VALUES ("+koffie.getMayorBrand+","+koffie.getApparaat+","+koffie.getType+","+koffie.getBeleving+","+koffie.getSoort+","+koffie.getFormaat+","+koffie.getSmaak+","+koffie.getUPC+");";
        }
}
    for(koffie:koffies){  
        String sqlKoffieID = "SELECT KoffieID FROM koffie WHERE Mayor Brand = '"+koffie.getMayorBrand+"' AND Apparaat = '"+koffie.getApparaat+"' AND Type = '"+koffie.getType+"' AND Beleving = '"+koffie.getBeleving+"' AND Soort = '"+koffie.getSoort+"' AND Formaat = '"+koffie.getFormaat+"' AND Smaak = '"+koffie.getSmaak+"' AND Universial Product Code = '"+koffie.getUPC+"';";
        int KoffieID = db.sqlExecute(sqlKoffieID); //Klopt niet helemaal
        
        String sqlSupermarktID = "SELECT VerkoperID FROM verkoper WHERE Verkopernaam ='"+koffie.getSupermarkt"';";
        int SupermarktID = db.sqlExecute(sqlSupermarktID); //Klopt ook niet helemaal
        
        String sqlGegevens = "INSERT INTO verkoop VALUES (1,"+SupermarktID+","+KoffieID","+koffie.getEenKwartaalZeven+"),(2,"+SupermarktID+","+KoffieID","+koffie.getTweeKwartaalZeven+"),(3,"+SupermarktID+","+KoffieID","+koffie.getDrieKwartaalZeven+"),(4,"+SupermarktID+","+KoffieID","+koffie.getVierKwartaalZeven+"),(5,"+SupermarktID+","+KoffieID","+koffie.getEenKwartaalAcht+"),(6,"+SupermarktID+","+KoffieID","+koffie.getTweeKwartaalAcht+"),(7,"+SupermarktID+","+KoffieID","+koffie.getDrieKwartaalAcht+"),(8,"+SupermarktID+","+KoffieID","+koffie.getVierKwartaalAcht+");"
        db.sqlUpdate(sqlGegevens);
        
        int aantal2007 = koffie.getEenKwartaalZeven + koffie.getTweeKwartaalZeven + koffie.getDrieKwartaalZeven + koffie.getVierKwartaalZeven;
        int aantal2008 = koffie.getEenKwartaalAcht + koffie.getTweeKwartaalAcht + koffie.getDrieKwartaalAcht + koffie.getVierKwartaalAcht;
        
        String sqlVerkoop_Jaar = "INSERT INTO verkoop_jaar VALUES (1,"+SupermarktID+","+KoffieID+","+aantal2007"), (1,"+SupermarktID+","+KoffieID+","+aantal2008")"
    }
}
}
