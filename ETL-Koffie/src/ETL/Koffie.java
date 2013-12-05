/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ETL;

import java.math.BigDecimal;

/**
 *
 * @author Liannie
 */
public class Koffie {

    private String majorBrand;
    private String apparaat;
    private String type;
    private String beleving;
    private String soort;
    private String formaat;
    private String smaak;
    private int uPC;
    private int eenKwartaalZeven;
    private int tweeKwartaalZeven;
    private int drieKwartaalZeven;
    private int vierKwartaalZeven;
    private int eenKwartaalAcht;
    private int tweeKwartaalAcht;
    private int drieKwartaalAcht;
    private int vierKwartaalAcht;
    

    public Koffie(String majorBrand, String apparaat, String type, String beleving, String soort,
            String formaat, String smaak, int uPC) {
        this.majorBrand = majorBrand;
        this.apparaat = apparaat;
        this.type = type;
        this.beleving = beleving;
        this.soort = soort;
        this.formaat = formaat;
        this.smaak = smaak;
        this.uPC = uPC;   
        this.eenKwartaalZeven = 0;
        this.tweeKwartaalZeven = 0;
        this.drieKwartaalZeven = 0;
        this.vierKwartaalZeven = 0;
        this.eenKwartaalAcht = 0;
        this.tweeKwartaalAcht = 0;
        this.drieKwartaalAcht = 0;
        this.vierKwartaalAcht = 0;
    }

    public String getMajorBrand() {
        return majorBrand;
    }

    public void setMajorBrand(String majorBrand) {
        this.majorBrand = majorBrand;
    }

    public String getApparaat() {
        return apparaat;
    }

    public void setApparaat(String apparaat) {
        this.apparaat = apparaat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeleving() {
        return beleving;
    }

    public void setBeleving(String beleving) {
        this.beleving = beleving;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public String getFormaat() {
        return formaat;
    }

    public void setFormaat(String formaat) {
        this.formaat = formaat;
    }

    public String getSmaak() {
        return smaak;
    }

    public void setSmaak(String smaak) {
        this.smaak = smaak;
    }

    public int getUPC() {
        return uPC;
    }

    public void setUPC(int uPC) {
        this.uPC = uPC;
    }

    public void setEenKwartaalZeven(int eenKwartaalZeven) {
        this.eenKwartaalZeven = eenKwartaalZeven;
    }

    public void setTweeKwartaalZeven(int tweeKwartaalZeven) {
        this.tweeKwartaalZeven = tweeKwartaalZeven;
    }

    public void setDrieKwartaalZeven(int drieKwartaalZeven) {
        this.drieKwartaalZeven = drieKwartaalZeven;
    }

    public void setVierKwartaalZeven(int vierKwartaalZeven) {
        this.vierKwartaalZeven = vierKwartaalZeven;
    }

    public void setEenKwartaalAcht(int eenKwartaalAcht) {
        this.eenKwartaalAcht = eenKwartaalAcht;
    }

    public void setTweeKwartaalAcht(int tweeKwartaalAcht) {
        this.tweeKwartaalAcht = tweeKwartaalAcht;
    }

    public void setDrieKwartaalAcht(int drieKwartaalAcht) {
        this.drieKwartaalAcht = drieKwartaalAcht;
    }

    public void setVierKwartaalAcht(int vierKwartaalAcht) {
        this.vierKwartaalAcht = vierKwartaalAcht;
    }

    
    
}

