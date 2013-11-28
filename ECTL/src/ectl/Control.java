/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ectl;

import java.util.ArrayList;

/**
 *
 * @author Ivo
 */
public class Control {
    
        private FixedWidthExtract fwe;
    private ExtractA2 exce;
    private AccessExtractor access;
    private MysqlDatabase db1;
    private ArrayList<Klant> klanten, klantenfwe, klantenexce, klantenaccess;
    
    public Control(){
        db1 = new MysqlDatabase("myfirstdwh");
        
        fwe = new FixedWidthExtract();
        exce = new ExtractA2();
        access = new AccessExtractor();
        
        klanten = new ArrayList<Klant>();

        
        fwe.extract(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant\\A4.txt");
        exce.read(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant\\A2.xls");
        access.extractor(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Bronbestanden\\Klant\\A1.mdb");
        
        extract();
        load();
        
    }
    
    public void extract(){
        klantenfwe = fwe.getKlanten();
        klantenexce = exce.getKlanten();
        klantenaccess = access.getKlanten();
    }
    
    public void load(){
        int i = 0;
        String query = "";
        
        klanten.addAll(klantenfwe);
        klanten.addAll(klantenexce);
        klanten.addAll(klantenaccess);
        
        for(Klant k : klanten){
            
            if(i<1){
            query = "INSERT INTO klant VALUES(  '"+k.getKlantNummer()+"',"
                                            + " '"+k.getKlantNaam()+"',"
                                            + " '"+k.getTelefoon()+"',"
                                            + " '"+k.getMobiel()+"',"
                                            + " '"+k.getPlaats()+"',"
                                            + " '"+k.getLand()+"',"
                                            + " '"+k.getPercentage()+"' )";
            i++;
            }
            
            else{
                query += ",('"+k.getKlantNummer()+"',"
                        + " '"+k.getKlantNaam()+"',"
                        + " '"+k.getTelefoon()+"',"
                        + " '"+k.getMobiel()+"',"
                        + " '"+k.getPlaats()+"',"
                        + " '"+k.getLand()+"',"
                        + " '"+k.getPercentage()+"' )";
            }
        }
        db1.sqlUpdate(query);

        //Sluiten van de verbinding
        db1.Sluitconnectie();
        
    }    
}
