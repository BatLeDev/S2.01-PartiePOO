package models;

import java.util.HashMap;

/**
 * This class is used to represent a compteur, with its id, its libelle, its
 * sens, its latitude and its longitude.
 * 
 * <p>
 * This class save all the compteur in a {@code HashMap}, with the id as key and
 * the {@link Compteur} as value.
 * If a Compteur belong to a {@link Quartier}, the compteur is added to the list
 * of compteur of the quartier
 */
public class Compteur {

    // ----------------------------- static attributes -----------------------------

    /**
     * HashMap containing all compteurs objects.
     * 
     * <p>In key it's the id of the compteur, in value it's the compteur object.
     * This {@code Hashmap} allow to get a compteur by his id and verify uniqueness of id
     */
    private static HashMap<Integer,Compteur> compteurList = new HashMap<Integer,Compteur>();

    // ----------------------------- static methods -----------------------------

    /**
     * Get a compteur by its id
     * 
     * @param id an integer representing the id of the compteur ({@code positive})
     * @return the {@link Compteur} corresponding to the id
     */
    public static Compteur getCompteur(int id) {
        return Compteur.compteurList.get(id);
    }

    /**
     * Delete a compteur by its id
     * 
     * @param id an integer representing the id of the compteur ({@code positive})
     * @return the {@link Compteur} corresponding to the id
     */
    public static Compteur deleteCompteur(int id) {
        Compteur c = Compteur.compteurList.remove(id);
        if (c != null) {
            Quartier quartier = c.getQuartier();
            if (quartier != null) {
                quartier.removeCompteur(id);
            }
        }
        ReleveJournalier.removeAllRelevesOfACompteur(id);
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
     * Constructor of the class Compteur with {@link Quartier}
     * 
     * <p>{@code libelle + sens = "libelle vers sens"}
     * 
     * @param id        an {@code integer} representing the id of the compteur ({@code positive})
     * @param libelle   a {@code String} representing the libelle of the compteur ({@code not null or empty})
     * @param sens      a {@code String} representing the sens of the compteur ({@code not null or empty})
     * @param latitude  a {@code double} representing the latitude of the compteur
     * @param longitude a {@code double} representing the longitude of the compteur
     * @param quartier  a {@link Quartier} representing the quartier of the compteur ({@code can be null})
     * 
     * @throws IllegalArgumentException if parameters are invalid
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
     * 
     * <p>{@code libelle + sens = "libelle vers sens"}
     * 
     * @param id        an {@code integer} representing the id of the compteur ({@code positive})
     * @param libelle   a {@code String} representing the libelle of the compteur ({@code not null or empty})
     * @param sens      a {@code String} representing the sens of the compteur ({@code not null or empty})
     * @param latitude  a {@code double} representing the latitude of the compteur
     * @param longitude a {@code double} representing the longitude of the compteur
     * 
     * @throws IllegalArgumentException if parameters are invalid
     */
    public Compteur(int id, String libelle, String sens, double latitude, double longitude) {
        this(id, libelle, sens, latitude, longitude, null);
    }
    
    // ----------------------------- setters -----------------------------
    
    /**
     * Setter for the libelle of the compteur
     * 
     * @param libelle a {@code String} representing the libelle of the compteur ({@code not null or empty})
     * 
     * @throws IllegalArgumentException if parameters {@code libelle} is {@code null} or empty
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
     * @param sens a {@code String} representing the sens of the compteur ({@code not null or empty})
     * 
     * @throws IllegalArgumentException if parameters {@code sens} is {@code null} or empty
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
     * @param latitude a {@code double} representing the latitude of the compteur
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * Setter for the longitude of the compteur
     * 
     * @param longitude a {@code double} representing the longitude of the compteur
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Setter for the quartier of the compteur
     * 
     * <p>Update the compteurList of the old and new {@link Quartier}.
     * 
     * @param quartier a {@link Quartier} representing the quartier of the compteur ({@code can be null})
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
     * Setter for the quartier of the compteur, with the id of the {@link Quartier}
     * <p>Update the compteur list of the old and new {@link Quartier}.
     * 
     * @param idQuartier an {@code integer} representing the id of the quartier of the compteur ({@code positive})
     * 
     * @throws IllegalArgumentException if {@code idQuartier} is {@code negative} or doesn't correspond to any {@link Quartier}
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
     * Get the {@code id} of the compteur
     * 
     * @return an {@code integer} representing the {@code id} of the compteur ({@code positive})
     */
    public int getId() {
        return this.idCompteur;
    }

    /**
     * Get the {@code libelle} of the compteur
     * 
     * @return a {@code String} representing the {@code libelle} of the compteur
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Get the {@code sens} of the compteur
     * 
     * @return a {@code String} representing the {@code sens} of the compteur
     */
    public String getSens() {
        return this.sens;
    }

    /**
     * Get the {@code latitude} of the compteur
     * 
     * @return a {@code double} representing the {@code latitude} of the compteur
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Get the {@code longitude} of the compteur
     * 
     * @return a {@code double} representing the {@code longitude} of the compteur
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Get the {@link Quartier} of the compteur
     *
     * @return a {@link Quartier} representing the {@link Quartier} of the compteur, {@code null} if the compteur doesn't belong to any quartier
     */
    public Quartier getQuartier() {
        return this.quartier;
    }

    /**
     * Get the position of the compteur.
     * 
     * @return a {@code double[]} of size 2 representing the position of the compteur.
     *         The format of the returned array is: {{@code latitude}, {@code longitude}}
     */
    public double[] getPosition() {
        double[] ret = {this.latitude, this.longitude};
        return ret;
    }
    
    // ----------------------------- prints -----------------------------
    
    /**
     * To show the compteur in a readable way
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
     * Convert the compteur to a CSV line. You can use this method to save the compteur in a CSV file.
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