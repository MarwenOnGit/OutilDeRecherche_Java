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
        ComparateurDeNom comparateur = new ComparateurCombine();
//        ComparateurLevenshtein compLev = new ComparateurLevenshtein();
        Pretraiteur pretraiteur1 = new NettoyeurDeListe() ;
        Pretraiteur pretraiteur2 = new TransformateurMinuscules();
       // Nom nom1 = new Nom("marouan bouhmed");
       // Nom nom2 = new Nom("MarOuAn BouHmed");
       // Nom nom3 = new Nom("khaled smiri");
      //  Nom nom4 = new Nom("marouan@##@#@#@$$@$@ bouhmed");

        DataImporter localCSVImporter = new LocalCSVDataImporter("src/data/peps_names_658k.csv");
        List<Nom> listeDeNoms = localCSVImporter.importData();
//        System.out.println("taille de la liste importée" + listeDeNoms.size());
        Selectionneur<List<Nom>> selectionneur = new SelectionneurSimple();
        Generateur generateur = new GenerateurDeCandidatsSimple();
        MoteurDeRecherche moteur = new MoteurDeRecherche(generateur, comparateur, selectionneur);
        List<Pretraiteur> pretraiteurs = new ArrayList<Pretraiteur>();
        pretraiteurs.add(new NettoyeurDeListe());
        pretraiteurs.add(new TransformateurMinuscules());
        moteur.pretraiteurs = pretraiteurs;
        Nom nomToSearch = new Nom("mohamed abdelkader");
//        System.out.println("nom recherché length: " + String.join("", nomToSearch.getMots()).length());
        List<Nom> resultat = moteur.search(nomToSearch, listeDeNoms);
        List<String> resultatEnString  = new ArrayList<String>();
        for (Nom nom : resultat){
            resultatEnString.add(nom.getNomOriginalString());
        }
        System.out.println("nombre de resultats: "+ resultatEnString.size());
        System.out.println(resultatEnString);

}

    }