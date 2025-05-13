package moteur;

import comparateurs.ComparateurCombine;
import comparateurs.ComparateurDeChaine;
import comparateurs.ComparateurDeNom;
import config.preprocessor.Pretraiteur;
import generateur.Generateur;
import inputs.Nom;
import selectionneur.Selectionneur;

import java.util.ArrayList;
import java.util.List;
import moteur.*;
public class Deduplicateur {
    private List<Pretraiteur> pretraiteurs;
    private ComparateurCombine comparateur ;
    private Selectionneur selectionneur ;
    private Generateur generateur ;
//    private MoteurDeRecherche moteur = new MoteurDeRecherche(generateur ,new ComparateurCombine(comparateur), selectionneur );
    private MoteurDeRecherche moteur ;
    public Deduplicateur( List<Pretraiteur> pretraiteurs, ComparateurCombine comparateur , Selectionneur selectionneur ) {
        this.pretraiteurs = pretraiteurs;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur;

    }
    public Deduplicateur( MoteurDeRecherche moteur ) {
        this.moteur = moteur;

    }
    public void dedupliquer ( List<Nom> nomsBruts){
        List<Nom> nomsDoubles = new ArrayList<Nom>();
        for ( Nom nom : nomsBruts){
            nomsBruts.remove(nom);
           List<Nom> temp = moteur.search(nom,nomsBruts);
           nomsDoubles.addAll(temp);
       }
        System.out.println( "Les noms doublants : ");
        for ( Nom nom : nomsDoubles){
            System.out.println( nom );
            System.out.println( nom.getId());
        }

    }
    public Deduplicateur(Generateur generateur , List<Pretraiteur> pretraiteurs ,Selectionneur selectionneur,ComparateurDeNom comparateur ) {
        this.pretraiteurs = pretraiteurs;
//        this.comparateur = comparateur;
        this.selectionneur = selectionneur;
    }
}
