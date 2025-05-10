package moteur;

import generateur.*;
import comparateurs.ComparateurDeNom;
import selectionneur.*;
import config.indexeur.Indexeur;
import config.preprocessor.Pretraiteur;
import inputs.*;
import java.util.*;
public class MoteurDeRecherche {
    private ComparateurDeNom comparateur ;
    private Generateur generateur ;
    private Selectionneur selectionneur ;
    public List<Pretraiteur> pretraiteurs ;
    public MoteurDeRecherche(Generateur generateur, ComparateurDeNom comparateur , Selectionneur selectionneur  ) {
        this.generateur = generateur ;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur ;
    }
    public List< Nom > search (Nom cible, List<Nom> listeDeNoms  ) {
        List< CoupleAvecScore> listCouplesScores = new ArrayList<>();
        List< Nom> cibleList = new ArrayList<>();
        cibleList.add(cible);
        for(Pretraiteur pretraiteur : pretraiteurs){
            pretraiteur.pretraiter(listeDeNoms);
        }

        for (Couple couple : generateur.generer ( cibleList, listeDeNoms ) ){
            CoupleAvecScore coupleAvecScore = new CoupleAvecScore(couple,comparateur.comparerNom(couple.nom1(),couple.nom2()));
            listCouplesScores.add(coupleAvecScore);
            System.out.println(coupleAvecScore);
        }
        List<Nom> nomsSelectionnes = (List<Nom>)selectionneur.selectionner(listCouplesScores);
        List<Nom> resultat = new ArrayList<>();
        for (Nom nomSelectionne : nomsSelectionnes){
            System.out.println("nom original de " + nomSelectionne + " " + nomSelectionne.getNomOriginalString());
            resultat.add(nomSelectionne);
        }

        return resultat;

    }

}
