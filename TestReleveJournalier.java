import models.ReleveJournalier;

import java.util.ArrayList;
import java.util.Arrays;

public class TestReleveJournalier {
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_OK = "\u001B[32m";
    private static final String COLOR_ERROR = "\u001B[31m";
    private static int nbTest = 0;
    private static int nbError = 0;

    public static void main(String[] args) {
        testConstructeur();
        testTypeAnomalieValide();
        testRelevesHeuresValide();
        testGetReleveJournalier();
        testGetRelevesByJour();
        testGetRelevesByCompteur();
        testRemoveReleveJournalier();
        testRemoveAllRelevesOfAJour();
        testRemoveAllRelevesOfACompteur();
        testGetPassageHoraire();
        testGetNbPassageTotal();
        testGetMoyennePassageByHour();
        
        System.out.println();
        if (nbError == 0) {
            printOk("Test réussi ! : " + nbTest + " / " + nbTest);
        } else {
            printError("Echec du test : " + (nbTest - nbError) + " / " + nbTest);
        }    
    }

    public static void testConstructeur() {
        // Test 1 : Création d'un relevé journalier valide
        int[] relevesHeures = new int[2];
        relevesHeures[0] = 1;
        relevesHeures[1] = 12;
        try {
            new ReleveJournalier(1, "2023-06-01", relevesHeures, "Faible");
            printOk("Test 1 réussi - Constructeur (compteur valide, jour valide)");
        } catch (IllegalArgumentException e) {
            printError("Test 1 échoué - Constructeur (compteur valide, jour valide)");
        }
    
        // Test 2 : Compteur invalide
        try {
            new ReleveJournalier(-1, "2023-06-02", relevesHeures, "Faible");
            printError("Test 2 échoué - Constructeur (compteur invalide, jour valide)");
        } catch (IllegalArgumentException e) {
            printOk("Test 2 réussi - Constructeur (compteur invalide, jour valide)");
        }
    
        // Test 3 : Jour invalide
        try {
            new ReleveJournalier(1, "202-06-31", relevesHeures, "Faible");
            printError("Test 3 échoué - Constructeur (compteur valide, jour invalide)");
        } catch (IllegalArgumentException e) {
            printOk("Test 3 réussi - Constructeur (compteur valide, jour invalide)");
        }
    }

    public static void testTypeAnomalieValide() {
        ReleveJournalier releve = new ReleveJournalier(1, "2023-06-01", new int[24], "Faible");
    
        // Test 1 : Type d'anomalie valide (Faible)
        try {
            releve.setPresenceAnomalie("Faible");
            printOk("Test 1 réussi - Type d'anomalie valide (Faible)");
        } catch (IllegalArgumentException e) {
            printError("Test 1 échoué - Type d'anomalie valide (Faible)");
        }
    
        // Test 2 : Type d'anomalie valide (Élevée)
        try {
            releve.setPresenceAnomalie("Élevée");
            printOk("Test 2 réussi - Type d'anomalie valide (Élevée)");
        } catch (IllegalArgumentException e) {
            printError("Test 2 échoué - Type d'anomalie valide (Élevée)");
        }
    
        // Test 3 : Type d'anomalie invalide
        try {
            releve.setPresenceAnomalie("Invalide");
            printError("Test 3 échoué - Type d'anomalie invalide");
        } catch (IllegalArgumentException e) {
            printOk("Test 3 réussi - Type d'anomalie invalide");
        }
    }

    public static void testRelevesHeuresValide() {
        ReleveJournalier releve = new ReleveJournalier(1, "2023-06-01", new int[24], "Faible");
    
        // Test 1 : Relevés d'heures valides
        int[] relevesHeures = new int[24];
        Arrays.fill(relevesHeures, 1);
        try {
            releve.setRelevesHeures(relevesHeures);
            printOk("Test 1 réussi - Relevés d'heures valides");
        } catch (IllegalArgumentException e) {
            printError("Test 1 échoué - Relevés d'heures valides");
        }
    
        // Test 2 : Relevés d'heures invalides
        int[] relevesHeuresInvalides = new int[24];
        try {
            releve.setRelevesHeures(relevesHeuresInvalides);
            printError("Test 2 échoué - Relevés d'heures invalides");
        } catch (IllegalArgumentException e) {
            printOk("Test 2 réussi - Relevés d'heures invalides");
        }
    }

    private static void testGetReleveJournalier() {
        // Test 1 : Récupération du relevé journalier
        ReleveJournalier releve = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier jour = releve.getReleveJournalier("2023-06-01", 1);
        if (jour.equals("2023-06-01")) {
            printOk("Test 1 réussi - Récupération du relevé journalier");
        } else {
            printError("Test 1 échoué - Récupération du relevé journalier");
        }
    }
    

    private static void testGetRelevesByJour() {
        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-06-02", new int[]{5, 18}, "Faible");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-06-03", new int[]{9, 20}, "Faible");
    
