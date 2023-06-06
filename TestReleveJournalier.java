import models.Compteur;
import models.Jour;
import models.ReleveJournalier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/////////////////// This class test all methods in a random order ///////////////////

public class TestReleveJournalier {
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_OK = "\u001B[32m";
    private static final String COLOR_ERROR = "\u001B[31m";
    private static int nbTest = 0;
    private static int nbError = 0;

    public static void main(String[] args) {
        // Initialisation pour l'ensemble des tests
        new Compteur(1, "Bonduelle", "nord", 49.000, 51.000);
        new Compteur(2, "Bonduelle", "sud", 49.000, 51.000);
        new Compteur(3, "Bonduelle", "est", 49.000, 51.000);
        new Compteur(4, "Bonduelle", "ouest", 49.000, 51.000);

        // Créer une liste des noms des méthodes de test
        ArrayList<String> testMethods = new ArrayList<>();
        testMethods.add("testConstructeur");
        testMethods.add("testSetPresenceAnomalie");
        testMethods.add("testSetRelevesHeures");
        testMethods.add("testGetReleveJournalier");
        testMethods.add("testGetRelevesByJour");
        testMethods.add("testGetRelevesByCompteur");
        testMethods.add("testRemoveReleveJournalier");
        testMethods.add("testRemoveAllRelevesOfAJour");
        testMethods.add("testRemoveAllRelevesOfACompteur");
        testMethods.add("testGetPassageHoraire");
        testMethods.add("testGetNbPassageTotal");
        testMethods.add("testGetMoyennePassageByHour");

        // Mélanger l'ordre des méthodes
        Collections.shuffle(testMethods);

        // Exécuter les méthodes de test dans l'ordre aléatoire
        for (String methodName : testMethods) {
            try {
                Class<?> testClass = TestReleveJournalier.class;
                testClass.getMethod(methodName).invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Afficher le résultat des tests
        System.out.println();
        if (nbError == 0) {
            printOk("Test réussi ! : " + nbTest + " / " + nbTest);
        } else {
            printError("Echec du test : " + (nbTest - nbError) + " / " + nbTest);
        }
    }

    public static void testConstructeur() {
        System.out.println("-- Test du constructeur --");

        new Jour("2023-06-01", "Lundi", null, 20.0);
        new Jour("2023-06-02", "Mardi", null, 20.0);

        // Test 1 : Création d'un relevé journalier valide
        int[] relevesHeures = new int[24];
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
            new ReleveJournalier(1, "2023-06-03", relevesHeures, "Faible");
            printError("Test 3 échoué - Constructeur (compteur valide, jour invalide)");
        } catch (IllegalArgumentException e) {
            printOk("Test 3 réussi - Constructeur (compteur valide, jour invalide) : " + e.getMessage());
        }
    }

    public static void testSetPresenceAnomalie() {
        System.out.println("-- Test de setPresenceAnomalie --");

        new Jour("2023-07-01", "Lundi", null, 20.0);
        ReleveJournalier releve = new ReleveJournalier(1, "2023-07-01", new int[24], "Faible");
    
        // Test 1 : Type d'anomalie valide (Faible)
        try {
            releve.setPresenceAnomalie("Faible");
            printOk("Test 1 réussi - Type d'anomalie valide (Faible)");
        } catch (IllegalArgumentException e) {
            printError("Test 1 échoué - Type d'anomalie valide (Faible)");
        }
    
        // Test 2 : Type d'anomalie valide (Forte)
        try {
            releve.setPresenceAnomalie("Forte");
            printOk("Test 2 réussi - Type d'anomalie valide (Forte)");
        } catch (IllegalArgumentException e) {
            printError("Test 2 échoué - Type d'anomalie valide (Forte)");
        }
    
        // Test 3 : Type d'anomalie invalide
        try {
            releve.setPresenceAnomalie("Invalide");
            printError("Test 3 échoué - Type d'anomalie invalide");
        } catch (IllegalArgumentException e) {
            printOk("Test 3 réussi - Type d'anomalie invalide");
        }
    }

    public static void testSetRelevesHeures() {
        System.out.println("-- Test de setRelevesHeures --");

        new Jour("2023-08-01", "Lundi", null, 20.0);
        ReleveJournalier releve = new ReleveJournalier(1, "2023-08-01", new int[24], "Faible");
    
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
        relevesHeuresInvalides[0] = -1;
        try {
            releve.setRelevesHeures(relevesHeuresInvalides);
            printError("Test 2 échoué - Relevés d'heures invalides");
        } catch (IllegalArgumentException e) {
            printOk("Test 2 réussi - Relevés d'heures invalides");
        }
    }

    public static void testGetReleveJournalier() {
        System.out.println("-- Test de getReleveJournalier --");

        new Jour("2023-09-01", "Lundi", null, 20.0);

        // Test 1 : Récupération du relevé journalier
        ReleveJournalier releve = new ReleveJournalier(1, "2023-09-01", new int[24], "Faible");
        ReleveJournalier res = ReleveJournalier.getReleveJournalier("2023-09-01", 1);
        if (res == releve) {
            printOk("Test 1 réussi - Récupération du relevé journalier");
        } else {
            printError("Test 1 échoué - Récupération du relevé journalier");
        }
    }
    
    public static void testGetRelevesByJour() {
        System.out.println("-- Test de getRelevesByJour --");

        // Création de quelques jours
        new Jour("2023-10-01", "Lundi", null, 20.0);
        new Jour("2023-10-02", "Mardi", null, 20.0);
        new Jour("2023-10-03", "Mercredi", null, 20.0);

        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-10-01", new int[24], "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(2, "2023-10-01", new int[24], "Faible");
        ReleveJournalier releve3 = new ReleveJournalier(2, "2023-10-02", new int[24], "Faible");
    
        // Test 1 : Récupération des relevés par jour valide
        ArrayList<ReleveJournalier> relevesByJour1 = ReleveJournalier.getRelevesByJour("2023-10-01");
        ArrayList<ReleveJournalier> relevesByJour2 = ReleveJournalier.getRelevesByJour("2023-10-02");
        ArrayList<ReleveJournalier> relevesByJour3 = ReleveJournalier.getRelevesByJour("2023-10-03");

        if (relevesByJour1.size() == 2 && relevesByJour2.contains(releve3) && relevesByJour3 == null) {
            printOk("Test 1 réussi - Récupération des relevés par jour valide");
        } else {
            printError("Test 1 échoué - Récupération des relevés par jour valide");
        }
    
        // Test 2 : Récupération des relevés par jour invalide
        ArrayList<ReleveJournalier> relevesByJour4 = ReleveJournalier.getRelevesByJour("");
        if (relevesByJour4 == null) {
            printOk("Test 2 réussi - Récupération des relevés par jour invalide");
        } else {
            printError("Test 2 échoué - Récupération des relevés par jour invalide");
        }
    }
    
    public static void testGetRelevesByCompteur() {
        System.out.println("-- Test de getRelevesByCompteur --");

        // Création de quelques jours
        new Jour("2023-11-01", "Lundi", null, 20.0);
        new Jour("2023-11-02", "Mardi", null, 20.0);

        // Création d'un compteur utilisé uniquement ici
        new Compteur(10, "Bonduelle", "nord", 49.000, 51.000);

        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(10, "2023-11-01", new int[24], "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(10, "2023-11-02", new int[24], "Faible");
        
        // Test 1 : Récupération des relevés par compteur valide
        ArrayList<ReleveJournalier> relevesByCompteur = ReleveJournalier.getRelevesByCompteur(10);
        if (relevesByCompteur.size() == 2 && relevesByCompteur.contains(releve1) && relevesByCompteur.contains(releve2)) {
            printOk("Test 1 réussi - Récupération des relevés par compteur valide");
        } else {
            printError("Test 1 échoué - Récupération des relevés par compteur valide");
        }
    
        // Test 2 : Récupération des relevés par compteur inexistant
        relevesByCompteur = ReleveJournalier.getRelevesByCompteur(45);
        if (relevesByCompteur == null) {
            printOk("Test 2 réussi - Récupération des relevés par compteur inexistant");
        } else {
            printError("Test 2 échoué - Récupération des relevés par compteur inexistant");
        }
    }
    
    public static void testRemoveReleveJournalier() {
        System.out.println("-- Test de removeReleveJournalier --");

        new Jour("2023-12-01", "Lundi", null, 20.0);
        new Jour("2023-12-02", "Mardi", null, 20.0);
        new Jour("2023-12-03", "Mercredi", null, 20.0);

        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2023-12-01", new int[24], "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2023-12-02", new int[24], "Faible");
    
        // Test 1 : Suppression d'un relevé journalier existant
        ReleveJournalier result = ReleveJournalier.removeReleveJournalier(releve2.getLeJour(), releve2.getLeCompteur());
        if (result != null && ReleveJournalier.getReleveJournalier(releve2.getLeJour(), releve2.getLeCompteur()) == null) {
            printOk("Test 1 réussi - Suppression d'un relevé journalier existant");
        } else {
            printError("Test 1 échoué - Suppression d'un relevé journalier existant");
        }
    
        // Test 2 : Suppression d'un relevé journalier inexistant
        result = ReleveJournalier.removeReleveJournalier("2023-12-03", 3);
        if (result == null) {
            printOk("Test 2 réussi - Suppression d'un relevé journalier inexistant");
        } else {
            printError("Test 2 échoué - Suppression d'un relevé journalier inexistant");
        }
    }
    
    public static void testRemoveAllRelevesOfAJour() {
        System.out.println("-- Test de removeAllRelevesOfAJour --");

        // Création de quelques jours
        new Jour("2024-01-01", "Lundi", null, 20.0);

        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2024-01-01", new int[24], "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(2, "2024-01-01", new int[24], "Forte");

        // Test 1 : Suppression de tous les relevés d'un jour existant
        ArrayList<ReleveJournalier> listeReleves = ReleveJournalier.removeAllRelevesOfAJour("2024-01-01");
        if (listeReleves.size() == 2 && ReleveJournalier.getReleveJournalier("2024-01-01", 1) == null && ReleveJournalier.getReleveJournalier("2024-01-01", 2) == null) {
            printOk("Test 1 réussi - Suppression de tous les relevés d'un jour existant");
        } else {
            printError("Test 1 échoué - Suppression de tous les relevés d'un jour existant");
        }

        // Test 2 : Suppression de tous les relevés d'un jour sans relevé
        listeReleves = ReleveJournalier.removeAllRelevesOfAJour("2024-01-03");
        if (listeReleves == null) {
            printOk("Test 2 réussi - Suppression de tous les relevés d'un jour sans relevé");
        } else {
            printError("Test 2 échoué - Suppression de tous les relevés d'un jour sans relevé");
        }
    }

    public static void testRemoveAllRelevesOfACompteur() {
        System.out.println("-- Test de removeAllRelevesOfACompteur --");

        // Création de quelques jours
        new Jour("2024-03-01", "Lundi", null, 20.0);
        new Jour("2024-03-02", "Mardi", null, 20.0);

        // Création de quelques relevés journaliers
        ReleveJournalier releve1 = new ReleveJournalier(1, "2024-03-01", new int[24], "Faible");
        ReleveJournalier releve2 = new ReleveJournalier(1, "2024-03-02", new int[24], "Forte");

        // Création d'un compteur utilisé uniquement ici
        new Compteur(20, "Bonduelle", "nord", 49.000, 51.000);

        // Test 1 : Suppression de tous les relevés d'un compteur existant
        ArrayList<ReleveJournalier> listeReleves = ReleveJournalier.removeAllRelevesOfACompteur(1);
        if (ReleveJournalier.getReleveJournalier("2024-03-01", 1) == null && ReleveJournalier.getReleveJournalier("2024-02-02", 1) == null) {
            printOk("Test 1 réussi - Suppression de tous les relevés d'un compteur existant");
        } else {
            printError("Test 1 échoué - Suppression de tous les relevés d'un compteur existant");
        }

        // Test 2 : Suppression de tous les relevés d'un compteur sans relevé
        listeReleves = ReleveJournalier.removeAllRelevesOfACompteur(20);
        if (listeReleves == null) {
            printOk("Test 2 réussi - Suppression de tous les relevés d'un compteur sans relevé");
        } else {
            printError("Test 2 échoué - Suppression de tous les relevés d'un compteur sans relevé");
        }
    }

    public static void testGetPassageHoraire() {
        System.out.println("-- Test de getPassageHoraire --");

        // Création d'un jour
        new Jour("2024-04-01", "Lundi", null, 20.0);

        // Création d'un relevé journalier
        int[] relevesHeures = new int[24];
        relevesHeures[23] = 404;
        ReleveJournalier releve1 = new ReleveJournalier(1, "2024-04-01", relevesHeures, "Faible");

        // Test 1: Récupération du nombre de passages par heure d'une heure valide
        int nbPassage = releve1.getPassageHoraire(23);
        if (nbPassage == 404) {
            printOk("Test 1 réussi - Récupération du nombre de passages par heure d'une heure valide");
        } else {
            printError("Test 1 échoué - Récupération du nombre de passages par heure d'une heure valide");
        }

        // Test 2: Récupération du nombre de passages par heure d'une heure invalide
        try {
            nbPassage = releve1.getPassageHoraire(24);
            printError("Test 2 échoué - Récupération du nombre de passages par heure d'une heure invalide");
        } catch (IllegalArgumentException e) {
            printOk("Test 2 réussi - Récupération du nombre de passages par heure d'une heure invalide");
        }

    }

    public static void testGetNbPassageTotal() {
        System.out.println("-- Test de getNbPassageTotal --");

        // Création de quelques relevés journaliers
        new Jour("2024-05-01", "Lundi", null, 20.0);
        int[] relevesHeures1 = new int[24];
        relevesHeures1[0] = 10;
        relevesHeures1[23] = 20;
        ReleveJournalier releve1 = new ReleveJournalier(1, "2024-05-01", relevesHeures1, "Faible");
        
        // Test : Récupération du nombre total de passages
        int nbPassagesTotal = releve1.getNbPassageTotal();
        if (nbPassagesTotal == 30) {
            printOk("Test réussi - Récupération du nombre total de passages");
        } else {
            printError("Test échoué - Récupération du nombre total de passages");
        }
    }
    
    public static void testGetMoyennePassageByHour() {
        System.out.println("-- Test de getMoyennePassageByHour --");

        // Création d'un relevé journalier
        new Jour("2024-05-02", "Lundi", null, 20.0);
        int[] relevesHeures1 = new int[24];
        relevesHeures1[0] = 10;
        relevesHeures1[7] = 15;
        relevesHeures1[23] = 20;
        ReleveJournalier releve1 = new ReleveJournalier(1, "2024-05-02", relevesHeures1, "Faible");
        
        // Test 1: Récupération de la moyenne de passage par heure
        double nbPassagesTotal = releve1.getMoyennePassageByHour();
        if (nbPassagesTotal == 1.875) {
            printOk("Test réussi - Récupération du nombre total de passages");
        } else {
            printError("Test échoué - Récupération du nombre total de passages : " + nbPassagesTotal + " au lieu de 1.875");
        }

        // Test 2: Récupération de la moyenne de passage par heure avec que des relevés à 0
        releve1 = new ReleveJournalier(2, "2024-05-02", new int[24], "Faible");
        nbPassagesTotal = releve1.getMoyennePassageByHour();
        if (nbPassagesTotal == 0d) {
            printOk("Test réussi - Récupération du nombre total de passages avec que des relevés à 0");
        } else {
            printError("Test échoué - Récupération du nombre total de passages avec que des relevés à 0 : " + nbPassagesTotal + " au lieu de 0");
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
