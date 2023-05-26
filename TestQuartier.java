import models.Quartier;

public class TestQuartier {
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_ERROR = "\u001B[31m";
    public static final String COLOR_OK = "\u001B[32m";

    public static int nbTest = 0;
    public static int nbError = 0;

    public static void main(String[] args) {
        System.out.println("------ TestQuartier ------");
        testConstructor();
        testToCSV();
        testgetQuartier();
        testdeleteQuartier();

        System.out.println();
        if (nbError == 0) {
            printOk("Test réussi ! : " + nbTest + " / " + nbTest);
        } else {
            printError("Echec du test : " + (nbTest - nbError) + " / " + nbTest);
        }
    }

    public static void testConstructor() {
        System.out.println("\nTest du constructeur");
        try {
            Quartier quartier = new Quartier(10, "Quartier Nord", 100.0);
            String expected = "Quartier Nord#10 : nbCompteurs = 0, longueurPiste = 100.0";
            String actual = quartier.toString();
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
            new Quartier(10, "Quartier Nord", 100.0);
            printError("\tECHEC : cas d'erreur id existant"); // Si aucune exception n'est levée
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("models.Quartier.constructor : l'id est invalide (<0 ou deja existant)")) {
                printOk("\tOK : cas d'erreur id existant");
            } else {
                printError("\tECHEC : cas d'erreur id existant : " + e.getMessage());
            }
        }

        try {
            new Quartier(11, "", 100.0);
            printError("\tECHEC : cas d'erreur libelle vide"); // Si aucune exception n'est levée
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("models.Quartier.constructor : models.Quartier.setNom : Le parametre nom n'est pas valide")) {
                printOk("\tOK : cas d'erreur libelle vide");
            } else {
                printError("\tECHEC : cas d'erreur libelle vide : " + e.getMessage());
            }
        }
    }

    public static void testToCSV() {
        System.out.println("\nTest de toCSV");
        Quartier quartier = new Quartier(14, "Quartier Nord", 100.0);
        String expected = "14;Quartier Nord;100.0";
        String actual = quartier.toCSV();
        if (!expected.equals(actual)) {
            printError("\tECHEC : cas normal");
            printError("\t\tExpected : " + expected);
            printError("\t\tActual : " + actual);
            nbError -= 2;
            nbTest -= 2;
        } else {
            printOk("\tOK : cas normal");
        }
    }

    public static void testgetQuartier() {
        System.out.println("\nTest de getQuartier");
        Quartier quartier = Quartier.getQuartier(10);
        String expected = "Quartier Nord#10 : nbCompteurs = 0, longueurPiste = 100.0";
        String actual = quartier.toString();
        if (!expected.equals(actual)) {
            printError("\tECHEC : cas normal (peut venir de toString)");
            printError("\t\tExpected : " + expected);
            printError("\t\tActual : " + actual);
            nbError -= 2;
            nbTest -= 2;
        } else {
            printOk("\tOK : cas normal");
        }


        Quartier q = Quartier.getQuartier(-1);
        if (q != null) {
            printError("\tECHEC : cas d'erreur id inexistant");
            printError("\t\tExpected : null");
            printError("\t\tActual : " + q);
            nbError -= 2;
            nbTest -= 2;
        } else {
            printOk("\tOK : cas d'erreur id inexistant");
        }
    }

    public static void testdeleteQuartier() {
        System.out.println("\nTest de deleteQuartier");
        // Création d'un quartier
        new Quartier(20, "Quartier Sud", 100.0);
        // Suppression du quartier
        Quartier.deleteQuartier(20);
        // Vérification de la suppression
        Quartier quartier = Quartier.getQuartier(20);
        if (quartier != null) {
            printError("\tECHEC : cas normal");
            nbError -= 2;
            nbTest -= 2;
        } else {
            printOk("\tOK : cas normal");
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