        // Création d'une liste de relevés
        ArrayList<ReleveJournalier> listeReleves = new ArrayList<>();
        listeReleves.add(releve1);
        listeReleves.add(releve2);
        listeReleves.add(releve3);
    
        // Test 1 : Récupération des relevés par jour valide
        ArrayList<ReleveJournalier> relevesByJour1 = ReleveJournalier.getRelevesByJour("2023-06-01");
        ArrayList<ReleveJournalier> relevesByJour2 = ReleveJournalier.getRelevesByJour("2023-06-02");
        ArrayList<ReleveJournalier> relevesByJour3 = ReleveJournalier.getRelevesByJour("2023-06-03");
        if (relevesByJour1.size() == 2 && relevesByJour2.contains(releve2) && relevesByJour3.contains(releve3)) {
            printOk("Test 1 réussi - Récupération des relevés par jour valide");
        } else {
            printError("Test 1 échoué - Récupération des relevés par jour valide");
        }
    
        // Test 2 : Récupération des relevés par jour invalide
        ArrayList<ReleveJournalier> relevesByJour4 = ReleveJournalier.getRelevesByJour("2023-06-04");
        if (relevesByJour4.isEmpty()) {
            printOk("Test 2 réussi - Récupération des relevés par jour invalide");
        } else {
            printError("Test 2 échoué - Récupération des relevés par jour invalide");
        }
    }
    
    private static void testGetRelevesByCompteur() {
        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-06-02", new int[]{5, 18}, "Faible");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-06-02", new int[]{9, 20}, "Faible");
    
        // Création d'une liste de relevés
        ArrayList<ReleveJournalier> listeReleves = new ArrayList<>();
        listeReleves.add(releve1);
        listeReleves.add(releve2);
        listeReleves.add(releve3);
    
        // Test 1 : Récupération des relevés par compteur valide
        ArrayList<ReleveJournalier> relevesByCompteur = ReleveJournalier.getRelevesByCompteur(listeReleves, 1);
        if (relevesByCompteur.size() == 2 && relevesByCompteur.contains(releve1) && relevesByCompteur.contains(releve2)) {
            printOk("Test 1 réussi - Récupération des relevés par compteur valide");
        } else {
            printError("Test 1 échoué - Récupération des relevés par compteur valide");
        }
    
        // Test 2 : Récupération des relevés par compteur invalide
        relevesByCompteur = ReleveJournalier.getRelevesByCompteur(listeReleves, 3);
        if (relevesByCompteur.isEmpty()) {
            printOk("Test 2 réussi - Récupération des relevés par compteur invalide");
        } else {
            printError("Test 2 échoué - Récupération des relevés par compteur invalide");
        }
    }
    
    private static void testRemoveReleveJournalier() {
        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-06-02", new int[]{5, 18}, "Faible");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-06-02", new int[]{9, 20}, "Faible");
    
        // Création d'une liste de relevés
        ArrayList<ReleveJournalier> listeReleves = new ArrayList<>();
        listeReleves.add(releve1);
        listeReleves.add(releve2);
        listeReleves.add(releve3);
    
        // Test 1 : Suppression d'un relevé journalier existant
        ReleveJournalier releveToRemove = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        boolean result = ReleveJournalier.removeReleveJournalier(listeReleves, releveToRemove);
        if (result && listeReleves.size() == 2 && !listeReleves.contains(releveToRemove)) {
            printOk("Test 1 réussi - Suppression d'un relevé journalier existant");
        } else {
            printError("Test 1 échoué - Suppression d'un relevé journalier existant");
        }
    
        // Test 2 : Suppression d'un relevé journalier inexistant
        ReleveJournalier nonExistingReleve = new ReleveJournalier(2, "2023-06-01", new int[]{1, 12}, "Faible");
        result = ReleveJournalier.removeReleveJournalier(listeReleves, nonExistingReleve);
        if (!result && listeReleves.size() == 2) {
            printOk("Test 2 réussi - Suppression d'un relevé journalier inexistant");
        } else {
            printError("Test 2 échoué - Suppression d'un relevé journalier inexistant");
        }
    }
    
    
    private static void testRemoveAllRelevesOfAJour() {
        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-06-02", new int[]{5, 18}, "Moyenne");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-06-02", new int[]{9, 20}, "Faible");
    
        // Création d'une liste de relevés
        ArrayList<ReleveJournalier> listeReleves = new ArrayList<>();
        listeReleves.add(releve1);
        listeReleves.add(releve2);
        listeReleves.add(releve3);
    
        // Test 1 : Suppression de tous les relevés d'un jour existant
        boolean result = ReleveJournalier.removeAllRelevesOfAJour(listeReleves, "2023-06-02");
        if (result && listeReleves.size() == 1) {
            printOk("Test 1 réussi - Suppression de tous les relevés d'un jour existant");
        } else {
            printError("Test 1 échoué - Suppression de tous les relevés d'un jour existant");
        }
    
        // Test 2 : Suppression de tous les relevés d'un jour inexistant
        result = ReleveJournalier.removeAllRelevesOfAJour(listeReleves, "2023-06-03");
        if (!result && listeReleves.size() == 1) {
            printOk("Test 2 réussi - Suppression de tous les relevés d'un jour inexistant");
        } else {
            printError("Test 2 échoué - Suppression de tous les relevés d'un jour inexistant");
        }
    }
    
    private static void testRemoveAllRelevesOfACompteur() {
        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-06-02", new int[]{5, 18}, "Moyenne");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-06-02", new int[]{9, 20}, "Faible");
    
        // Création d'une liste de relevés
        ArrayList<ReleveJournalier> listeReleves = new ArrayList<>();
        listeReleves.add(releve1);
        listeReleves.add(releve2);
        listeReleves.add(releve3);
    
        // Test 1 : Suppression de tous les relevés d'un compteur existant
        boolean result = ReleveJournalier.removeAllRelevesOfACompteur(listeReleves, 1);
        if (result && listeReleves.size() == 1) {
            printOk("Test 1 réussi - Suppression de tous les relevés d'un compteur existant");
        } else {
            printError("Test 1 échoué - Suppression de tous les relevés d'un compteur existant");
        }
    
        // Test 2 : Suppression de tous les relevés d'un compteur inexistant
        result = ReleveJournalier.removeAllRelevesOfACompteur(listeReleves, 3);
        if (!result && listeReleves.size() == 1) {
            printOk("Test 2 réussi - Suppression de tous les relevés d'un compteur inexistant");
        } else {
            printError("Test 2 échoué - Suppression de tous les relevés d'un compteur inexistant");
        }
    }
    
    private static void testGetPassageHoraire() {
        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-06-02", new int[]{5, 18}, "Moyenne");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-06-02", new int[]{9, 20}, "Faible");
    
        // Création d'une liste de relevés
        ArrayList<ReleveJournalier> listeReleves = new ArrayList<>();
        listeReleves.add(releve1);
        listeReleves.add(releve2);
        listeReleves.add(releve3);
    
        // Test 1 : Récupération du passage horaire d'un relevé existant
        int passageHoraire = ReleveJournalier.getPassageHoraire(listeReleves, 1, "2023-06-01");
        if (passageHoraire == 2) {
            printOk("Test 1 réussi - Récupération du passage horaire d'un relevé existant");
        } else {
            printError("Test 1 échoué - Récupération du passage horaire d'un relevé existant");
        }
    
        // Test 2 : Récupération du passage horaire d'un relevé inexistant
        passageHoraire = ReleveJournalier.getPassageHoraire(listeReleves, 2, "2023-06-03");
        if (passageHoraire == 0) {
            printOk("Test 2 réussi - Récupération du passage horaire d'un relevé inexistant");
        } else {
            printError("Test 2 échoué - Récupération du passage horaire d'un relevé inexistant");
        }
    }
    
    
    private static void testGetNbPassageTotal() {
        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-06-02", new int[]{5, 18}, "Moyenne");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-06-02", new int[]{9, 20}, "Faible");
    
        // Création d'une liste de relevés
        ArrayList<ReleveJournalier> listeReleves = new ArrayList<>();
        listeReleves.add(releve1);
        listeReleves.add(releve2);
        listeReleves.add(releve3);
    
        // Test : Récupération du nombre total de passages
        int nbPassagesTotal = ReleveJournalier.getNbPassageTotal(listeReleves);
        if (nbPassagesTotal == 4) {
            printOk("Test réussi - Récupération du nombre total de passages");
        } else {
            printError("Test échoué - Récupération du nombre total de passages");
        }
    }
    
    private static void testGetMoyennePassageByHour() {
        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-06-01", new int[]{1, 12}, "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-06-02", new int[]{5, 18}, "Moyenne");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-06-02", new int[]{9, 20}, "Faible");
    
        // Création d'une liste de relevés
        ArrayList<ReleveJournalier> listeReleves = new ArrayList<>();
        listeReleves.add(releve1);
        listeReleves.add(releve2);
        listeReleves.add(releve3);
    
        // Test : Récupération de la moyenne de passage par heure
        double moyennePassage = ReleveJournalier.getMoyennePassageByHour(listeReleves);
        if (moyennePassage == 1.3333333333333333) {
            printOk("Test réussi - Récupération de la moyenne de passage par heure");
        } else {
            printError("Test échoué - Récupération de la moyenne de passage par heure");
        }
    }
    
    

    private static void printOk(String message) {
        System.out.println(COLOR_OK + message + COLOR_RESET);
        nbTest++;
    }

    private static void printError(String message) {
        System.out.println(COLOR_ERROR + message + COLOR_RESET);
        nbTest++;
        nbError++;
    }
}
