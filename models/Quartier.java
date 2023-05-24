package models;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Quartier
 * This class is used to represent a quartier, with its id, its nom and its
 * length of track
 * 
 * This class save all the quartier in a HashMap, with the id as key
 * Each quartier has a list of compteur
 */
public class Quartier {
    
    // ----------------------------- static attributes -----------------------------

    /**
     * HashMap containing all quartier's objects
     * The key is the id of the quartier
     * The value is the quartier object
     * This HashMap allow to get a quartier by his id and verify uniqueness of id
     */
    private static HashMap<Integer,Quartier> quartierList = new HashMap<Integer,Quartier>();

    // ----------------------------- static methods -----------------------------

    /**
     * Get a quartier by his id
     * 
     * @return the quartier object corresponding to the id, null if not found
     */
    public static Quartier getQuartierById(int id) {
        return Quartier.quartierList.get(id);
    }

    /**
     * Delete a quartier by his id
     * 
     * @return the quartier object corresponding to the id, null if not found
     */
    public static Quartier delQuartierById(int id) {
        return quartierList.remove(id);
    }

    // ----------------------------- attributes -----------------------------
    private int idQuartier;
    private String nomQuartier;
    private double lgPisteCyclable;
    private ArrayList<Integer> idCompteurs;

    // ----------------------------- constructor -----------------------------

    /**
     * Constructor of the class Quartier
     * can launch an IllegalArgumentException in setters if the parameters are not valid
     * 
     * @param id                an integer representing the (unique) id of the quartier (positive)
     * @param nom               a String representing the name of the quartier (not null or empty)
     * @param lgPisteCyclable   a double representing the length of the track of the quartier (positive)
     */
    public Quartier(int id, String nom, double lgPisteCyclable) {
        if (id < 0 || quartierList.containsKey(id)) {
            throw new IllegalArgumentException("models.Quartier.constructor : l'id est invalide (<0 ou deja existant)");
        }

        this.idQuartier = id;
        this.setNom(nom);
        this.setLgPisteCyclable(lgPisteCyclable);
        this.idCompteurs = new ArrayList<Integer>();
            
        Quartier.quartierList.put(id, this); // add the quartier to the quartierList
    }
    
    // ----------------------------- setters -----------------------------

    /**
     * Set the name of the quartier
     * 
     * @param nomQuartier a String representing the name of the quartier (not null or empty)
     */
    public void setNom(String nomQuartier) {
        if (nomQuartier == null || nomQuartier.isEmpty()) {
            throw new IllegalArgumentException("models.Quartier.setNom : Le parametre nom n'est pas valide");
        }
        this.nomQuartier = nomQuartier;
    }

    /**
     * Set the length of the track of the quartier
     * 
     * @param lgPisteCyclable a double representing the length of the track of the quartier (positive)
     */
    public void setLgPisteCyclable(double lgPisteCyclable) {
        if (lgPisteCyclable < 0) {
            throw new IllegalArgumentException(
                    "models.Quartier.setLgPisteCyclable : La longueur de la piste doit être positive");
        }
        this.lgPisteCyclable = lgPisteCyclable;
    }

    // ----------------------------- getters -----------------------------

    /**
     * Get the id of the quartier
     * 
     * @return an int representing the id of the quartier
     */
    public int getId() {
        return this.idQuartier;
    }

    /**
     * Get the name of the quartier
     * 
     * @return a String representing the name of the quartier
     */
    public String getNom() {
        return this.nomQuartier;
    }

    /**
     * Get the length of the track of the quartier
     * 
     * @return a double representing the length of the track of the quartier
     */
    public double getLgPisteCyclable() {
        return this.lgPisteCyclable;
    }
    
    /**
     * Get the list of compteur of the quartier
     * 
     * @return an ArrayList of Integer representing the list of compteur of the quartier
     */
    public ArrayList<Integer> getCompteursList() {
        return new ArrayList<Integer>(this.idCompteurs);
    }
    
    // ----------------------------- add/remove -----------------------------

    /**
     * Add a compteur to the quartier
     * /!\ the quartierId of the compteur is not changed
     * 
     * @param idCompteur an int representing the id of the compteur to add (positive)
     */
    protected void addCompteur(int idCompteur) {
        if (idCompteur < 0) {
            throw new IllegalArgumentException("models.Quartier.addCompteur : L'id du compteur doit être positif");
        }
        if (!this.idCompteurs.contains(idCompteur)) { // if the compteur is not already in the list
            this.idCompteurs.add(idCompteur);
        }
    }

    /**
     * Remove a compteur from the quartier
     * /!\ the quartierId of the compteur is not changed
     * 
     * @param idCompteur an int representing the id of the compteur to remove (positive)
     */
    protected void removeCompteur(int idCompteur) {
        if (idCompteur < 0) {
            throw new IllegalArgumentException("models.Quartier.removeCompteur : L'id du compteur doit être positif");
        }
        this.idCompteurs.remove(idCompteur);
    }

    // ----------------------------- prints -----------------------------

    /**
     * Get a String representing the quartier
     * 
     * @return "nomQuartier#idQuartier : longueurPiste = lgPisteCyclable"
     */
    public String toString() {
        String ret = this.nomQuartier + "#" + this.idQuartier + " : longueurPiste = " + lgPisteCyclable;
        return ret;
    }

    /**
     * Get a String representing the quartier in CSV format
     * 
     * @return "nomQuartier;idQuartier;lgPisteCyclable"
     */
    public String toCSV() {
        String ret = this.nomQuartier + ";" + this.idQuartier + ";" + lgPisteCyclable;
        return ret;
    }
}
