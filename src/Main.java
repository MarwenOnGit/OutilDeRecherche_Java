import config.preprocessor.NettoyeurDeListe;
import config.preprocessor.Pretraiteur;
import config.preprocessor.TransformateurMinuscules;
import generateur.Generateur;
import generateur.GenerateurDeCandidatsParTaille;
import generateur.GenerateurDeCandidatsSimple;
import selectionneur.Selectionneur;
import selectionneur.SelectionneurDeNPremiers;
import selectionneur.SelectionneurSimple;
import comparateurs.*;
import inputs.*;
import moteur.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ComparateurDeNomSoph comparateur = new ComparateurDeNomSoph();
        ComparateurLevenshtein compLev = new ComparateurLevenshtein();
        Pretraiteur pretraiteur1 = new NettoyeurDeListe() ;
        Pretraiteur pretraiteur2 = new TransformateurMinuscules();
        Nom nom1 = new Nom();
        Nom nom2 = new Nom();
        Nom nom3 = new Nom();
        Nom nom4 = new Nom();
        List<String> mots1 = new ArrayList<>();
        List<String> mots2 = new ArrayList<>();
        List< String> mots3 = new ArrayList<>();
        List< String> mots4 = new ArrayList<>();
        List< String> mots0= new ArrayList<>();
        mots1.add("marouan");
        mots1.add("bouhmed");
        mots2.add("Marouan");
        mots2.add("bouhmed");
        mots3.add("khaled");
        mots3.add("smiri");
        mots4.add("marouane@##@#@#@$$@$@");
        mots0.add("ben hmed ");
        nom1.setMots(mots1);
        nom2.setMots(mots2);
        nom2.setMots(mots2);
        nom3.setMots(mots3);
        nom4.setMots(mots4);
        List<Nom> listeDeNoms = new ArrayList<>();
        listeDeNoms.add(nom1);
        listeDeNoms.add(nom2);
        listeDeNoms.add(nom3);
        listeDeNoms.add(nom4);
        Nom nom0 = new Nom();
        listeDeNoms.add(nom1);
        Selectionneur<List<Nom>> selectionneur = new SelectionneurSimple();
        Generateur generateur = new GenerateurDeCandidatsSimple();
        MoteurDeRecherche moteur = new MoteurDeRecherche(generateur,comparateur, selectionneur);
        List<Pretraiteur> pretraiteurs = new ArrayList<Pretraiteur>();
        pretraiteurs.add(new NettoyeurDeListe());
        pretraiteurs.add(new TransformateurMinuscules());
        moteur.pretraiteurs = pretraiteurs;
//        moteur.pret
//        System.out.println(listeDeNoms);
//        listeDeNoms= pretraiteur2.pretraiter(pretraiteur1.pretraiter(listeDeNoms));
//        System.out.println(listeDeNoms);
//        System.out.println(comparateur.comparerNom(listeDeNoms.get(0),listeDeNoms.get(1)));
//        System.out.println(compLev.comparer(listeDeNoms.get(2).transformerEnString(), listeDeNoms.get(1).transformerEnString()));
        System.out.println(moteur.search(nom1, listeDeNoms));
}

    }