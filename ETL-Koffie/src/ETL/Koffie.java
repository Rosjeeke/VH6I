package ETL;

public class Koffie
    {
        private String majorBrand;
        private String apparaat;
        private String type;
        private String beleving;
        private String soort;
        private String formaat;
        private String smaak;
        private String upc;
        private String eenKwartaalZeven, tweeKwartaalZeven, drieKwartaalZeven, vierKwartaalZeven, eenKwartaalAcht, tweeKwartaalAcht, drieKwartaalAcht, vierKwartaalAcht, superMarkt;
    

    public Koffie(String majorBrand, String apparaat, String type, String beleving, String soort, String formaat, String smaak, String upc, String eenKwartaalZeven, String tweeKwartaalZeven, String drieKwartaalZeven, String vierKwartaalZeven, String eenKwartaalAcht, String tweeKwartaalAcht, String drieKwartaalAcht, String vierKwartaalAcht, String superMarkt) 
    {
        this.majorBrand = majorBrand;
        this.apparaat = apparaat;
        this.type = type;
        this.beleving = beleving;
        this.soort = soort;
        this.formaat = formaat;
        this.smaak = smaak;
        this.upc = upc;   
        this.eenKwartaalZeven = eenKwartaalZeven;
        this.tweeKwartaalZeven = tweeKwartaalZeven;
        this.drieKwartaalZeven = drieKwartaalZeven;
        this.vierKwartaalZeven = vierKwartaalZeven;
        this.eenKwartaalAcht = eenKwartaalAcht;
        this.tweeKwartaalAcht = tweeKwartaalAcht;
        this.drieKwartaalAcht = drieKwartaalAcht;
        this.vierKwartaalAcht = vierKwartaalAcht;
        this.superMarkt = superMarkt;
    }
    
    public Koffie getNewCopy(String majorBrand, String apparaat, String type, String beleving, String soort, String formaat, String smaak, String upc, String eenKwartaalZeven, String tweeKwartaalZeven, String drieKwartaalZeven, String vierKwartaalZeven, String eenKwartaalAcht, String tweeKwartaalAcht, String drieKwartaalAcht, String vierKwartaalAcht, String superMarkt)
    { 
        Koffie k = new Koffie(majorBrand, apparaat, type, beleving, soort, formaat, smaak, upc, eenKwartaalZeven, tweeKwartaalZeven, drieKwartaalZeven, vierKwartaalZeven, eenKwartaalAcht, tweeKwartaalAcht, drieKwartaalAcht, vierKwartaalAcht, superMarkt);
        k.majorBrand = majorBrand;
        k.apparaat = apparaat;
        k.type = type;
        k.beleving = beleving;
        k.soort = soort;
        k.formaat = formaat;
        k.smaak = smaak;
        k.upc = upc;
        k.eenKwartaalZeven = eenKwartaalZeven;
        k.tweeKwartaalZeven = tweeKwartaalZeven;
        k.drieKwartaalZeven = drieKwartaalZeven;
        k.vierKwartaalZeven = vierKwartaalZeven;
        k.eenKwartaalAcht = eenKwartaalAcht;
        k.tweeKwartaalAcht = tweeKwartaalAcht;
        k.drieKwartaalAcht = drieKwartaalAcht;
        k.vierKwartaalAcht = vierKwartaalAcht;
        return k;
    }
    
    public String getMajorBrand() 
    {
        return majorBrand;
    }

    public void setMajorBrand(String majorBrand) 
    {
        this.majorBrand = majorBrand;
    }

    public String getApparaat() 
    {
        return apparaat;
    }

    public void setApparaat(String apparaat) 
    {
        this.apparaat = apparaat;
    }

    public String getType() 
    {
        return type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getBeleving() 
    {
        return beleving;
    }

    public void setBeleving(String beleving) 
    {
        this.beleving = beleving;
    }

    public String getSoort() 
    {
        return soort;
    }

    public void setSoort(String soort) 
    {
        this.soort = soort;
    }

    public String getFormaat() 
    {
        return formaat;
    }

    public void setFormaat(String formaat) 
    {
        this.formaat = formaat;
    }

    public String getSmaak() 
    {
        return smaak;
    }

    public void setSmaak(String smaak) 
    {
        this.smaak = smaak;
    }

    public String getUPC() 
    {
        return upc;
    }

    public void setUPC(String uPC) 
    {
        this.upc = uPC;
    }

    public void setEenKwartaalZeven(String eenKwartaalZeven) 
    {
        this.eenKwartaalZeven = eenKwartaalZeven;
    }

    public String getEenKwartaalZeven()
    {
        return eenKwartaalZeven;
    }
    
    public void setTweeKwartaalZeven(String tweeKwartaalZeven) 
    {
        this.tweeKwartaalZeven = tweeKwartaalZeven;
    }

    public void setDrieKwartaalZeven(String drieKwartaalZeven) 
    {
        this.drieKwartaalZeven = drieKwartaalZeven;
    }

    public void setVierKwartaalZeven(String vierKwartaalZeven) 
    {
        this.vierKwartaalZeven = vierKwartaalZeven;
    }

    public void setEenKwartaalAcht(String eenKwartaalAcht) 
    {
        this.eenKwartaalAcht = eenKwartaalAcht;
    }

    public void setTweeKwartaalAcht(String tweeKwartaalAcht) 
    {
        this.tweeKwartaalAcht = tweeKwartaalAcht;
    }

    public void setDrieKwartaalAcht(String drieKwartaalAcht) 
    {
        this.drieKwartaalAcht = drieKwartaalAcht;
    }

    public void setVierKwartaalAcht(String vierKwartaalAcht) 
    {
        this.vierKwartaalAcht = vierKwartaalAcht;
    }
    
    public String getSuperMarkt()
    {
        return superMarkt;
    }
    
    public void setSuperMarkt(String superMarkt)
    {
        this.superMarkt = superMarkt;
    }

    public String getTweeKwartaalZeven() 
    {
        return tweeKwartaalZeven;
    }

    public String getDrieKwartaalZeven() 
    {
        return drieKwartaalZeven;
    }

    public String getVierKwartaalZeven() 
    {
        return vierKwartaalZeven;
    }

    public String getEenKwartaalAcht() 
    {
        return eenKwartaalAcht;
    }

    public String getTweeKwartaalAcht() 
    {
        return tweeKwartaalAcht;
    }

    public String getDrieKwartaalAcht() 
    {
        return drieKwartaalAcht;
    }

    public String getVierKwartaalAcht() 
    {
        return vierKwartaalAcht;
    }  
}