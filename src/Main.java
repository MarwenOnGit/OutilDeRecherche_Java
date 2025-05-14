import comparateurDeListes.ComparateurDeListes;
import comparateurDeListes.ComparateurDeListesConcret;
import config.preprocessor.PretraiteurDeCharSpecial;
import config.preprocessor.Pretraiteur;
import config.preprocessor.TransformateurMinuscules;
import generateur.Generateur;
import generateur.GenerateurDeCandidatsParPremiereLettre;
import generateur.GenerateurDeCandidatsParTailleV2;
import generateur.GenerateurDeCandidatsSimple;
import importer.DataImporter;
import importer.LocalCSVDataImporter;
import selectionneur.Selectionneur;
import selectionneur.SelectionneurSimple;
import comparateurs.*;
import inputs.*;
import moteur.*;


import java.util.*;
import java.util.Scanner ;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

//
//        /////////////// Data importing
//        DataImporter localCSVImporter = new LocalCSVDataImporter("src/data/peps_names_658k.csv");
//        List<Nom> listeDeNoms = localCSVImporter.importData(); // Désactivé lors de la comparaison
//        /////////////////////////////




        /////////// engine pieces
        Selectionneur<List<Nom>> selectionneur = new SelectionneurSimple(0.7);
//        Generateur generateur = new GenerateurDeCandidatsParTailleV2();
        Generateur generateur = new GenerateurDeCandidatsParPremiereLettre();
        ComparateurDeNom comparateur = new ComparateurDeNomSimple();
        /// /////////////////////


        /// //////////// engine init
        MoteurDeRecherche moteur = new MoteurDeRecherche(generateur, comparateur, selectionneur);
        /// //////////////////


        //////// Preprocessors
        Pretraiteur pretraiteur1 = new PretraiteurDeCharSpecial() ;
        Pretraiteur pretraiteur2 = new TransformateurMinuscules();
        List<Pretraiteur> pretraiteurs = new ArrayList<Pretraiteur>();
        pretraiteurs.add(new PretraiteurDeCharSpecial());
        pretraiteurs.add(new TransformateurMinuscules());
        moteur.pretraiteurs = pretraiteurs;
//        moteur.pretraiteurs = new ArrayList<>(); //DEBUG
//        moteur.executerPretraitement(listeDeNoms); // execution du pretraitement //deactivé lors de comparaison des listes

        /////////////////////////////////////////

        //application timing start (Comparison)
        long startTime = System.currentTimeMillis();
        ////////////////////////////////////////

        ///  Comparison
        DataImporter localCSVImporter = new LocalCSVDataImporter("src/data/peps_names_64k.csv");
        List<Nom> listeNom1 = localCSVImporter.importData();
        localCSVImporter = new LocalCSVDataImporter("src/data/peps_names_64k.csv");
        List<Nom> listeNom2 = localCSVImporter.importData();

        ComparateurDeListes comparateurDeListes = new ComparateurDeListesConcret(moteur);
        List<Nom> nomsEnCommun = comparateurDeListes.comparerListes(listeNom1,listeNom2);
        System.out.println("liste de noms en commun:("+nomsEnCommun.size()+")");
//        System.out.println(nomsEnCommun.toString());
//        System.out.println(listeNom1.subList(0,10));

        ////////////////////////////////////////////////

        // applcation timing end (Comparison)
        long endTime = System.currentTimeMillis();
        System.out.println("temps d'execution: " + (endTime - startTime) + " ms");

        /////////////////////////////////////////////////

//
//        /////////// search input
//        System.out.print("Enter the name you wanna search :");
//        Scanner scannerObj = new Scanner(System.in);
//        Nom nomToSearch = new Nom(scannerObj.nextLine());
//        ////////////////////////
//
//
//        //application timing start
//        long startTime = System.currentTimeMillis();
//        ////////////////////////////////////////
//
//
//        /////////// search execution
//        List<Nom> resultat = moteur.search(nomToSearch, listeDeNoms);
//        List<String> resultatEnString  = new ArrayList<String>();
//        for (Nom nom : resultat){
//            resultatEnString.add(nom.getNomOriginalString());
//        }
//        ///////////////////////////////////
//
//
//        // applcation timing end
//        long endTime = System.currentTimeMillis();
//        /////////////////////////////////////////////////
//
//
//        ///////////////////// results and data
//        System.out.println("nom recherché length: " + String.join("", nomToSearch.getMots()).length());
//        System.out.println("nombre de resultats: "+ resultatEnString.size());
//        System.out.println(resultatEnString);
//        System.out.println("Exection time : " + (int)(endTime - startTime)/1000 + " seconds and " +(endTime - startTime) % 1000 + " ms");
//        ///////////////////////////////////////////////

    }

}