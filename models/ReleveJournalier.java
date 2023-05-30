package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ReleveJournalier {
    
    // -------------- Attributs Static -------------- //


    private static final String[] typeAnomalie = {"Faible","Forte"};

    private static HashMap<Integer,ArrayList<ReleveJournalier>> ReleveComptList = new HashMap<Integer,ArrayList<ReleveJournalier>>();
    private static HashMap<String,ArrayList<ReleveJournalier>> ReleveJourList = new HashMap<String,ArrayList<ReleveJournalier>>();


    private static boolean typeAnomalieValide(String type) {
        boolean ret = true;
        if (type != null){
            ret = false;
            for (String s : typeAnomalie) {
                if (s.equals(type)) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    private static boolean relevesHeuresValide(int[] relevesHeures) {
        boolean ret = false;

        if (relevesHeures != null) {
            if (relevesHeures.length == 24) {
                int i = 0;
                while ((i < relevesHeures.length) && (relevesHeures[i] >= 0)) {
                    i++;
                }
                if (i == 24){
                    ret = true;
                }
            }
        }
        return ret;
    }


    public static ArrayList<ReleveJournalier> getRelevePourJour (String date){
        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier> (ReleveJourList.get(date));
        return ret;
    }

    public static ArrayList<ReleveJournalier> getRelevePourCompteur (int idCompteur){
        ArrayList<ReleveJournalier> ret = new ArrayList<ReleveJournalier> (ReleveComptList.get(idCompteur));
        return ret;
    }

    /**
     * retourne le Releve Journalier correspondant a la date et au Compteur passe en parametre
     * @param date la date du releve
     * @param idCompteur l'identifiant du compteur du releve
     * @return le Releve Journalier correspondant a la date passee en parametre
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

    public static void clear () {
        ReleveComptList.clear();
        ReleveJourList.clear();
    }


    //////////////////////////////////////////////////////////////////////////
    
    private int leCompteur;
    private String leJour;
    private int[] relevesHeures;
    String presenceAnomalie;

    public ReleveJournalier (int leCompteur , String leJour , int[] relevesHeures , String presenceAnomalie){
        if ((Compteur.getCompteur(leCompteur) == null)){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre leCompteur n'est pas valide");
        }
        if ((leJour == null) && (Jour.getJour(leJour) == null)){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre leJour n'est pas valide");
        }
        if (!ReleveJournalier.relevesHeuresValide(relevesHeures)){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre relevesHeures n'est pas valide");
        }
        if (!ReleveJournalier.typeAnomalieValide(presenceAnomalie)){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre presenceAnomalie n'est pas valide");
        }
        
        this.leCompteur = leCompteur;
        this.leJour = leJour;
        this.relevesHeures = relevesHeures;
        this.presenceAnomalie = presenceAnomalie;
    }

    public int getLeCompteur() {
        return leCompteur;
    }

    public String getLeJour() {
        return leJour;
    }

    public int[] getRelevesHeures() {
        int[] tab = new int[24];
        for (int i = 0; i < 24; i++){
            tab[i] = relevesHeures[i];
        }
        return tab;
    }

    public String getPresenceAnomalie() {
        return presenceAnomalie;
    }

    public void setLeCompteur(int leCompteur) {
        if (leCompteur == 0){
            throw new IllegalArgumentException("models.ReleveJournalier.setLeCompteur : Le parametre leCompteur n'est pas valide");
        }
        this.leCompteur = leCompteur;
    }

    public void setLeJour(String leJour) {
        if (leJour == null){
            throw new IllegalArgumentException("models.ReleveJournalier.setLeJour : Le parametre leJour n'est pas valide");
        }
        this.leJour = leJour;
    }

    public void setRelevesHeures(int[] relevesHeures) {
        if (!ReleveJournalier.relevesHeuresValide(relevesHeures)){
            throw new IllegalArgumentException("models.ReleveJournalier.setRelevesHeures : Le parametre relevesHeures n'est pas valide");
        }
        this.relevesHeures = relevesHeures;
    }

    public void setPresenceAnomalie(String presenceAnomalie) {
        if (!ReleveJournalier.typeAnomalieValide(presenceAnomalie)){
            throw new IllegalArgumentException("models.ReleveJournalier.setPresenceAnomalie : Le parametre presenceAnomalie n'est pas valide");
        }
        this.presenceAnomalie = presenceAnomalie;
    }

    public double getPassageHoraire (int heure){
        if ((heure < 0) || (heure > 23)){
            throw new IllegalArgumentException("models.ReleveJournalier.getPassageHoraire : Le parametre heure n'est pas valide");
        }
        return relevesHeures[heure];
    }

    public void setPassageHoraire (int heure , int nbPassage){
        if ((heure < 0) || (heure > 23)){
            throw new IllegalArgumentException("models.ReleveJournalier.setPassageHoraire : Le parametre heure n'est pas valide");
        }
        if (nbPassage < 0){
            throw new IllegalArgumentException("models.ReleveJournalier.setPassageHoraire : Le parametre nbPassage n'est pas valide");
        }
        relevesHeures[heure] = nbPassage;
    }

    public int getPassageJour() {
        int ret = 0;
        for (int i = 0; i < relevesHeures.length; i++) {
            ret += relevesHeures[i];
        }
        return ret;
    }

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
