import models.Compteur;
import models.Quartier;

public class TestCompteur {
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_ERROR = "\u001B[31m";
    public static final String COLOR_OK = "\u001B[32m";

    public static int nbTest = 0;
    public static int nbError = 0;

    public static void main(String[] args) {
        System.out.println("------ TestCompteur ------");
        testConstructor();
        testSetQuartier();
        testToCSV();

        System.out.println();
        if (nbError == 0) {
            printOk("Test réussi ! : " + nbTest + " / " + nbTest);
        } else {
            printError("Echec du test : " + (nbTest - nbError) + " / " + nbTest);
        }
    }

    public static void testConstructor() {
        System.out.println("\nTest du constructeur sans quartier");
        try {
            new Compteur(1, "Bonduelle", "Nord", 49.7, -1.40);

            String expected = "Bonduelle vers Nord#1 : quartier = null, latitude = 49.7, longitude = -1.4";
            String actual = Compteur.getCompteur(1).toString();

            if (!expected.equals(actual)) {
                printError("\tECHEC : cas normal (peut venir de toString)");
                printError("\t\tExpected : " + expected);
                printError("\t\tActual : " + actual);
                nbError -= 2;
                nbTest -= 2;
            } else {
                printOk("\tOK : cas normal");
            }

        } catch (IllegalArgumentException e) {
            printError("\tECHEC : cas normal : " + e.getMessage());
        }

        try {
            new Compteur(-1, "Bonduelle", "Nord", 49.7, -1.40);
            printError("\tECHEC : idCompteur négatif");
        } catch (IllegalArgumentException e) {
            printOk("\tOK : idCompteur négatif");
        }

        try {
            new Compteur(2, "", "Nord", 49.7, -1.40);
            printError("\tECHEC : nom vide");
        } catch (IllegalArgumentException e) {
            printOk("\tOK : nom vide");
        }

        System.out.println("\nTest du constructeur avec quartier");
        Quartier q = new Quartier(1, "Centre ville", 1000);
        try {
            new Compteur(3, "Bonduelle", "Nord", 49.7, -1.40, q);
            printOk("\tOK : cas normal");
        } catch (IllegalArgumentException e) {
            printError("\tECHEC : cas normal : " + e.getMessage());
        }

        try {
            new Compteur(4, "Bonduelle", "Nord", 49.7, -1.40, null);
            printOk("\tOK : quartier null");
        } catch (IllegalArgumentException e) {
            printError("\tECHEC : quartier null : " + e.getMessage());
        }
    }

    public static void testSetQuartier() {
        System.out.println("\nTest de setQuartier");

        Quartier.deleteQuartier(1);
        Quartier q1 = new Quartier(1, "Centre ville", 1000);
        Quartier q2 = new Quartier(2, "Quartier 2", 2000);

        Compteur.deleteCompteur(1);
        Compteur c = new Compteur(1, "Bonduelle", "Nord", 49.7, -1.40);

        try {
            c.setQuartier(q1);
            String expected = "Bonduelle vers Nord#1 : quartier = 1#, latitude = 49.7, longitude = -1.4";
            String actual = c.toString();

            if (!expected.equals(actual)) {
                printError("\tECHEC : cas normal set nouveau quartier (peut venir de toString)");
                printError("\t\tExpected : " + expected);
                printError("\t\tActual : " + actual);
                nbError -= 2;
                nbTest -= 2;
            } else {
                printOk("\tOK : cas normal set nouveau quartier");
            }

        } catch (IllegalArgumentException e) {
            printError("\tECHEC : cas normal set nouveau quartier : " + e.getMessage());
        }

        try {
            c.setQuartier(q2);
            String expected = "Bonduelle vers Nord#1 : quartier = 2#, latitude = 49.7, longitude = -1.4";
            String actual = c.toString();

            if (!expected.equals(actual)) {
                printError("\tECHEC : cas normal remplacement de quartier (peut venir de toString)");
                printError("\t\tExpected : " + expected);
                printError("\t\tActual : " + actual);
                nbError -= 2;
                nbTest -= 2;
            } else {
                printOk("\tOK : cas normal remplacement de quartier");
            }

        } catch (IllegalArgumentException e) {
            printError("\tECHEC : cas normal remplacement de quartier : " + e.getMessage());
        }

        try {
            c.setQuartier(-2);
            printError("\tECHEC : cas d'erreur : set quartier avec id négatif");
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("models.Compteur.setQuartier : L'id du quartier ne peut pas être négatif")) {
                printError("\tECHEC : cas d'erreur : set quartier avec id négatif : " + e.getMessage());
            } else {
                printOk("\tOK : cas d'erreur : set quartier avec id négatif");
            }
        }

        try {
            c.setQuartier(3);
            printError("\tECHEC : cas d'erreur : set quartier avec id inexistant");
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("models.Compteur.setQuartier : L'id du quartier ne correspond à aucun quartier")) {
                printError("\tECHEC : cas d'erreur : set quartier avec id inexistant : " + e.getMessage());
            } else {
                printOk("\tOK : cas d'erreur : set quartier avec id inexistant");
            }
        }
    }
    
    public static void testToCSV() {
        System.out.println("\nTest de toCSV");

        Quartier.deleteQuartier(1);
        Quartier q1 = new Quartier(1, "Centre ville", 1000);

        Compteur.deleteCompteur(1);
        Compteur c = new Compteur(1, "Bonduelle", "Nord", 49.7, -1.40);

        try {
            String expected = "1;Bonduelle;Nord;49.7;-1.4;null";
            String actual = c.toCSV();

            if (!expected.equals(actual)) {
                printError("\tECHEC : cas normal quartier null");
                printError("\t\tExpected : " + expected);
                printError("\t\tActual : " + actual);
                nbError -= 2;
                nbTest -= 2;
            } else {
                printOk("\tOK : cas normal quartier null");
            }

        } catch (IllegalArgumentException e) {
            printError("\tECHEC : cas normal quartier null : " + e.getMessage());
        }

        try {
            c.setQuartier(q1);
            String expected = "1;Bonduelle;Nord;49.7;-1.4;1";
            String actual = c.toCSV();

            if (!expected.equals(actual)) {
                printError("\tECHEC : cas normal");
                printError("\t\tExpected : " + expected);
                printError("\t\tActual : " + actual);
                nbError -= 2;
                nbTest -= 2;
            } else {
                printOk("\tOK : cas normal");
            }

        } catch (IllegalArgumentException e) {
            printError("\tECHEC : cas normal : " + e.getMessage());
        }
    }

    public static void printOk(String message) {
        System.out.println(COLOR_OK + message + COLOR_RESET);
        nbTest++;
    }

    public static void printError(String message) {
        System.out.println(COLOR_ERROR + message + COLOR_RESET);
        nbTest++;
        nbError++;
    }

}
