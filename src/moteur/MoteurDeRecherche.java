package moteur;
import comparateurs.ComparateurCombine;
import comparateurs.ComparateurLevenshtein;

import comparateurs.ComparateurDeChaine;
import generateur.*;
import comparateurs.ComparateurDeNom;
import selectionneur.*;
import config.indexeur.Indexeur;
import config.preprocessor.Pretraiteur;
import inputs.*;
import java.util.*;
public class MoteurDeRecherche {
    private ComparateurDeNom comparateurUtilise ;
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
        ComparateurDeNom comparateur = comparateurUtilise;
        for(Pretraiteur pretraiteur : pretraiteurs){
            pretraiteur.pretraiter(listeDeNoms);
            pretraiteur.pretraiter(cibleList);
        }

        for (Couple couple : generateur.generer ( cibleList, listeDeNoms ) ){
            CoupleAvecScore coupleAvecScore = new CoupleAvecScore(couple,comparateur.comparerNom(couple.nom1(),couple.nom2()));
//            System.out.println(coupleAvecScore);
            listCouplesScores.add(coupleAvecScore);
           // System.out.println(coupleAvecScore);
        }
        List<Nom> nomsSelectionnes = (List<Nom>)selectionneur.selectionner(listCouplesScores);
        List<Nom> resultat = new ArrayList<>();
        for (Nom nomSelectionne : nomsSelectionnes){
            resultat.add(nomSelectionne);
        }
        return resultat;
    }

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
