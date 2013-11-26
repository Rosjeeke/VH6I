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
public class Main_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ExtractA2 excelextracter = new ExtractA2();
        excelextracter.read();
    }
}
