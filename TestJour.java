import models.Jour;

public class TestJour {
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_ERROR = "\u001B[31m";
    public static final String COLOR_OK = "\u001B[32m";

    public static int nbTest = 0;
    public static int nbError = 0;

    public static void main(String[] args) {
        System.out.println("------ TestJour ------");
        testConstructor();

        System.out.println();
        if (nbError == 0) {
            printOk("Test réussi ! : " + nbTest + " / " + nbTest);
        } else {
            printError("Echec du test : " + (nbTest - nbError) + " / " + nbTest);
        }
    }

    public static void testConstructor() {
        System.out.println("\nTest du constructeur");

        
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
