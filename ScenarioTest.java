import java.util.ArrayList;
import java.util.Arrays;

import models.*;

public class ScenarioTest {
    public static void main(String[] args) {

        // --------- Imaginons que l'on récupère les données de la base de donnée ---------
        // Création de quartier (2 quartier)
        System.out.println("--------- Création de quartier (2 quartier) ---------");
        Quartier q1 = new Quartier(1, "Centre Ville", 21549);
        Quartier q2 = new Quartier(4, "Hauts Pavés - Saint Félix", 30523);

        // Création de compteur (2 compteur par quartier)
        System.out.println("--------- Création de compteur (2 compteur par quartier) ---------");
        Compteur c1 = new Compteur(654, "Bonduelle", "Sud", 47.2116, -1.5432, q1);
        Compteur c2 = new Compteur(655, "Bolle", "Nord", 47.2116, -1.5432, q1);
        Compteur c3 = new Compteur(746, "Chemin de la halage Tortière", "Sud", 47.2356, -1.5495, q2);
        Compteur c4 = new Compteur(747, "Chemin de la halage Tortière", "Nord", 47.2356, -1.5495, q1);

        // Création de jours (une semaine)
        System.out.println("--------- Création de jours (une semaine) ---------");
        Jour j1 = new Jour("2020-01-01", "Mercredi", "Noel", 7.28);
        Jour j2 = new Jour("2020-01-02", "Jeudi", "Noel", 9.70);
        Jour j3 = new Jour("2020-01-03", "Vendredi", "Noel", 10.80);
        Jour j4 = new Jour("2020-01-04", "Samedi", "Noel", 5.28);
        Jour j5 = new Jour("2020-01-05", "Dimanche", "Noel", 6);
        Jour j6 = new Jour("2020-01-06", "Lundi", null, 4.32);
        Jour j7 = new Jour("2020-01-07", "Mardi", null, 10.40);

        // Création de releveJournalier
        System.out.println("--------- Création de releveJournalier ---------");
        int[] rjH1 = { 0, 1, 0, 3, 3, 0, 3, 1, 0, 1, 2, 12, 5, 4, 5, 18, 20, 15, 4, 3, 0, 2, 0, 0 };
        ReleveJournalier rj1 = new ReleveJournalier(746, "2020-01-01", rjH1, null);
        int[] rjH2 = { 2, 0, 0, 0, 0, 0, 1, 14, 33, 21, 9, 14, 9, 12, 17, 16, 24, 36, 32, 6, 5, 2, 2, 3 };
        ReleveJournalier rj2 = new ReleveJournalier(746, "2020-01-02", rjH2, null);
        int[] rjH3 = { 0, 1, 0, 0, 0, 1, 2, 13, 32, 23, 8, 14, 8, 14, 12, 14, 29, 38, 19, 7, 2, 2, 3, 0 };
        ReleveJournalier rj3 = new ReleveJournalier(746, "2020-01-03", rjH3, null);
        int[] rjH4 = { 4, 0, 0, 0, 0, 1, 0, 1, 1, 0, 28, 24, 37, 18, 17, 26, 47, 30, 8, 8, 6, 4, 1, 6 };
        ReleveJournalier rj4 = new ReleveJournalier(746, "2020-01-04", rjH4, null);
        int[] rjH5 = { 2, 1, 0, 0, 0, 0, 0, 1, 0, 11, 15, 37, 25, 24, 32, 40, 62, 48, 12, 1, 1, 2, 0, 0 };
        ReleveJournalier rj5 = new ReleveJournalier(746, "2020-01-05", rjH5, null);
        int[] rjH6 = { 0, 0, 0, 0, 0, 0, 2, 26, 87, 25, 12, 12, 47, 26, 11, 31, 48, 120, 104, 27, 10, 10, 3, 0 };
        ReleveJournalier rj6 = new ReleveJournalier(746, "2020-01-06", rjH6, null);
        int[] rjH7 = { 0, 0, 0, 0, 0, 1, 1, 34, 93, 34, 13, 14, 26, 30, 13, 21, 44, 107, 102, 47, 9, 4, 8, 1 };
        ReleveJournalier rj7 = new ReleveJournalier(746, "2020-01-07", rjH7, null);

        int[] rjH8 = { 0, 0, 3, 1, 0, 2, 2, 3, 0, 1, 3, 10, 8, 11, 8, 13, 9, 13, 3, 2, 4, 1, 2, 0 };
        ReleveJournalier rj8 = new ReleveJournalier(747, "2020-01-01", rjH8, null);
        int[] rjH9 = { 0, 0, 0, 0, 0, 0, 1, 4, 34, 22, 12, 8, 12, 17, 9, 13, 10, 40, 38, 10, 3, 0, 3, 2 };
        ReleveJournalier rj9 = new ReleveJournalier(747, "2020-01-02", rjH9, null);
        int[] rjH10 = { 1, 1, 0, 0, 0, 0, 1, 7, 32, 34, 19, 15, 12, 14, 6, 17, 21, 45, 33, 7, 6, 1, 1, 3 };
        ReleveJournalier rj10 = new ReleveJournalier(747, "2020-01-03", rjH10, null);
        int[] rjH11 = { 2, 4, 0, 0, 1, 0, 0, 3, 7, 16, 16, 21, 33, 15, 28, 39, 49, 33, 12, 4, 3, 1, 3, 5 };
        ReleveJournalier rj11 = new ReleveJournalier(747, "2020-01-04", rjH11, null);
        int[] rjH12 = { 0, 2, 7, 2, 0, 2, 0, 1, 9, 10, 52, 39, 38, 13, 52, 70, 57, 32, 16, 11, 1, 0, 2, 0 };
        ReleveJournalier rj12 = new ReleveJournalier(747, "2020-01-05", rjH12, null);
        int[] rjH13 = { 0, 0, 0, 0, 0, 0, 6, 60, 159, 66, 31, 12, 26, 34, 18, 15, 33, 57, 83, 25, 6, 0, 4, 1 };
        ReleveJournalier rj13 = new ReleveJournalier(747, "2020-01-06", rjH13, null);
        int[] rjH14 = { 1, 0, 1, 0, 1, 1, 1, 60, 173, 77, 23, 25, 22, 35, 18, 11, 22, 51, 76, 20, 12, 5, 7, 2 };
        ReleveJournalier rj14 = new ReleveJournalier(747, "2020-01-07", rjH14, null);

        // --------- Cas d'usage ---------
        // On change un compteur de quartier
        System.out.println("Quartier du compteur " + c4.getId() + "(avant changement) : " + c4.getQuartier().getNom());
        c4.setQuartier(q2);
        System.out.println("Quartier du compteur " + c4.getId() + "(après changement) : " + c4.getQuartier().getNom());

        // On corrige une faute de frappe dans un libelle de compteur
        System.out.println("Libelle du compteur " + c2.getId() + " (avant renommage) : " + c2.getLibelle());
        c2.setLibelle("Bonduelle");
        System.out.println("Libelle du compteur " + c2.getId() + " (avant renommage) : " + c2.getLibelle());

        // On récupère la position d'un compteur
        System.out.println("Position du compteur " + c1.getId() + " : " + Arrays.toString(c1.getPosition()));

        // On récupère la liste des compteurs d'un quartier
        System.out.println("Liste des compteurs du quartier " + q1.getNom() + " :");
        for (int cId : q1.getCompteursList()) {
            Compteur c = Compteur.getCompteur(cId);
            System.out.print(c.getId() + ", ");
        }
        System.out.println();

        // Afficher "youpiiiiiiii" si c'est un weekend
        if (j1.estWeekEnd()) {
            System.out.println("Jour " + j1.getDate() + ": youpiiiiiiii");
        } else {
            System.out.println("Jour " + j1.getDate() + " : nonnnnnnnnnnnnnnnnn");
        }

        if (j5.estWeekEnd()) {
            System.out.println("Jour " + j5.getDate() + ": youpiiiiiiii");
        } else {
            System.out.println("Jour " + j5.getDate() + " : nonnnnnnnnnnnnnnnnn");
        }

        // Afficher le quartier du c3
        System.out.println("Quartier du compteur " + c3.getId() + " : " + c3.getQuartier());

        // Afficher le nombre de passage moyen de la semaine sur le quartier 4
        ArrayList<Integer> q2List = q2.getCompteursList();
        int nbPassageTotal = 0;
        int nbReleves = 0;
        for (int cId : q2List) {
            ArrayList<ReleveJournalier> rjList = ReleveJournalier.getReleveByCompteur(cId);
            for (ReleveJournalier rj : rjList) {
                nbPassageTotal += rj.getNbPassageTotal();
                nbReleves++;
            }
        }
        System.out.println("Nombre de passage moyen de la semaine sur le quartier " + q2.getNom() + " : "
                + (nbPassageTotal / nbReleves));

    }

}



// 