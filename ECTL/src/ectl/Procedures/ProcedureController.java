package Procedures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import ectl.ExtractA2;

import GUI.MainFrame;

//Determines which procedure this file should go through. A request is sent in by MainFrame.
public class ProcedureController {

	private File file;
	private Boolean hasnext;
	private String extension;
	private MainFrame mf;

	public ProcedureController(File file, MainFrame mainFrame, String path){
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
				}
			}
			catch(Exception e){
				System.out.println("Fout bij komma-zoeker");
			}


		}
		else if(extension.equals(".xls")){
			System.out.println("Excel");
                        ExtractA2 excelextracter = new ExtractA2();
                        excelextracter.read(path);
		}
		else if(extension.equals(".mdb")){
			System.out.println("MDB");
		}

		else{ System.out.println("Invalid file.."); }
	}

}
