package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ReleveJournalier {
    
    // ----------------------------- static attributes -----------------------------
    private static HashMap<Integer, ArrayList<ReleveJournalier>> ReleveComptList = new HashMap<Integer, ArrayList<ReleveJournalier>>();
    private static HashMap<String, ArrayList<ReleveJournalier>> ReleveJourList = new HashMap<String, ArrayList<ReleveJournalier>>();
    
    private static final String[] typeAnomalie = {"Faible","Forte"};

    // ----------------------------- static methods -----------------------------

    /**
     * Check if the type is valid (null or in the list of typeAnomalie)
     * @param type the type to check
     * @return true if the type is valid, false otherwise
     */
    private static boolean typeAnomalieValide(String type) {
        boolean ret = true;

        if (type != null) {
            ret = false;
            for (String e : typeAnomalie) {
                if (e.equals(type)) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    /**
     * Check if the relevesHeures is valid (not null, length = 24, all values >= 0)
     * @param relevesHeures the relevesHeures to check
     * @return true if the relevesHeures is valid, false otherwise
     */
    private static boolean relevesHeuresValide(int[] relevesHeures) {
        boolean ret = false;

        if (relevesHeures != null && relevesHeures.length == 24) { // Check valid length
            ret = true;
            int i = 0;
            while (i < relevesHeures.length && ret) { // Check all values >= 0
                if (relevesHeures[i] < 0) {
                    ret = false;
                }
            }
        }
        return ret;
    }

    /**
     * Get an ArrayList of the ReleveJournalier associated to a Jour
     * @param idCompteur the date of the Compteur (YYYY-MM-DD)
     * @return an ArrayList of ReleveJournalier 
     */
    public static ArrayList<ReleveJournalier> getRelevePourJour (String date){
        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier> (ReleveJourList.get(date));
        return ret;
    }

    /**
     * Get an ArrayList of the ReleveJournalier associated to a Compteur
     * @param idCompteur the id of the Compteur
     * @return an ArrayList of ReleveJournalier 
     */
    public static ArrayList<ReleveJournalier> getRelevePourCompteur (int idCompteur){
        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier> (ReleveComptList.get(idCompteur));
        return ret;
    }

    /**
     * Get the ReleveJournalier by the date of its Jour and the id of its Compteur
     * @param date the date of the Jour associated (YYYY-MM-DD)
     * @param idCompteur the id of the Compteur associated
     * @return the ReleveJournalier corresponding to the date and the id
     */
    public static ReleveJournalier getReleveJournalier (String date, int idCompteur) {
        ReleveJournalier ret = null;
        ArrayList<ReleveJournalier> listJour = getRelevePourJour(date);
        ArrayList<ReleveJournalier> listCompt = getRelevePourCompteur(idCompteur);

        Iterator<ReleveJournalier> itJour = listJour.iterator();
        boolean trouve = false;
        while (itJour.hasNext() && !trouve){

            Iterator<ReleveJournalier> itCompt = listCompt.iterator();
            ReleveJournalier tmp = itJour.next();
            while (itCompt.hasNext() && !trouve) {
                if (tmp == itCompt.next()){
                    trouve = true;
                    ret = tmp;
                }
            }
        }
        return ret;
    }

    /**
     * Remove a ReleveJournalier saved by the date of the Jour associated and the id of the Compteur associatedd.
     * If the ReleveJournalier doesn't exist, do nothing
     * @param date the date of the Jour associated (YYYY-MM-DD)
     * @param idCompteur the id of the Compteur associatedd
     */
    public static void removeReleveJournalier (String date, int idCompteur) {
        ReleveJournalier releve = getReleveJournalier(date, idCompteur);
        if (releve != null){
            ReleveComptList.get(idCompteur).remove(releve);
            ReleveJourList.get(date).remove(releve);
        }
    }

    /**
     * Remove all the ReleveJournalier associated to this Compteur
     * If the ReleveJournalier doesn't exist, do nothing
     * @param idCompteur the id of the Compteur associatedd
     */
    public static void removeReleveJournalierPourCompteur(int idCompteur){
        for (ReleveJournalier releve : getRelevePourCompteur(idCompteur)) {
            removeReleveJournalier(releve.getLeJour(), idCompteur);
        }
    }

    /**
     * Remove all the ReleveJournalier associated to this Compteur
     * If the ReleveJournalier doesn't exist, do nothing
     * @param date the date of the Jour associated (YYYY-MM-DD)
     */
    public static void removeReleveJournalierPourJour(String date){
        for (ReleveJournalier releve : getRelevePourJour(date)) {
            removeReleveJournalier(date, releve.getLeCompteur());
        }
    }

    /**
     * Remove all the ReleveJournalier saved
     */
    public static void clear () {
        ReleveComptList.clear();
        ReleveJourList.clear();
    }

    // ----------------------------- attributes -----------------------------
    private int leCompteur;
    private String leJour;
    private int[] relevesHeures;
    String presenceAnomalie;

    /**
     * Constructor of the class ReleveJournalier
     * can launch an IllegalArgumentException if the parameters are not valid
     * 
     * @param leCompteur the id of the compteur (positive)
     * @param leJour the day of the releve (not null or empty)
     * @param relevesHeures the relevesHeures of the releve (not null, length = 24, all values >= 0)
     * @param presenceAnomalie the presenceAnomalie of the releve (null or in the list of typeAnomalie)
     */
    public ReleveJournalier (int leCompteur , String leJour , int[] relevesHeures , String presenceAnomalie){
        if (Compteur.getCompteur(leCompteur) == null){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre leCompteur n'est pas valide");
        }
        if (Jour.getJour(leJour) == null){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre leJour n'est pas valide");
        }
        this.leCompteur = leCompteur;
        this.leJour = leJour;

        try {
            this.setRelevesHeures(relevesHeures);
            this.setPresenceAnomalie(presenceAnomalie);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : " + e.getMessage());
        }
    }

    // ----------------------------- getters -----------------------------

    /**
     * Get the compteur of the releve
     * @return the compteur id of the releve
     */
    public int getLeCompteur() {
        return leCompteur;
    }

    /**
     * Get the day of the releve
     * @return the date of the releve
     */
    public String getLeJour() {
        return leJour;
    }

    /**
     * Get the relevesHeures of the releve
     * 
     * @return an array of int representing the relevesHeures of the releve
     */
    public int[] getRelevesHeures() {
        int[] tab = new int[24];
        for (int i = 0; i < 24; i++){
            tab[i] = relevesHeures[i];
        }
        return tab;
    }

    /**
     * Get the presenceAnomalie of the releve
     * @return null, "Faible" or "Forte"
     */
    public String getPresenceAnomalie() {
        return presenceAnomalie;
    }

    // ----------------------------- setters -----------------------------

    /**
     * Set the relevesHeures of the releve
     * can launch an IllegalArgumentException if the parameters are not valid
     * 
     * @param relevesHeures the relevesHeures of the releve (not null, length = 24, all values >= 0)
     */
    public void setRelevesHeures(int[] relevesHeures) {
        if (!ReleveJournalier.relevesHeuresValide(relevesHeures)){
            throw new IllegalArgumentException("models.ReleveJournalier.setRelevesHeures : Le parametre relevesHeures n'est pas valide");
        }
        this.relevesHeures = relevesHeures;
    }

    /**
     * Set the presenceAnomalie of the releve
     * @param presenceAnomalie the presenceAnomalie of the releve (null or in the list of typeAnomalie)
     */
    public void setPresenceAnomalie(String presenceAnomalie) {
        if (!ReleveJournalier.typeAnomalieValide(presenceAnomalie)){
            throw new IllegalArgumentException("models.ReleveJournalier.setPresenceAnomalie : Le parametre presenceAnomalie n'est pas valide");
        }
        this.presenceAnomalie = presenceAnomalie;
    }

    /**
     * Set the relevesHeures of the releve at the hour
     * can launch an IllegalArgumentException if the parameters are not valid
     * 
     * @param heure the hour of the releve (between 0 and 23)
     * @param nbPassage the relevesHeures of the releve at the hour (positive or 0)
     */
    public void setPassageHoraire (int heure , int nbPassage){
        if ((heure < 0) || (heure > 23)){
            throw new IllegalArgumentException("models.ReleveJournalier.setPassageHoraire : Le parametre heure n'est pas valide");
        }
        if (nbPassage < 0){
            throw new IllegalArgumentException("models.ReleveJournalier.setPassageHoraire : Le parametre nbPassage n'est pas valide");
        }
        relevesHeures[heure] = nbPassage;
    }

    // ----------------------------- methods -----------------------------

    /**
     * Get the relevesHeures of the releve at the hour
     * can launch an IllegalArgumentException if the parameters are not valid
     * 
     * @param heure the hour of the releve (between 0 and 23)
     * @return the relevesHeures of the releve at the hour
     */
    public double getPassageHoraire(int heure) {
        if ((heure < 0) || (heure > 23)) {
            throw new IllegalArgumentException(
                    "models.ReleveJournalier.getPassageHoraire : Le parametre heure n'est pas valide");
        }
        return relevesHeures[heure];
    }

    /**
     * Get the total relevesHeures of the releve
     * @return the total relevesHeures of the releve
     */
    public int getNbPassageJour() {
        int ret = 0;
        for (int i = 0; i < relevesHeures.length; i++) {
            ret += relevesHeures[i];
        }
        return ret;
    }

    /**
     * Get the average relevesHeures of the releve
     * @return the average relevesHeures of the releve
     */
    public double getMoyennePassageHoraire(){
        double ret = 0;
        int nbPassage = 0;
        for (int i = 0; i < relevesHeures.length; i++) {
            ret += relevesHeures[i] * i;
            nbPassage += relevesHeures[i];
        }
        if (nbPassage != 0){
            ret = ret / nbPassage;
        }
        return ret;
    }
}
