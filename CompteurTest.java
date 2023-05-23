import models.Compteur;
import models.Quartier;

public class CompteurTest {
    private Compteur compteur;

    public void setUp() {
        Quartier quartier = new Quartier(1, "Quartier 1", 10.0);
        compteur = new Compteur(1, "Compteur 1", "Sens 1", 0.0, 0.0, quartier);
    }

    public void testGetId() {
        setUp();
        int expectedId = 1;
        int actualId = compteur.getId();
        assertEqual(expectedId, actualId);
    }

    public void testGetLibelle() {
        setUp();
        String expectedLibelle = "Compteur 1";
        String actualLibelle = compteur.getLibelle();
        assertEqual(expectedLibelle, actualLibelle);
    }

    public void testGetSens() {
        setUp();
        String expectedSens = "Sens 1";
        String actualSens = compteur.getSens();
        assertEqual(expectedSens, actualSens);
    }

    public void testGetLatitude() {
        setUp();
        double expectedLatitude = 0.0;
        double actualLatitude = compteur.getLatitude();
        assertEqual(expectedLatitude, actualLatitude);
    }

    public void testGetLongitude() {
        setUp();
        double expectedLongitude = 0.0;
        double actualLongitude = compteur.getLongitude();
        assertEqual(expectedLongitude, actualLongitude);
    }

    public void testGetQuartier() {
        setUp();
        Quartier expectedQuartier = new Quartier(1, "Quartier 1", 10.0);
        Quartier actualQuartier = compteur.getQuartier();
        assertEqual(expectedQuartier, actualQuartier);
    }

    private void assertEqual(int expected, int actual) {
        if (expected != actual) {
            System.out.println("Test failed: Expected " + expected + ", but got " + actual);
        } else {
            System.out.println("Test passed");
        }
    }

    private void assertEqual(String expected, String actual) {
        if (!expected.equals(actual)) {
            System.out.println("Test failed: Expected " + expected + ", but got " + actual);
        } else {
            System.out.println("Test passed");
        }
    }

    private void assertEqual(double expected, double actual) {
        if (Double.compare(expected, actual) != 0) {
            System.out.println("Test failed: Expected " + expected + ", but got " + actual);
        } else {
            System.out.println("Test passed");
        }
    }

    private void assertEqual(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            System.out.println("Test failed: Expected " + expected + ", but got " + actual);
        } else {
            System.out.println("Test passed");
        }
    }

    public void runTests() {
        testGetId();
        testGetLibelle();
        testGetSens();
        testGetLatitude();
        testGetLongitude();
        testGetQuartier();
    }

    public static void main(String[] args) {
        CompteurTest test = new CompteurTest();
        test.runTests();
    }
}
