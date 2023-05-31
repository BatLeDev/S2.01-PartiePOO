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
            printOk("OK : Constructor test passed");
        } catch (IllegalArgumentException e) {
            printError("ECHEC : Constructor test failed: " + e.getMessage());
        }
    }

    private static void testDeleteJour() {
        System.out.println("\nTest de deleteJour");
        new Jour("2023-06-15", "Jeudi", null, 21.5);
        Jour.deleteJour("2023-06-15");

        if (Jour.getJour("2023-06-15") == null) {
            printOk("OK : deleteJour test passed");
        } else {
            printError("ECHEC : deleteJour test failed");
        }
    }

    private static void testSetJourString() {
        System.out.println("\nTest de setJour(String)");
        Jour jour3 = new Jour("2023-04-25", "Lundi", null, 25.5);
        jour3.setJour("Mardi");

        if (jour3.getJour().equals("Mardi")) {
            printOk("OK: setJour(String) test passed");
        } else {
            printError("ECHEC : setJour(String) test failed");
        }
    }

    private static void testSetJourInt() {
        System.out.println("\nTest de setJour(int)");
        Jour jour4 = new Jour("2023-05-14", "Lundi", null, 25.5);
        jour4.setJour(2);

        if (jour4.getJour().equals("Mercredi")) {
            printOk("OK : setJour(int) test passed");
        } else {
            printError("ECHEC : setJour(int) test failed");
        }
    }

    private static void testSetTemperatureMoyenne() {
        System.out.println("\nTest de setTemperatureMoyenne");
        Jour jour5 = new Jour("2023-05-02", "Lundi", null, 25.5);
        jour5.setTemperatureMoyenne(20.3);

        if (jour5.getTemperatureMoyenne() == 20.3) {
            printOk("OK : setTemperatureMoyenne test passed");
        } else {
            printError("ECHEC : setTemperatureMoyenne test failed");
        }
    }

    private static void testEstWeekend() {
        System.out.println("\nTest de estWeekend");
        Jour jour6 = new Jour("2023-11-01", "Lundi", null, 25.5);
        Jour jour7 = new Jour("2023-11-02", "Mardi", null, 24.8);
        Jour jour8 = new Jour("2023-11-03", "Samedi", null, 23.7);

        if (!jour6.estWeekEnd() && !jour7.estWeekEnd() && jour8.estWeekEnd()) {
            printOk("OK : estWeekend test passed");
        } else {
            printError("ECHEC : estWeekend test failed");
        }
    }

    private static void testEstVacances() {
        System.out.println("\nTest de estVacances");
        Jour jour9 = new Jour("2023-12-01", "Lundi", null, 25.5);
        Jour jour10 = new Jour("2023-12-02", "Mardi", "Noel", 24.8);
        Jour jour11 = new Jour("2023-12-03", "Samedi", "Ete", 23.7);

        if (!jour9.estVacances() && jour10.estVacances() && jour11.estVacances()) {
            printOk("OK : estVacances test passed");
        } else {
            printError("ECHEC : estVacances test failed");
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
