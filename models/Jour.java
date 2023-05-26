package models;

import java.util.HashMap;


/**
 * les jours de la semaine
 */
enum JourSemaine {
    LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE;
}

/**
 * les vacances scolaires
 */
enum Vacances {
    Noel, Ascension, Hiver, Ete, Toussaint, Printemps;
}

/**
 * Classe Jour permet de stocker sa date, sa temperature moyenne, son jour de la semaine et ses vacances scolaires
 */
public class Jour {

    // Static
    
    /**
     * liste des jours crees
     */
    private static HashMap<String,Jour> jourList = new HashMap<String,Jour>();

    /**
     * retourne le jour correspondant a la date passee en parametre
     * @param date la date du jour 
     * @return le jour correspondant a la date passee en parametre
     */
    public static Jour getJour (String date) {
        return Jour.jourList.get(date);
    }

    /**
     * Supprime le jour correspondant a la date passee en parametre
     * @param date la date du jour a supprimer
     */
    public static void deleteJour (String date) {
        Jour.jourList.remove(date);
    }


    // non static

    private String date;
    private double temperatureMoyenne;
    private JourSemaine jour;
    private Vacances vacances;

    public Jour (int id, String date , double temperatureMoyenne , JourSemaine jour , Vacances vacances){
        if (!this.dateValide(date)){
            throw new IllegalArgumentException("models.Jour.constructor : Le parametre date n'est pas valide");
        }
        if (Jour.jourList.containsKey(date)){
            throw new IllegalArgumentException("models.Jour.constructor : Le parametre date est deja utilise");
        }
        if (jour == null){
            throw new IllegalArgumentException("models.Jour.constructor : Le parametre jour n'est pas valide");
        }
        
        this.date = date;
        this.temperatureMoyenne = temperatureMoyenne;
        this.jour = jour;
        this.vacances = vacances;

        Jour.jourList.put(date, this);
    }

    private boolean dateValide(String date) {
        boolean ret = false;

        if (date != null && !date.isEmpty()) {
            String[] dateSplit = date.split("-");
            if (dateSplit.length == 3) {
                int annee = Integer.parseInt(dateSplit[0]);
                int mois = Integer.parseInt(dateSplit[1]);
                int jour = Integer.parseInt(dateSplit[2]);

                if ((jour > 0) && (jour < 32) && (mois > 0) && (mois < 13) && (annee > 0)) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public String getDate () {
        return this.date;
    }

    public double getTemperatureMoyenne () {
        return this.temperatureMoyenne;
    }

    public JourSemaine getJour () {
        return this.jour;
    }

    public Vacances getVacances () {
        return this.vacances;
    }

    public void setTemperatureMoyenne (double temperatureMoyenne) {
        this.temperatureMoyenne = temperatureMoyenne;
    }

    public void setJour (JourSemaine jour) {
        if (jour == null){
            throw new IllegalArgumentException("models.Jour.setJour : Le parametre jour n'est pas valide");
        }
        this.jour = jour;
    }

    public void setVacances (Vacances vacances) {
        this.vacances = vacances;
    }

    public boolean estWeekEnd(){
        boolean ret = false;
        if (this.jour == JourSemaine.SAMEDI || this.jour == JourSemaine.DIMANCHE){
            ret = true;
        }
        return ret;
    }

    public boolean estVacances(){
        boolean ret = false;
        if (this.vacances != null){
            ret = true;
        }
        return ret;
    }



}
