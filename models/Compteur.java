package models;

import java.util.HashMap;

/**
 * Class compteur
 * This class is used to represent a compteur, with its id, its libelle, its sens, its latitude and its longitude
 * 
 * This class save all the compteur in a HashMap, with the id as key
 */
public class Compteur {

    /**
     * HashMap containing all the compteur
     * The key is the id of the compteur
     * The value is the compteur object
     */
    private static HashMap<Integer,Compteur>compteurList=new HashMap<Integer,Compteur>();

    // attributes
    private int idCompteur;
    private String libelle;
    private String sens;
    private double latitude;
    private double longitude;
    private Quartier quartier;

    /**
     * Constructor of the class Compteur
     * libelle + sens = "libelle vers sens"
     * can launch an IllegalArgumentException in setters if the parameters are not valid
     * 
     * @param id        an integer representing the (unique) id of the compteur (positive)
     * @param libelle   a String representing the libelle of the compteur (not null or empty)
     * @param sens      a String representing the sens of the compteur (not null or empty)
     * @param latitude  a double representing the latitude of the compteur (positive)
     * @param longitude a double representing the longitude of the compteur (positive)
     * @param quartier  a Quartier object representing the quartier of the compteur (not null)
     */
    public Compteur(int id, String libelle, String sens, double latitude, double longitude, Quartier quartier) {
        if (id < 0 || !compteurList.containsKey(id)) {
            throw new IllegalArgumentException("models.Compteur.constructor : l'id est invalide (<0 ou deja existant)");
        }

        this.idCompteur = id;
        this.setLibelle(libelle);
        this.setSens(sens);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setQuartier(quartier);

        // add the compteur to the compteurList
        Compteur.compteurList.put(id, this);
    }

    /**
     * Constructor of the class Compteur
     * libelle + sens = "libelle vers sens"
     * can launch an IllegalArgumentException in setters if the parameters are not valid
     * 
     * @param id        an integer representing the id of the compteur (positive)
     * @param libelle   a String representing the libelle of the compteur (not null or empty)
     * @param sens      a String representing the sens of the compteur (not null or empty)
     * @param latitude  a double representing the latitude of the compteur (positive)
     * @param longitude a double representing the longitude of the compteur (positive)
     * @param idQuartier an integer representing the id of the quartier of the compteur (positive)
     */
    public Compteur(int id, String libelle, String sens, double latitude, double longitude, int idQuartier) {
        this(id, libelle, sens, latitude, longitude, Quartier.getQuartierById(idQuartier));
    }
    
    // ----------------------------- setters -----------------------------

    /**
     * Setter for the libelle of the compteur
     * 
     * @param libelle a String representing the libelle of the compteur (not null or empty)
     */
    public void setLibelle(String libelle) {
        if (libelle == null || libelle.isEmpty()) {
            throw new IllegalArgumentException("models.Compteur.setLibelle : Le libelle n'est pas valide");
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
            throw new IllegalArgumentException("models.Compteur.setSens : Le sens n'est pas valide");
        }
        this.sens = sens;
    }

    /**
     * Setter for the latitude of the compteur
     * 
     * @param latitude a double representing the latitude of the compteur (positive)
     */
    public void setLatitude(double latitude) {
        if (latitude < 0) {
            throw new IllegalArgumentException("models.Compteur.setLatitude : La latitude doit être positive");
        }
        this.latitude = latitude;
    }
    
    /**
     * Setter for the longitude of the compteur
     * 
     * @param longitude a double representing the longitude of the compteur (positive)
     */
    public void setLongitude(double longitude) {
        if (longitude < 0) {
            throw new IllegalArgumentException("models.Compteur.setLongitude : La longitude doit être positive");
        }
        this.longitude = longitude;
    }

    /**
     * Setter for the quartier of the compteur
     * 
     * @param quartier a Quartier object representing the quartier of the compteur (not null)
     */
    public void setQuartier(Quartier quartier) {
        if (quartier == null) {
            throw new IllegalArgumentException("models.Compteur.setQuartier : Le quartier n'est pas valide");
        }
        this.quartier = quartier;
    }

    /**
     * Setter for the quartier of the compteur
     * 
     * @param idQuartier an integer representing the id of the quartier of the compteur (positive)
     */
    public void setQuartier(int idQuartier) {
        this.setQuartier(Quartier.getQuartierById(idQuartier));
    }

    // ----------------------------- getters -----------------------------

    /**
     * Get a compteur by his id
     * 
     * @return the compteur object corresponding to the id
     */
    public static Compteur getCompteurById(int id) {
        return Compteur.compteurList.get(id);
    }

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

    // ----------------------------- dropper -----------------------------

    /**
     * Delete a compteur by his id
     * 
     * @return the compteur object corresponding to the id
     */
    public static Compteur delQuartierById(int id) {
        return Compteur.compteurList.remove(id);
    }

    // ----------------------------- views -----------------------------

    /**
     * Get a String representing the compteur
     * 
     * @return "libelle vers sens#idCompteur : quartier = idQuartier, latitude = latitude, longitude = getLongitude"
     */
    public String toString() {
        String ret = this.libelle + "vers" + this.sens + "#" + this.idCompteur +
                " : quartier = " + this.quartier.getId() + ", latitude = " + this.latitude + ", longitude = " + this.longitude;
        return ret;
    }
    
    /**
     * Get a String representing the compteur
     * 
     * @return "idCompteur;libelle;sens;latitude;longitude;idQuartier"
     */
    public String toCSV() {
        String ret = this.idCompteur + ";" + this.libelle + ";" + this.sens + ";" + this.latitude + ";" + this.longitude + ";" + this.quartier.getId();
        return ret;
    }
}