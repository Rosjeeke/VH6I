/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ectl;

import java.math.BigDecimal;

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
    private BigDecimal percentage;

    public Klant(int klantNummer, String klantNaam, String telefoon, String mobiel, String plaats, String land, BigDecimal percentage) {
        this.klantNummer = klantNummer;
        this.klantNaam = klantNaam;
        this.telefoon = telefoon;
        this.mobiel = mobiel;
        this.plaats = plaats;
        this.land = land;
        this.percentage = percentage;
    }

    public int getKlantNummer() {
        return klantNummer;
    }

    public void setKlantNummer(int klantNummer) {
        this.klantNummer = klantNummer;
    }

    public String getKlantNaam() {
        return klantNaam;
    }

    public void setKlantNaam(String klantNaam) {
        this.klantNaam = klantNaam;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getMobiel() {
        return mobiel;
    }

    public void setMobiel(String mobiel) {
        this.mobiel = mobiel;
    }

    public String getPlaats() {
        return plaats;
    }
    
    public void setPlaats(String plaats){
        this.plaats = plaats;
    }

    public String getLand() {
        return land;
    }
    
    public void setLand(String land){
        this.land = land;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }
    
    public void setPercentage(BigDecimal percentage){
        this.percentage = percentage;
    }
}
