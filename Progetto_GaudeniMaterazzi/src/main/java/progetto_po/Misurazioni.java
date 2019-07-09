package progetto_po;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Misurazioni implements Serializable {
    private double DMPS;
    private double CPC;

    Misurazioni(String dmps, String cpc) {

        this.DMPS = Double.parseDouble(dmps);
        this.CPC = Double.parseDouble(cpc);
    }

    @JsonIgnore
    public double getDMPS(){
        return DMPS;
    }

    @JsonIgnore
    public double getCPC(){
        return CPC;
    }

    @JsonIgnore
    public boolean equalmis(Misurazioni c) {
        if (this.DMPS == c.getDMPS() && this.CPC == c.getCPC()) return true;
        else return false;

    }
}

