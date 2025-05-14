import config.indexeur.Indexeur;
import config.indexeur.Mapper;
import config.preprocessor.NettoyeurDeListe;
import config.preprocessor.Pretraiteur;
import config.preprocessor.TransformateurMinuscules;
import generateur.*;
import importer.DataImporter;
import importer.LocalCSVDataImporter;
import selectionneur.Selectionneur;
import selectionneur.SelectionneurDeNPremiers;
import selectionneur.SelectionneurSimple;
import comparateurs.*;
import inputs.*;
import moteur.*;


import java.util.*;
import java.util.Scanner ;
import comparateurs.ComparateurLevenshtein;
public class  MainComparaison {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Mapper map = new Mapper();
        ComparateurDeNom comparateur = new ComparateurCombine(new ComparateurLevenshtein());
        Pretraiteur pretraiteur1 = new NettoyeurDeListe() ;
        Pretraiteur pretraiteur2 = new TransformateurMinuscules();


        DataImporter localCSVImporter1 = new LocalCSVDataImporter("src/data/peps_names_1k.csv");
        DataImporter localCSVImporter2 = new LocalCSVDataImporter("src/data/peps_names_1k.csv");
        List<Nom> listeDeNoms1 = localCSVImporter1.importData();
        List<Nom> listeDeNoms2 = localCSVImporter2.importData();
        Selectionneur<List<Nom>> selectionneur = new SelectionneurSimple(0.9);
        Generateur generateur = new GenerateurDeCandidatsSimple();
//        System.out.println (map.indexer(listeDeNoms));
        MoteurDeRecherche moteur = new MoteurDeRecherche(generateur, new ComparateurDeNomSoph(),selectionneur);
        List<Pretraiteur> pretraiteurs = new ArrayList<Pretraiteur>();
        pretraiteurs.add(new NettoyeurDeListe());
        pretraiteurs.add(new TransformateurMinuscules());
        moteur.setPretraiteurs(pretraiteurs) ;

        long startTime = System.nanoTime();  // Temps de départ
        List<Nom> resultats = moteur.comparer(listeDeNoms1,listeDeNoms2);
        long endTime = System.nanoTime();    // Temps de fin

        long duration = endTime - startTime;
        long durationInMillis = duration / 1000000;
        long durationInSeconds = duration / 1000000000;

        System.out.println("liste commune:");
        for (Nom nom : resultats){
            System.out.println("id = "+ nom.getId() +", nom =" +nom.getNomOriginalString());
        }
        System.out.println("taille de la liste : "+ resultats.size());
        System.out.println("temps d'execution : " + durationInSeconds + " seconds and " +durationInMillis + " ms");



    }

}