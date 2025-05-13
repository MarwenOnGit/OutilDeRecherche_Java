import config.indexeur.Indexeur;
import config.indexeur.Mapper;
import config.preprocessor.NettoyeurDeListe;
import config.preprocessor.Pretraiteur;
import config.preprocessor.TransformateurMinuscules;
import generateur.Generateur;
import generateur.GenerateurDeCandidatsParTaille;
import generateur.GenerateurDeCandidatsParTailleV2;
import generateur.GenerateurDeCandidatsSimple;
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
public class Main {
    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Mapper map = new Mapper();
        ComparateurDeNom comparateur = new ComparateurCombine(new ComparateurLevenshtein());
//        ComparateurLevenshtein compLev = new ComparateurLevenshtein();
        Pretraiteur pretraiteur1 = new NettoyeurDeListe() ;
        Pretraiteur pretraiteur2 = new TransformateurMinuscules();
       // Nom nom1 = new Nom("marouan bouhmed");
       // Nom nom2 = new Nom("MarOuAn BouHmed");
       // Nom nom3 = new Nom("khaled smiri");
      //  Nom nom4 = new Nom("marouan@##@#@#@$$@$@ bouhmed");

        DataImporter localCSVImporter = new LocalCSVDataImporter("src/data/peps_names_1k.csv");
        List<Nom> listeDeNoms = localCSVImporter.importData();
        Selectionneur<List<Nom>> selectionneur = new SelectionneurDeNPremiers(3);
        Generateur generateur = new GenerateurDeCandidatsParTailleV2();
        System.out.println (map.indexer(listeDeNoms));
//        MoteurDeRecherche moteur = new MoteurDeRecherche(generateur, new ComparateurCombine(new ComparateurLevenshtein()), selectionneur);
        List<Pretraiteur> pretraiteurs = new ArrayList<Pretraiteur>();
        pretraiteurs.add(new NettoyeurDeListe());
        pretraiteurs.add(new TransformateurMinuscules());
//        moteur.setPretraiteurs(pretraiteurs) ;
        Nom nomToSearch = new Nom("mohamed");
//        List<Nom> resultat = moteur.search(nomToSearch, listeDeNoms);
        List<String> resultatEnString  = new ArrayList<String>();
//        for (Nom nom : resultat){
//            resultatEnString.add(nom.getNomOriginalString());
//        }
        System.out.println("nombre de resultats: "+ resultatEnString.size());
        System.out.println(resultatEnString);

    }

}