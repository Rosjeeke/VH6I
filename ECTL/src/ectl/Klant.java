/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelExtract;

/**
 *
 * @author Liannie
 */
public class Klant {
    private int klantNummer;
    private String klantNaam;
    private String telefoon;
    private String mobiel;
    private String plaats;
    private String land;
    private int percentage;
    
    public Klant(int klantNummer, String klantNaam, String telefoon, String mobiel, String plaats, String land, int percentage){
        this.klantNummer = klantNummer;
        this.klantNaam = klantNaam;
        this.telefoon = telefoon;
        this.mobiel = mobiel;
        this.plaats = plaats;
        this.land = land;
        this.percentage = percentage;        
    }
    
}
