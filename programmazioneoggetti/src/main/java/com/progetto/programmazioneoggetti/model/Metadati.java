package com.progetto.programmazioneoggetti.model;

/**
 *
 * @author Sandro Materazzi
 * @author Francesco Gaudeni
 */

public class Metadati {

    private String alias;
    private String type;


    public Metadati(String alias, String type) {

        this.alias = alias;
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){

        return "Metadata [alias=" + alias + ", type=" + type + "]";
    }
}


