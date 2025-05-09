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
    private List<Pretraiteur> pretraiteurs ;
//    private Indexeur indexeur ;
    public MoteurDeRecherche(Generateur generateur, ComparateurDeNom comparateur , Selectionneur selectionneur  ) {
        this.generateur = generateur ;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur ;
    }
    public List< Nom > search (Nom cible, List<Nom> listeDeNoms  ) {
        List< CoupleAvecScore> listCouplesScores = new ArrayList<>();
        List< Nom> cibleList = new ArrayList<>();
        cibleList.add(cible);
        List<Nom> listePretraitee = new ArrayList<>();
        listePretraitee.addAll(listeDeNoms);

        for(Pretraiteur pretraiteur : pretraiteurs){
            listePretraitee = pretraiteur.pretraiter(listePretraitee);
        }
        for (Couple couple : generateur.generer ( listeDeNoms, cibleList ) ){
            CoupleAvecScore coupleAvecScore = new CoupleAvecScore(couple,comparateur.comparerNom(couple.nom1(),couple.nom2()));
            listCouplesScores.add(coupleAvecScore);

        }

            return (List<Nom>) selectionneur.selectionner(listCouplesScores);

    }

}

