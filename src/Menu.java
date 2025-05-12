import java.util.*;


import inputs.*;            // Imports all classes from the inputs package
import comparateurs.*;      // Imports all comparators
import generateur.*;
import config.preprocessor.*;
import moteur.*  ;
import selectionneur.*;
import importer.*;

import java.io.*;

public class Menu {

        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            MoteurDeRecherche moteur = configurerMoteur();

            while (true) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Effectuer une recherche");
                System.out.println("2. Comparer deux listes");
                System.out.println("3. Dédupliquer une liste");
                System.out.println("4. Reconfigurer les paramètres");
                System.out.println("5. Quitter");

                switch (getIntInput("Votre choix: ")) {
                    case 1 -> effectuerRecherche(moteur);
                    case 2 -> comparerDeuxListes(moteur);
                    case 3 -> dedupliquerListe(moteur);
                    case 4 -> moteur = configurerMoteur();
                    case 5 -> {
                        System.out.println("Au revoir !");
                        return;
                    }
                    default -> System.out.println("Choix invalide !");
                }
            }
        }

        private static MoteurDeRecherche configurerMoteur() {
            System.out.println("=== CONFIGURATION ===");

            List<Pretraiteur> pretraiteurs = choisirPretraiteurs();
            ComparateurDeChaine comparateur = choisirComparateur();
            Selectionneur selectionneur = choisirSelectionneur();
            Generateur generateur = choisirGenerateur();

            MoteurDeRecherche moteur = new MoteurDeRecherche(generateur, new ComparateurCombine(comparateur), selectionneur);
            moteur.setPretraiteurs(pretraiteurs);

            return moteur;
        }

        private static List<Pretraiteur> choisirPretraiteurs() {
            List<Pretraiteur> pretraiteurs = new ArrayList<>();
            System.out.println("-------------Choix de pretraiteurs-------------");
            System.out.println("1. Minuscules");
            System.out.println("2. Nettoyeur caractères spéciaux");
            System.out.println("3. Les deux");
            System.out.println("4. Sans pretraiteurs");

            switch (getIntInput("Choix de prétraitement: ")) {
                case 1 -> pretraiteurs.add(new TransformateurMinuscules());
                case 2 -> pretraiteurs.add(new NettoyeurDeListe());
                case 3 -> {
                    pretraiteurs.add(new TransformateurMinuscules());
                    pretraiteurs.add(new NettoyeurDeListe());
                }
                case 4 -> System.out.println("hope this is the problem"); // doesn't do anything

                default -> System.out.println("Choix invalide, aucun prétraitement ajouté.");
            }
            System.out.println(pretraiteurs);
            return pretraiteurs;
        }

        private static ComparateurDeChaine choisirComparateur() {
            System.out.println("--------------Choix de comparateur-------------");
            System.out.println("1. Levenshtein");
            System.out.println("2. Jaro-Winkler");
            return switch (getIntInput("Choix de comparateur: ")) {
                case 1 -> new ComparateurLevenshtein();
                case 2 -> new ComparateurJaroWinkler();
                default -> {
                    System.out.println("Choix invalide. Comparateur par défaut utilisé.");
                    yield new ComparateurLevenshtein();
                }
            };
        }

        private static Selectionneur choisirSelectionneur() {
            System.out.println("--------------Choix de selectionneur------------");
            System.out.println("1. Par seuil");
            System.out.println("2. Top N");

            return switch (getIntInput("Choix de sélectionneur: ")) {
                case 1 -> {
                    double seuil = getDoubleInput("Seuil: ");
                    yield new SelectionneurSimple();
                }
                case 2 -> {
                    int n = getIntInput("N: ");
                    yield new SelectionneurDeNPremiers(n);
                }
                default -> {
                    System.out.println("Choix invalide. Sélectionneur simple utilisé.");
                    yield new SelectionneurSimple();
                }
            };
        }

        private static Generateur choisirGenerateur() {
            System.out.println("------------Choix de generateur------------1");
            System.out.println("1. Générateur par taille");
            System.out.println("2. Générateur par taille V2");

            return switch (getIntInput("Choix de générateur: ")) {
                case 1 -> new GenerateurDeCandidatsParTaille();
                case 2 -> new GenerateurDeCandidatsParTailleV2();
                default -> {
                    System.out.println("Choix invalide. Générateur simple utilisé.");
                    yield new GenerateurDeCandidatsParTailleV2();
                }
            };
        }

        private static void effectuerRecherche(MoteurDeRecherche moteur) {
            String nom = getStringInput("Nom à rechercher: ");
            Nom nomARechercher = new Nom(nom);
            File fichier = demanderFichier("Fichier CSV: ");
            if (fichier == null) return;

            DataImporter importer = new LocalCSVDataImporter(fichier.getPath());
            List<Nom> liste = importer.importData();

            long start = System.nanoTime();
            System.out.println(moteur.search(nomARechercher, liste));
            long end = System.nanoTime();
            for ( Nom n : moteur.search(nomARechercher, liste)) {
                System.out.println(n.getNomOriginalString());
            }
            System.out.println("Durée: " + (end - start) / 1_000_000 + " ms");
        }

        private static void comparerDeuxListes(MoteurDeRecherche moteur) {
            File f1 = demanderFichier("Premier fichier CSV: ");
            File f2 = demanderFichier("Deuxième fichier CSV: ");
            if (f1 == null || f2 == null) return;

            List<Nom> l1 = new LocalCSVDataImporter(f1.getPath()).importData();
            List<Nom> l2 = new LocalCSVDataImporter(f2.getPath()).importData();

            long start = System.nanoTime();
//            moteur.comparer(l1, l2).forEach(System.out::println);
            long end = System.nanoTime();

            System.out.println("Durée: " + (end - start) / 1_000_000 + " ms");
        }

        private static void dedupliquerListe(MoteurDeRecherche moteur) {
            File f1 = demanderFichier("Premier fichier CSV: ");
            File f2 = demanderFichier("Deuxième fichier CSV: ");
            if (f1 == null || f2 == null) return;

            List<Nom> l1 = new LocalCSVDataImporter(f1.getPath()).importData();
            List<Nom> l2 = new LocalCSVDataImporter(f2.getPath()).importData();

            long start = System.nanoTime();
//            moteur.dedupliquer(l1, l2).forEach(System.out::println);
            long end = System.nanoTime();

            System.out.println("Durée: " + (end - start) / 1_000_000 + " ms");
        }

        // === Utility Methods ===

        private static File demanderFichier(String prompt) {
            System.out.print(prompt);
            String path = scanner.nextLine();
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Fichier introuvable !");
                return null;
            }
            return file;
        }

        private static int getIntInput(String prompt) {
            while (true) {
                try {
                    System.out.print(prompt);
                    return Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException ex) {
                    System.out.println("Entrée invalide !");
                }
            }
        }

        private static double getDoubleInput(String prompt) {
            while (true) {
                try {
                    System.out.print(prompt);
                    return Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException ex) {
                    System.out.println("Entrée invalide !");
                }
            }
        }

        private static String getStringInput(String prompt) {
            System.out.print(prompt);
            return scanner.nextLine();
        }
    }


