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
                while (i < relevesHeures.length && relevesHeures[i] >= 0) {
                    i++;
                }
                if (i == 24){
                    ret = true;
                }
            }
        }
        return ret;
    }

}
