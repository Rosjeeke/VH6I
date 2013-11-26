/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelExtract;

import java.io.File;
import java.io.IOException;
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
public class ExtractA2{
    private ArrayList<Klant> klanten;
    
    public ExtractA2(){
        klanten = new ArrayList<Klant>();
    }
       
    public void read(){
        try {
            Workbook workbook = Workbook.getWorkbook(new File("C://Users/Liannie/Google Drive/School/VH6I/ETL/A2.xls"));
            Sheet sheet = workbook.getSheet(0);
            int aantalrijen = sheet.getRows();
                        
            int rij = 1;
            while(rij<aantalrijen){
                Cell nr = sheet.getCell(0, rij);
                int nrInt = Integer.parseInt(nr.getContents());
                Cell naam = sheet.getCell(1, rij);
                Cell telefoon = sheet.getCell(2, rij);
                Cell mobiel = sheet.getCell(3, rij);
                Cell plaats = sheet.getCell(4, rij);
                Cell land = sheet.getCell(5, rij);
                Cell percentage = sheet.getCell(6, rij);
                int percentageInt = Integer.parseInt(percentage.getContents());
                
                Klant nieuw = new Klant(nrInt, naam.getContents(), telefoon.getContents(), mobiel.getContents(), plaats.getContents(), land.getContents(), percentageInt);
                klanten.add(nieuw);
                
                rij++;
            }
            
        } catch (IOException | BiffException ex) {
            Logger.getLogger(ExtractA2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
}
