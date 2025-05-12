import java.util.*;


import config.indexeur.Mapper;
import inputs.*;            // Imports all classes from the inputs package
import comparateurs.*;      // Imports all comparators
import generateur.*;
import config.preprocessor.*;
import moteur.*  ;
import selectionneur.*;
import importer.*;
import java.util.*;
import java.io.*;

public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public  static void main(String[] args) {
        List< Pretraiteur> pp = new ArrayList< Pretraiteur>();
        pp.add ( new NettoyeurDeListe());
        pp.add ( new TransformateurMinuscules());
        GenerateurDeCandidatsParTailleV2 generatur  = new GenerateurDeCandidatsParTailleV2();
        ComparateurCombine comparateur = new ComparateurCombine(new ComparateurLevenshtein());
        SelectionneurSimple selectionneur = new SelectionneurSimple();
        MoteurDeRecherche moteur = new MoteurDeRecherche(generatur, comparateur, selectionneur);
        moteur.setPretraiteurs(pp);
        int choice;

        do {
            printMainMenu();
            choice = getIntInput("Votre choix: ");

            switch (choice) {
                case 1:
                    effectuerRecherche(moteur);
                    break;
                case 2:
                    comparerDeuxListes(moteur);
                    break;
                case 3:
                    dedupliquerListe(moteur);
                    break;
                case 4:
                    configurerParametres(moteur);
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }

        } while (choice != 5);
    }

    static void printMainMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Effectuer une recherche");
        System.out.println("2. Comparer deux listes");
        System.out.println("3. Dédupliquer une liste");
        System.out.println("4. Configurer les paramètres");
        System.out.println("5. Quitter");
    }

       static void effectuerRecherche(MoteurDeRecherche moteur) {
        System.out.print("Saisir le nom à rechercher: ");
        String nom = scanner.nextLine();
        File fichier = demanderFichier("Fournir le fichier CSV: ");
        DataImporter localCSVImporter = new LocalCSVDataImporter(fichier.getPath() );
        List<Nom> listeDeNoms = localCSVImporter.importData();
        if (fichier == null) return;
        System.out.println("Recherche effectuée pour: " + nom);
        moteur.search(new Nom(nom), listeDeNoms);

    }


     static void comparerDeuxListes(MoteurDeRecherche moteur) {
        File fichier1 = demanderFichier("Fournir le premier fichier CSV: ");
        if (fichier1 == null) return;
        File fichier2 = demanderFichier("Fournir le second fichier CSV: ");
        if (fichier2 == null) return;

        // ComparateurDeNom comparateur = new ComparateurDeNom(config);
        // comparateur.comparer(fichier1, fichier2);

        System.out.println("Comparaison effectuée.");
    }

     static void dedupliquerListe(MoteurDeRecherche moteur) {
        File fichier = demanderFichier("Fournir le fichier à traiter: ");
        if (fichier == null) return;
        System.out.println("Déduplication effectuée.");
    }
     static void choixPretraiteurs (MoteurDeRecherche moteur) {
       List<Pretraiteur> listeDePretraiteurs = new ArrayList<>();
        System.out.println("1. Nettoyer la liste des caracteres speciaux");
        System.out.println("2. Transfromer la liste en minuscules");
        System.out.println("3. Utiliser les deux pretraiteurs");
        System.out.println("4. Retour");
        int choix = getIntInput("Votre choix: ");
        switch (choix) {
            case 1:
                listeDePretraiteurs.add(new NettoyeurDeListe());break ;
            case 2 :
                listeDePretraiteurs.add (new TransformateurMinuscules());break ;
            case 3 :
                listeDePretraiteurs.add(new TransformateurMinuscules());
                listeDePretraiteurs.add(new NettoyeurDeListe());break ;
            case 4 :

            default :
                System.out.println("Choix invalide !");
        }
        moteur.setPretraiteurs(listeDePretraiteurs);

    }
    static void choixComparateur ( MoteurDeRecherche moteur) {
        int choix ;
        System.out.println("1. Comparateur de Levenshtein");
        System.out.println("2. Comparateur de Jaro-Winkler");
        System.out.println("3. Retour");
        choix = getIntInput("Votre choix:");
        switch ( choix ){
            case 1  :
                moteur.setComparateurUtilise( new ComparateurCombine(new ComparateurLevenshtein()));
            case 2 :
                moteur.setComparateurUtilise( new ComparateurCombine(new ComparateurJaroWinkler()));
            case 3 :
                configurerParametres(moteur);
            default :
                System.out.println("Choix invalide !");
        }
     }
     static void choixSelectionneur ( MoteurDeRecherche moteur) {
         int choix;
         do {
             System.out.println("1. Choisir le selectionneur par seuil");
             System.out.println("2. Choisir le selectionneur des N premiers");
             System.out.println("3. Retour");
             choix = getIntInput("Votre choix:");
             switch (choix) {
                 case 1:
                     moteur.setSelectionneur(new SelectionneurSimple());
                     break ;
                 case 2:
                     int N;
                     N = getIntInput("Donner N: ");
                     moteur.setSelectionneur(new SelectionneurDeNPremiers(N));
                    break ;
                 case 3:
                     configurerParametres(moteur);
                     break;
                 default:
                     System.out.println("Choix invalide !");
         }
    } while (choix != 3 );
}

    static void configurerParametres(MoteurDeRecherche moteur) {
        int choix;
        do {
            System.out.println("\n=== CONFIGURATION ===");
            System.out.println("1. Choisir les prétraitements");
            System.out.println("3. Choisir l'outil de comparaison");
            System.out.println("4. Choisir le selectionneur");
            System.out.println("5. Retour");

            choix = getIntInput("Votre choix: ");
            switch (choix) {
                case 1:
                    choixPretraiteurs(moteur);
                    break;
                case 2:
                    choixPretraiteurs(moteur);
                    break;
                case 3:
                    choixComparateur(moteur);
                    break ;
                case 4:
                    choixSelectionneur( moteur );
                    break;
                case 5:
                    printMainMenu();
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 5);
    }

     static File demanderFichier(String prompt) {
        System.out.print(prompt);
        String path = scanner.nextLine();
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Fichier introuvable !");
            return null;
        }
        return file;
    }

     static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Entrée invalide !");
            }
        }
    }

     static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Entrée invalide !");

            }
        }
     }

}
