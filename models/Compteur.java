package models;

import java.util.HashMap;

/**
 * Class compteur
 * This class is used to represent a compteur, with its id, its libelle, its sens, its latitude and its longitude
 * 
 * This class save all the compteur in a HashMap, with the id as key
 * If a compteur belong to a quartier, the compteur is added to the list of compteur of the quartier
 */
public class Compteur {

    // ----------------------------- static attributes -----------------------------

    /**
     * HashMap containing all compteurs objects
     * In key it's the id of the compteur
     * In value it's the compteur object
     * This HashMap allow to get a compteur by his id and verify uniqueness of id
     */
    private static HashMap<Integer,Compteur> compteurList = new HashMap<Integer,Compteur>();

    // ----------------------------- static methods -----------------------------

    /**
     * Get a compteur by his id
     * 
     * @return the compteur object corresponding to the id
     */
    public static Compteur getCompteur(int id) {
        return Compteur.compteurList.get(id);
    }

    /**
     * Delete a compteur by his id
     * 
     * @return the compteur object corresponding to the id
     */
    public static Compteur deleteCompteur(int id) {
        Compteur c = Compteur.compteurList.remove(id);
        if (c != null && c.getQuartier() != null) {
            c.getQuartier().removeCompteur(id);
        }
        return c;
    }

    // ----------------------------- attributes -----------------------------

    private int idCompteur;
    private String libelle;
    private String sens;
    private double latitude;
    private double longitude;
    private Quartier quartier;

    /**
     * Constructor of the class Compteur with the quartier object
     * libelle + sens = "libelle vers sens"
     * can launch an IllegalArgumentException in setters if the parameters are not valid
     * 
     * @param id        an integer representing the (unique) id of the compteur (positive)
     * @param libelle   a String representing the libelle of the compteur (not null or empty)
     * @param sens      a String representing the sens of the compteur (not null or empty)
     * @param latitude  a double representing the latitude of the compteur
     * @param longitude a double representing the longitude of the compteur
     * @param quartier  a Quartier object representing the quartier of the compteur
     */
    public Compteur(int id, String libelle, String sens, double latitude, double longitude, Quartier quartier) {
        if (id < 0 || compteurList.containsKey(id)) {
            throw new IllegalArgumentException("models.Compteur.constructor : l'id est invalide ( < 0 ou deja existant )");
        }

        this.idCompteur = id;
        try {
            this.setLibelle(libelle);
            this.setSens(sens);
            this.setLatitude(latitude);
            this.setLongitude(longitude);
            this.setQuartier(quartier);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("models.Compteur.constructor : " + e.getMessage());
        }

        // add the compteur to the compteurList
        Compteur.compteurList.put(id, this);
    }

    /**
     * Constructor of the class Compteur without quartier
     * libelle + sens = "libelle vers sens"
     * can launch an IllegalArgumentException in setters if the parameters are not valid
     * 
     * @param id        an integer representing the id of the compteur (positive)
     * @param libelle   a String representing the libelle of the compteur (not null or empty)
     * @param sens      a String representing the sens of the compteur (not null or empty)
     * @param latitude  a double representing the latitude of the compteur
     * @param longitude a double representing the longitude of the compteur
     */
    public Compteur(int id, String libelle, String sens, double latitude, double longitude) {
        this(id, libelle, sens, latitude, longitude, null);
    }

    // ----------------------------- setters -----------------------------

    /**
     * Setter for the libelle of the compteur
     * 
     * @param libelle a String representing the libelle of the compteur (not null or empty)
     */
    public void setLibelle(String libelle) {
        if (libelle == null || libelle.isEmpty()) {
            throw new IllegalArgumentException("models.Compteur.setLibelle : Le libelle ne peut pas être null ou vide");
        }
        this.libelle = libelle;
    }

    /**
     * Setter for the sens of the compteur
     * 
     * @param sens a String representing the sens of the compteur (not null or empty)
     */
    public void setSens(String sens) {
        if (sens == null || sens.isEmpty()) {
            throw new IllegalArgumentException("models.Compteur.setSens : Le sens ne peut pas être null ou vide");
        }
        this.sens = sens;
    }

    /**
     * Setter for the latitude of the compteur
     * 
     * @param latitude a double representing the latitude of the compteur
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * Setter for the longitude of the compteur
     * 
     * @param longitude a double representing the longitude of the compteur
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Setter for the quartier of the compteur
     * 
     * @param quartier a Quartier object representing the quartier of the compteur
     */
    public void setQuartier(Quartier quartier) {
        // remove the compteur from the old quartier
        if (this.quartier != null) {
            this.quartier.removeCompteur(this.idCompteur);
        }
        this.quartier = quartier; // set the new quartier (or null)
        if (quartier != null) {
            this.quartier.addCompteur(this.idCompteur); // add the compteur to the new quartier
        }
    }

    /**
     * Setter for the quartier of the compteur
     * 
     * @param idQuartier an integer representing the id of the quartier of the compteur (positive)
     */
    public void setQuartier(int idQuartier) {
        if (idQuartier < 0) {
            throw new IllegalArgumentException(
                    "models.Compteur.setQuartier : L'id du quartier ne peut pas être négatif");
        }
        
        Quartier q = Quartier.getQuartier(idQuartier); // set the new quartier
        if (q == null) {
            throw new IllegalArgumentException(
                    "models.Compteur.setQuartier : L'id du quartier ne correspond à aucun quartier");
        }
        this.setQuartier(q);
    }

    // ----------------------------- getters -----------------------------

    /**
     * Get the id of the compteur
     * 
     * @return the id of the compteur
     */
    public int getId() {
        return this.idCompteur;
    }

    /**
     * Get the libelle of the compteur
     * 
     * @return the libelle of the compteur
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Get the sens of the compteur
     * 
     * @return the sens of the compteur
     */
    public String getSens() {
        return this.sens;
    }

    /**
     * Get the latitude of the compteur
     * 
     * @return the latitude of the compteur
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Get the longitude of the compteur
     * 
     * @return the longitude of the compteur
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Get the quartier of the compteur
     * 
     * @return the quartier of the compteur
     */
    public Quartier getQuartier() {
        return this.quartier;
    }

    /**
     * Get the position of the compteur
     * format : [latitude, longitude]
     * 
     * @return the position of the compteur
     */
    public double[] getPosition() {
        double[] ret = {this.latitude, this.longitude};
        return ret;
    }

    // ----------------------------- prints -----------------------------

    /**
     * Get a String representing the compteur
     * 
     * @return "libelle vers sens#idCompteur : quartier = idQuartier, latitude = latitude, longitude = getLongitude"
     */
    public String toString() {
        String ret = this.libelle + " vers " + this.sens + "#" + this.idCompteur + " : quartier = ";
        if (this.quartier == null) {
            ret += "null";
        } else {
            ret += this.quartier.getId() + "#";
        }
        ret+= ", latitude = " + this.latitude + ", longitude = " + this.longitude;
        return ret;
    }
    
    /**
     * Get a String representing the compteur
     * 
     * @return "idCompteur;libelle;sens;latitude;longitude;idQuartier"
     */
    public String toCSV() {
        String ret = this.idCompteur + ";" + this.libelle + ";" + this.sens + ";" + this.latitude + ";" + this.longitude + ";";
        if (this.quartier == null) {
            ret += "null";
        } else {
            ret += this.quartier.getId();
        }
        return ret;
    }
}