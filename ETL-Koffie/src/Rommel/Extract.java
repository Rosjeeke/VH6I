/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rommel;

import Rommel.AccessExtractor;
import ETL.ExcelExtract;
import Rommel.FixedWidthExtract;
import ETL.GUI.MainFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Liannie
 */
public class Extract {
    private File file;
	private Boolean hasnext;
	private String extension;
	private MainFrame mf;
        private ExcelExtract excel;
        private FixedWidthExtract txt;
        private AccessExtractor access;
        private ArrayList<Klant> klanten;

	public Extract(File file, MainFrame mainFrame, String path){
                klanten = new ArrayList<Klant>();
		this.file = file;
		this.mf = mainFrame;
		Scanner x = null;
		hasnext = false;
		//Fills extension-string with the proper extension.
		int dotPos = file.getName().lastIndexOf(".");
		extension = file.getName().substring(dotPos);

		//add more extension here and refer them to a proper procedure-class!!! 
		if(extension.equals(".txt")){
			System.out.println("tekstbestand. Op komma controleren.");
			try{ 
				x = new Scanner(new BufferedReader (new FileReader (file)));
				if(x.findInLine(",") != null){

					System.out.println("CSV");

				}
				else {
					System.out.println("TXT");
                                        txt = new FixedWidthExtract();
                                        txt.extract(path);
                                        klanten = txt.getKlanten();
                                        
				}
			}
			catch(Exception e){
				System.out.println("Fout bij komma-zoeker");
			}


		}
		else if(extension.equals(".xls")){
			System.out.println("Excel");
                        excel = new ExcelExtract();
                        excel.read(path);
                        //excel.print();
                        excel.getKlanten();
		}
		else if(extension.equals(".mdb")){
			System.out.println("MDB");
                        access = new AccessExtractor();
                        access.extractor(path);
                        access.getKlanten();
		}

		else{ System.out.println("Invalid file.."); }
	}
        
        public ArrayList<Klant> getKlanten(){
            return klanten;
        }
    
}
