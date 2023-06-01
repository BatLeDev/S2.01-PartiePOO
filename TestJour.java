import models.Jour;

public class TestJour {
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_OK = "\u001B[32m";
    private static final String COLOR_ERROR = "\u001B[31m";
    private static int nbTest = 0;
    private static int nbError = 0;

    public static void main(String[] args) {
        System.out.println("------ TestJour ------");
        testConstructor();
        testDeleteJour();
        testSetJourString();
        testSetJourInt();
        testSetTemperatureMoyenne();
        testEstWeekend();
        testEstVacances();

        System.out.println();
        if (nbError == 0) {
            printOk("Test réussi ! : " + nbTest + " / " + nbTest);
        } else {
            printError("Echec du test : " + (nbTest - nbError) + " / " + nbTest);
        }    
    }

    private static void testConstructor() {
        System.out.println("\nTest du constructeur");
        try {
            new Jour("2023-05-30", "Lundi", null, 25.5);
            printOk("Constructor test passed");
        } catch (IllegalArgumentException e) {
            printError("Constructor test failed: " + e.getMessage());
        }
    
        // Invalid date format
        try {
            new Jour("2023/05/30", "Lundi", null, 25.5);
            printError("Constructor test failed: Invalid date format");
        } catch (IllegalArgumentException e) {
            printOk("Constructor test passed: Invalid date format");
        }
    
        // Existing date
        try {
            new Jour("2023-05-30", "Mardi", null, 24.8);
            new Jour("2023-05-30", "Mercredi", null, 23.7);
            printError("Constructor test failed: Existing date");
        } catch (IllegalArgumentException e) {
            printOk("Constructor test passed: Existing date");
        }
    }

    private static void testDeleteJour() {
        System.out.println("\nTest de deleteJour");
        // Valid case
        try {
            new Jour("2023-05-31", "Lundi", null, 25.5);
            Jour.deleteJour("2023-05-31");
            Jour deletedJour = Jour.getJour("2023-05-31");
            if (deletedJour == null) {
                printOk("deleteJour test passed");
            } else {
                printError("deleteJour test failed: Jour still exists");
            }
        } catch (IllegalArgumentException e) {
            printError("deleteJour test failed: " + e.getMessage());
        }
    }

    private static void testSetJourString() {
        System.out.println("\nTest de setJour(String)");
        Jour jour = new Jour("2023-06-01", "Lundi", null, 25.5);

        // Valid case
        try {
            jour.setJour("Mardi");
            if (jour.getJour().equals("Mardi")) {
                printOk("setJour(String) test passed");
            } else {
                printError("setJour(String) test failed: Incorrect day");
            }
        } catch (IllegalArgumentException e) {
            printError("setJour(String) test failed: " + e.getMessage());
        }

        // Invalid day
        try {
            jour.setJour("Sundae");
            printError("setJour(String) test failed: Invalid day");
        } catch (IllegalArgumentException e) {
            printOk("setJour(String) test passed: Invalid day");
        }
    }

    private static void testSetJourInt() {
        System.out.println("\nTest de setJour(int)");
        Jour jour = new Jour("2023-06-02", "Lundi", null, 25.5);

        // Valid case
        try {
            jour.setJour(2);
            if (jour.getJour().equals("Mercredi")) {
                printOk("setJour(int) test passed");
            } else {
                printError("setJour(int) test failed: Incorrect day");
            }
        } catch (IllegalArgumentException e) {
            printError("setJour(int) test failed: " + e.getMessage());
        }

        // Invalid day
        try {
            jour.setJour(8);
            printError("setJour(int) test failed: Invalid day");
        } catch (IllegalArgumentException e) {
            printOk("setJour(int) test passed: Invalid day");
        }
    }

    private static void testSetTemperatureMoyenne() {
        System.out.println("\nTest de setTemperatureMoyenne");
        Jour jour = new Jour("2023-06-03", "Lundi", null, 25.5);

        // Valid case
        try {
            jour.setTemperatureMoyenne(28.9);
            if (jour.getTemperatureMoyenne() == 28.9) {
                printOk("setTemperatureMoyenne test passed");
            } else {
                printError("setTemperatureMoyenne test failed: Incorrect average temperature");
            }
        } catch (IllegalArgumentException e) {
            printError("setTemperatureMoyenne test failed: " + e.getMessage());
        }

        // Aberrant temperature
        try {
            jour.setTemperatureMoyenne(80.0);
            printError("setTemperatureMoyenne test failed: Aberrant temperature");
        } catch (IllegalArgumentException e) {
            printOk("setTemperatureMoyenne test passed: Aberrant temperature");
        }
    }

    private static void testEstWeekend() {
        System.out.println("\nTest de estWeekend");
        Jour jour1 = new Jour("2023-06-04", "Lundi", null, 25.5);
        Jour jour2 = new Jour("2023-06-05", "Mardi", null, 26.3);
        Jour jour3 = new Jour("2023-06-06", "Samedi", null, 24.9);

        // Valid case
        if (jour1.estWeekEnd() == false && jour2.estWeekEnd() == false && jour3.estWeekEnd() == true) {
            printOk("estWeekend test passed");
        } else {
            printError("estWeekend test failed: Incorrect weekend detection");
        }
    }

    private static void testEstVacances() {
        System.out.println("\nTest de estVacances");
        Jour jour1 = new Jour("2023-06-07", "Lundi", null, 25.5);
        Jour jour2 = new Jour("2023-06-08", "Mardi", "Ete", 26.3);
        Jour jour3 = new Jour("2023-06-09", "Samedi", "Printemps", 24.9);

        // Valid case
        if (jour1.estVacances() == false && jour2.estVacances() == true && jour3.estVacances() == true) {
            printOk("estVacances test passed");
        } else {
            printError("estVacances test failed: Incorrect vacation detection");
        }
    }

    // Affichages
    private static void printOk(String message) {
        System.out.println(COLOR_OK + "OK : " + message + COLOR_RESET);
        nbTest++;
    }

    private static void printError(String message) {
        System.out.println(COLOR_ERROR + "ERROR : " + message + COLOR_RESET);
        nbTest++;
        nbError++;
    }
}
