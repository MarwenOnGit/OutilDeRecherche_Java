package selectionneur;
import inputs.Couple;
import inputs.CoupleAvecScore;
import inputs.Nom;

import java.util.ArrayList;
import java.util.List;

public class SelectionneurSimple implements Selectionneur<List<Nom>> {
    public static double seuil ;
    public  List<Nom> selectionner(List<CoupleAvecScore> couplesAvecScores) {
        List<Nom> nomsSelectionnes  = new ArrayList<>();
        for ( CoupleAvecScore couple : couplesAvecScores ){
//            System.out.println(couple.score);
            if ( couple.score >= seuil ){
                nomsSelectionnes.add(couple.coupleDeNoms.nom2());
            }
       }
        return nomsSelectionnes;
    }
    public List<Couple> selectionnerDedup(List<CoupleAvecScore> listeCoupleAvecScores) {
        List<Couple> couplesSelectionnes = new ArrayList<>();
        for ( CoupleAvecScore couple : listeCoupleAvecScores){
            couplesSelectionnes.add(couple.coupleDeNoms);
        }
        return couplesSelectionnes;
    }
    public SelectionneurSimple(double seuil) {
        this.seuil = seuil;
    }

}

