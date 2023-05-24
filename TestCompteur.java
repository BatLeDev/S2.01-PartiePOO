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


        System.out.println();
        if (nbError == 0) {
            printOk("Test réussi ! : " + nbTest + " / " + nbTest);
        } else {
            printError("Echec du test : " + (nbTest - nbError) + " / " + nbTest);
        }
    }

    public static void testConstructor() {
        System.out.println("Test du constructeur sans quartier");
        try {
            new Compteur(1, "Bonduelle", "Nord", 49.7, -1.40);

            String expected = "Bonduelle vers Nord#1 : quartier = null, latitude = 49.7, longitude = -1.4";
            String actual = Compteur.getCompteurById(1).toString();

            if (!expected.equals(actual)) {
                printError("\tECHEC : Cas normal (peut venir de toString))");
                printError("\t\tExpected : " + expected);
                printError("\t\tActual : " + actual);
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

        System.out.println("Test du constructeur avec quartier");
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
