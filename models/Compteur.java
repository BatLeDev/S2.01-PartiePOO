package models;

import java.util.HashMap;

public class Compteur {
    private static HashMap<Integer,Compteur>compteurList=new HashMap<Integer,Compteur>();

    private int idCompteur;
    private String libelle;
    private String sens;
    private double latitude;
    private double longitude;

    public Compteur(int id, String libelle, String sens, double latitude, double longitude) {
        if (id < 0 || !compteurList.containsKey(id)) {
            throw new IllegalArgumentException("models.Compteur.constructor : l'id est invalide (<0 ou deja existant)");
        }
        
        if (libelle == null || libelle.isEmpty()) {
            throw new IllegalArgumentException("models.Compteur.constructor : Le libelle n'est pas valide");
        }
        if (sens == null || sens.isEmpty()) {
            throw new IllegalArgumentException("models.Compteur.constructor : Le sens n'est pas valide");
        }
        
        if (latitude<0){
            throw new IllegalArgumentException("models.Compteur.constructor : La latitude doit être positive");
        }
        if (longitude<0){
            throw new IllegalArgumentException("models.Compteur.constructor : La longitude doit être positive");
        }
        
        this.idCompteur = id;
        this.libelle = libelle;
        this.sens = sens;
        this.latitude = latitude;
        this.longitude = longitude;
        
        Compteur.compteurList.put(id, this);
    }

    // setters

    // getters
    public static Compteur getQuartierById(int id) {
        return Compteur.compteurList.get(id);
    }

    // dropper
    public static Compteur delQuartierById(int id) {
        return Compteur.compteurList.remove(id);
    }

    // prints
    public String toString() {
        String ret = this.libelle + "vers" + this.sens + "#" + this.idCompteur +
        " : latitude = " + this.latitude + " longitude = " + this.longitude;
        return ret;
    }

}