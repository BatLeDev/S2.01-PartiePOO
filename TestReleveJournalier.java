import models.ReleveJournalier;

public class TestReleveJournalier {
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_OK = "\u001B[32m";
    private static final String COLOR_ERROR = "\u001B[31m";
    private static int nbTest = 0;
    private static int nbError = 0;

    public static void main(String[] args) {
        
        
        System.out.println();
        if (nbError == 0) {
            printOk("Test réussi ! : " + nbTest + " / " + nbTest);
        } else {
            printError("Echec du test : " + (nbTest - nbError) + " / " + nbTest);
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
