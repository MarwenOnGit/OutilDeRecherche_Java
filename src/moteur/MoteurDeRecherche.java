package moteur;
import comparateurs.ComparateurCombine;
import comparateurs.ComparateurLevenshtein;

import comparateurs.ComparateurDeChaine;
import config.indexeur.Mapper;
import generateur.*;
import comparateurs.ComparateurDeNom;
import selectionneur.*;
import config.indexeur.Indexeur;
import config.preprocessor.Pretraiteur;
import inputs.*;
import java.util.*;
public class MoteurDeRecherche {
    private ComparateurDeNom comparateurUtilise ;
    private ComparateurDeNom comparateurDeChaine ;
    private Generateur generateur ;
    private Selectionneur selectionneur ;
    public List<Pretraiteur> pretraiteurs ;
    public MoteurDeRecherche(Generateur generateur, ComparateurCombine comparateur , Selectionneur selectionneur  ) {
        this.generateur = generateur ;
        this.comparateurUtilise = comparateur;
        this.selectionneur = selectionneur ;
    }
    public MoteurDeRecherche() {

    }
    public List< Nom > search (Nom cible, List<Nom> listeDeNoms) {
        List<CoupleAvecScore> listCouplesScores = new ArrayList<>();
        List<Nom> cibleList = new ArrayList<>();
        cibleList.add(cible);
//        ComparateurLevenshtein comparateur =(ComparateurLevenshtein) comparateurUtilise ;
        for(Pretraiteur pretraiteur : pretraiteurs){
            pretraiteur.pretraiter(listeDeNoms);
            pretraiteur.pretraiter(cibleList);
        }

        for (Couple couple : generateur.generer ( cibleList, listeDeNoms ) ){
            CoupleAvecScore coupleAvecScore = new CoupleAvecScore(couple,comparateurUtilise.comparerNom(couple.nom1(),couple.nom2()));
            listCouplesScores.add(coupleAvecScore);
        }
        List<Nom> nomsSelectionnes = (List<Nom>)selectionneur.selectionner(listCouplesScores);
        List<Nom> resultat = new ArrayList<>();
        for (Nom nomSelectionne : nomsSelectionnes){
            resultat.add(nomSelectionne);
        }
        return resultat;
    }
    public List<Couple> dedupliquerListe (List<Nom> listeDeNoms) {
        Mapper map = new Mapper();
        Map<Integer,List<Nom>> dictionnaire = new HashMap<>();
        dictionnaire= map.indexer(listeDeNoms);
        List<Couple> premierRes = new ArrayList<>();
        for ( Nom nom : listeDeNoms ){
            List<Nom> listeTest = new ArrayList<>();
            listeTest.add (nom);
            GenerateurDeCandidatsParTaille generateurUtilise = ( GenerateurDeCandidatsParTaille) generateur ;
            premierRes =generateurUtilise.generer(listeTest, dictionnaire.get(nom.getNomEnString().length()));
        }
        List<CoupleAvecScore> listeDeCouplesScores = new ArrayList<>();
        for ( Couple couple : premierRes){
            listeDeCouplesScores.add( new CoupleAvecScore(couple,comparateurUtilise.comparerNom(couple.nom1(),couple.nom2())));
        }
        SelectionneurSimple selectionneurUtilise = ( SelectionneurSimple) selectionneur ;
        return  selectionneurUtilise.selectionnerDedup(listeDeCouplesScores);
    }
    //    public List<Nom> comparer( List<Nom> liste1 , List<Nom> liste2){
    //        Mapper outilDIndexage = new Mapper();
//        Map<Integer, List<Nom>> map1 =outilDIndexage.indexer(liste1);
//        Map<Integer, List<Nom>> map2 =outilDIndexage.indexer(liste2);
//        f)
//
//    }
    public void setComparateurUtilise(ComparateurCombine comparateurUtilise) {
        this.comparateurUtilise = comparateurUtilise;
    }

    public void setGenerateur(Generateur generateur) {
        this.generateur = generateur;
    }
    public void setSelectionneur(Selectionneur selectionneur) {
        this.selectionneur = selectionneur;

    }

    public void setPretraiteurs(List<Pretraiteur> pretraiteurs) {
            this.pretraiteurs = pretraiteurs;
    }
}
