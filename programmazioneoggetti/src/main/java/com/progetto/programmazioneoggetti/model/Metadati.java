package com.progetto.programmazioneoggetti.model;

/**
 *
 * @author Sandro Materazzi
 * @author Francesco Gaudeni
 */

public class Metadati {

    //Attributi

    private String alias;
    private String type;

    //Metodi

    /**
     * Crea un oggetto con determinati alias e type
     * @param alias nome dell'attributo
     * @param type tipo dell'attributo
     */
    public Metadati(String alias, String type) {

        this.alias = alias;
        this.type = type;
    }


    /**
     * Fornisce l'alias di una certo metadato.
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Imposta l'alias di un metadato.
     * @param alias nome dell'attributo
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Fornisce il tipo di una certo metadato.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Imposta il type di un metadato.
     * @param type tipo dell'attributo
     */
    public void setType(String type) {
        this.type = type;
    }

    public String toString(){

        return "Metadata [alias=" + alias + ", type=" + type + "]";
    }
}


