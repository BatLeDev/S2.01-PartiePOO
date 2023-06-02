package models;

import java.util.HashMap;

/*
 * Class Jour
 * This class is used to represent a day, with its date, its day, its holidays and its average temperature
 * 
 * This class save all the days in a HashMap, with the date as key and the Jour object as value
 */
public class Jour {

    // ----------------------------- static attributes -----------------------------
    
    /**
     * HashMap containing all Jour objects
     */
    private static HashMap<String, Jour> jourList = new HashMap<String, Jour>();

    private static final String[] VALIDEJOUR = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
    private static final String[] VALIDEVACANCES = { "Noel", "Ascension", "Hiver", "Ete", "Toussaint", "Printemps" };

    // ----------------------------- static methods -----------------------------

    /**
     * Get a jour by his date
     * 
     * @param date the date of the jour to get (format : YYYY-MM-DD)
     * @return the jour object corresponding to the date
     */
    public static Jour getJour (String date) {
        return Jour.jourList.get(date);
    }

    /**
     * Delete a jour by his date
     * 
     * @param date the date of the jour to delete (format : YYYY-MM-DD)
     */
    public static void deleteJour (String date) {
        Jour.jourList.remove(date);
        ReleveJournalier.removeReleveJournalierPourJour(date);
    }

    /**
     * Check if a date is valid
     * 
     * @param date the date to check (format : YYYY-MM-DD)
     * @return true if the date is valid, false otherwise
     */
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

    /**
     * Check if a string is in a list of string
     * 
     * @param aChercher the string to check 
     * @param liste the list of string to check in 
     * @return true if the string is in the list, false otherwise 
     */
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

    /**
     * Constructor of Jour
     * 
     * @param date the date of the jour to create (format : YYYY-MM-DD)
     * @param temperatureMoyenne the average temperature
     * @param jour the day of the week
     * @param vacances the holidays (null if there is no holidays)
     */
    public Jour(String date, String jour, String vacances, double temperatureMoyenne) {
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

    /**
     * Set the day of the week with a string
     * 
     * @param jour the day of the week
     */
    public void setJour(String jour) {
        if (jour == null || jour.isEmpty()) {
            throw new IllegalArgumentException(
                    "models.Jour.setJour : Le parametre jour n'est pas valide (null ou vide)");
        }

        if (!Jour.containString(jour, Jour.VALIDEJOUR)) {
            throw new IllegalArgumentException("models.Jour.setJour : Ce jour n'existe pas ou est mal orthographié");
        }
        this.jour = jour;
    }
    
    /**
     * Set the day of the week with an int
     * 
     * @param jour the day of the week
     */
    public void setJour(int jour) {
        if (jour < 0 || jour > 6) {
            throw new IllegalArgumentException("models.Jour.setJour : Le parametre jour n'est pas valide (0-6))");
        }
        this.jour = Jour.VALIDEJOUR[jour];
    }

    /**
     * Set the holidays
     * 
     * @param vacances the holidays, null if there is no holidays
     */
    public void setVacances(String vacances) {
        if (vacances == null || vacances.isEmpty()) {
            this.vacances = null;
        } else {
            if (!Jour.containString(vacances, Jour.VALIDEVACANCES)) {
                throw new IllegalArgumentException("models.Jour.setVacances : Ces vacances n'existent pas ou sont mal orthographiées");
            }
            this.vacances = vacances;
        }
    }

    /**
     * Set the average temperature
     * 
     * The average temperature must be between -40 and 60
     * 
     * @param temperatureMoyenne the average temperature
     */
    public void setTemperatureMoyenne(double temperatureMoyenne) {
        if (temperatureMoyenne < -40 || temperatureMoyenne > 60) {
            throw new IllegalArgumentException(
                    "models.Jour.setTemperatureMoyenne : temperatureMoyenne abérante");
        }
        this.temperatureMoyenne = temperatureMoyenne;
    }

    // ----------------------------- getters -----------------------------

    /**
     * Get the date of the jour
     * 
     * @return the date of the jour (format : YYYY-MM-DD)
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Get the day of the week
     * 
     * @return the day of the week
     */
    public double getTemperatureMoyenne() {
        return this.temperatureMoyenne;
    }

    /**
     * Get the holidays
     * 
     * @return the holidays, null if there is no holidays
     */
    public String getJour() {
        return this.jour;
    }

    /**
     * Get the average temperature
     * 
     * @return the average temperature
     */
    public String getVacances() {
        return this.vacances;
    }
    
    // ----------------------------- methods -----------------------------

    /**
     * Get the day of the week
     * 
     * @return the day of the week
     */
    public boolean estWeekEnd(){
        boolean ret = false;
        if (this.jour.equals("Samedi") || this.jour.equals("Dimanche")){
            ret = true;
        }
        return ret;
    }

    /**
     * Check if the day is a holiday
     * 
     * @return true if the day is a holiday, false otherwise
     */
    public boolean estVacances() {
        boolean ret = false;
        if (this.vacances != null) {
            ret = true;
        }
        return ret;
    }
}
