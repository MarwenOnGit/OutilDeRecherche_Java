package moteur;
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

    public MoteurDeRecherche(Generateur generateur, ComparateurDeNom comparateur , Selectionneur selectionneur  ) {
        this.generateur = generateur ;
        this.comparateurUtilise = comparateur;
        this.selectionneur = selectionneur ;
    }

    public void executerPretraitement(List<Nom> listeDeNoms){
        for(Pretraiteur pretraiteur : pretraiteurs){
            pretraiteur.pretraiter(listeDeNoms);
        }
    }
    public List< Nom > search (Nom cible, List<Nom> listeDeNoms) {
        List<CoupleAvecScore> listCouplesScores = new ArrayList<>();
        List<Nom> cibleList = new ArrayList<>();
        cibleList.add(cible);
//        ComparateurLevenshtein comparateur =(ComparateurLevenshtein) comparateurUtilise ;
        ComparateurDeNom comparateur =comparateurUtilise;

        for (Pretraiteur pretraiteur : pretraiteurs){
            pretraiteur.pretraiter(cibleList);
        }
        for (Couple couple : generateur.generer ( cibleList, listeDeNoms ) ){
            CoupleAvecScore coupleAvecScore = new CoupleAvecScore(couple,comparateur.comparerNom(couple.nom1(),couple.nom2()));
            listCouplesScores.add(coupleAvecScore);
        }
        List<Nom> nomsSelectionnes = (List<Nom>)selectionneur.selectionner(listCouplesScores);
        return nomsSelectionnes;
    }

}
