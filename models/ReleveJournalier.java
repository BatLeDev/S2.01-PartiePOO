package models;

import java.util.HashMap;

enum TypeAnomalie {
    Faible, Forte;
}

public class ReleveJournalier {
    
    // private static HashMap<Integer,ReleveJournalier> ReleveJournalierList = new HashMap<Integer,ReleveJournalier>();

    private Compteur leCompteur;
    private Jour leJour;
    private int[] relevesHeures;
    TypeAnomalie presenceAnomalie;

    public ReleveJournalier (Compteur leCompteur , Jour leJour , int[] relevesHeures , TypeAnomalie presenceAnomalie){
        if (leCompteur == null){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre leCompteur n'est pas valide");
        }
        if (leJour == null){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre leJour n'est pas valide");
        }
        if (!this.relevesHeuresValide(relevesHeures)){
            throw new IllegalArgumentException("models.ReleveJournalier.constructor : Le parametre relevesHeures n'est pas valide");
        }
        
        this.leCompteur = leCompteur;
        this.leJour = leJour;
        this.relevesHeures = relevesHeures;
        this.presenceAnomalie = presenceAnomalie;
    }

    private boolean relevesHeuresValide(int[] relevesHeures) {
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

    public Compteur getLeCompteur() {
        return leCompteur;
    }

    public Jour getLeJour() {
        return leJour;
    }

    public int[] getRelevesHeures() {
        int[] tab = new int[24];
        for (int i = 0; i < 24; i++){
            tab[i] = relevesHeures[i];
        }
        return tab;
    }

    public TypeAnomalie getPresenceAnomalie() {
        return presenceAnomalie;
    }

    public void setLeCompteur(Compteur leCompteur) {
        if (leCompteur == null){
            throw new IllegalArgumentException("models.ReleveJournalier.setLeCompteur : Le parametre leCompteur n'est pas valide");
        }
        this.leCompteur = leCompteur;
    }

    public void setLeJour(Jour leJour) {
        if (leJour == null){
            throw new IllegalArgumentException("models.ReleveJournalier.setLeJour : Le parametre leJour n'est pas valide");
        }
        this.leJour = leJour;
    }

    public void setRelevesHeures(int[] relevesHeures) {
        if (!this.relevesHeuresValide(relevesHeures)){
            throw new IllegalArgumentException("models.ReleveJournalier.setRelevesHeures : Le parametre relevesHeures n'est pas valide");
        }
        this.relevesHeures = relevesHeures;
    }

    public void setPresenceAnomalie(TypeAnomalie presenceAnomalie) {
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
