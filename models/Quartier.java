package models;

import java.util.HashMap;

public class Quartier {
    private static HashMap<Integer,Quartier>quartierList=new HashMap<Integer,Quartier>();

    private int idQuartier;
    private String nomQuartier;
    private double lgPisteCyclable;

    public Quartier(int id, String nom, double lgPisteCyclable) {
        if (id < 0 || !quartierList.containsKey(id)) {
            throw new IllegalArgumentException("models.Quartier.constructor : l'id est invalide (<0 ou deja existant)");
        }
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("models.Quartier.constructor : Le parametre nom n'est pas valide");
        }
        if (lgPisteCyclable<0){
            throw new IllegalArgumentException("models.Quartier.constructor : La longueur de la piste doit être positive");
        }
        this.idQuartier = id;
        this.nomQuartier = nom;
        this.lgPisteCyclable = lgPisteCyclable;
        Quartier.quartierList.put(id, this);
        
    }

    // setters

    // getters
    public static Quartier getQuartierById(int id) {
        return quartierList.get(id);
    }

    // dropper
    public static Quartier delQuartierById(int id) {
        return quartierList.remove(id);
    }

    // prints
    public String toString() {
        String ret = this.nomQuartier + "#" + this.idQuartier + " : longueurPiste = " + lgPisteCyclable;
        return ret;
    }
}