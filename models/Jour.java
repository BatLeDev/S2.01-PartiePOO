package models;

import java.util.HashMap;

/**
 * Classe Jour permet de stocker sa date, sa temperature moyenne, son jour de la semaine et ses vacances scolaires
 */
public class Jour {

    // ----------------------------- static attributes -----------------------------
    
    /**
     * liste des jours crees
     */
    private static HashMap<String, Jour> jourList = new HashMap<String, Jour>();
    private static String[] valideJours = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
    private static String[] valideVacances = { "Noel", "Ascension", "Hiver", "Ete", "Toussaint", "Printemps" };

    // ----------------------------- static methods -----------------------------

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

    private static boolean dateValide(String date) {
        boolean ret = false;

        if (date != null && !date.isEmpty()) {
            String[] dateSplit = date.split("-");
            if (dateSplit.length == 3) {
                int annee = Integer.parseInt(dateSplit[0]);
                int mois = Integer.parseInt(dateSplit[1]);
                int jour = Integer.parseInt(dateSplit[2]);

                if ((jour > 0) && (jour < 32) && (mois > 0) && (mois < 13) && (annee > 2000)) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    private static boolean containString(String aChercher, String[] liste) {
        boolean ret = false;
        for (String s : liste) {
            if (s.equals(aChercher)) {
                ret = true;
            }
        }
        return ret;
    }

    // ----------------------------- attributes -----------------------------

    private String date;
    private String jour;
    private String vacances;
    private double temperatureMoyenne;

    // ----------------------------- constructor -----------------------------

    public Jour(String date, double temperatureMoyenne, String jour, String vacances) {
        if (!Jour.dateValide(date)) {
            throw new IllegalArgumentException("models.Jour.constructor : Le parametre date n'est pas valide");
        }
        if (Jour.jourList.containsKey(date)) {
            throw new IllegalArgumentException("models.Jour.constructor : Cette date est déjà créée");
        }

        this.date = date;
        try {
            this.setJour(jour);
            this.setVacances(vacances);
            this.setTemperatureMoyenne(temperatureMoyenne);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("models.Jour.constructor : " + e.getMessage());
        }

        Jour.jourList.put(date, this);
    }
    
    // ----------------------------- setters -----------------------------

    public void setJour(String jour) {
        if (jour == null || jour.isEmpty()) {
            throw new IllegalArgumentException(
                    "models.Jour.setJour : Le parametre jour n'est pas valide (null ou vide)");
        }

        if (!Jour.containString(jour, Jour.valideJours)) {
            throw new IllegalArgumentException("models.Jour.setJour : Ce jour n'existe pas ou est mal orthographié");
        }
        this.jour = jour;
    }
    
    public void setJour(int jour) {
        if (jour < 0 || jour > 6) {
            throw new IllegalArgumentException("models.Jour.setJour : Le parametre jour n'est pas valide (0-6))");
        }
        this.jour = Jour.valideJours[jour];
    }

    public void setVacances(String vacances) {
        if (vacances == null || vacances.isEmpty()) {
            this.vacances = null;
        } else {
            if (!Jour.containString(vacances, Jour.valideVacances)) {
                throw new IllegalArgumentException("models.Jour.setVacances : Ces vacances n'existent pas ou sont mal orthographiées");
            }
            this.vacances = vacances;
        }
    }

    public void setTemperatureMoyenne(double temperatureMoyenne) {
        if (temperatureMoyenne < -40 || temperatureMoyenne > 60) {
            throw new IllegalArgumentException(
                    "models.Jour.setTemperatureMoyenne : temperatureMoyenne abérante");
        }
        this.temperatureMoyenne = temperatureMoyenne;
    }

    // ----------------------------- getters -----------------------------

    public String getDate() {
        return this.date;
    }

    public double getTemperatureMoyenne() {
        return this.temperatureMoyenne;
    }

    public String getJour() {
        return this.jour;
    }

    public String getVacances() {
        return this.vacances;
    }
    
    public boolean estWeekEnd(){
        boolean ret = false;
        if (this.jour.equals("Samedi") || this.jour.equals("Dimanche")){
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
