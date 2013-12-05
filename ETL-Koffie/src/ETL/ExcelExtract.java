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
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Liannie
 */
public class ExcelExtract {
    private ArrayList<Klant> klanten;
    
    public ExcelExtract(){
        klanten = new ArrayList<Klant>();
    }
       
    public void read(String bestand){
        try {
            Workbook workbook = Workbook.getWorkbook(new File(bestand));
            Sheet sheet = workbook.getSheet(0);
            int aantalrijen = sheet.getRows();
            
            int rij = 1;
            while(rij<aantalrijen){
                Cell nr = sheet.getCell(0, rij);
                int nrInt = Integer.parseInt(nr.getContents());
                Cell naam = sheet.getCell(1, rij);
                Cell telefoon = sheet.getCell(2, rij);
                Cell plaats = sheet.getCell(3, rij);
                Cell land = sheet.getCell(4, rij);
                Cell percentage = sheet.getCell(5, rij);
                BigDecimal percentageInt = new BigDecimal(percentage.getContents());
                        //Integer.parseInt(percentage.getContents());
                
                Klant nieuw = new Klant(nrInt, naam.getContents(), telefoon.getContents(), "", plaats.getContents(), land.getContents(), percentageInt);
                klanten.add(nieuw);
                
                rij++;
            }
            
        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExcelExtract.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
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
    
    public ArrayList<Klant> getKlanten(){
            return klanten;
        }
    
}
