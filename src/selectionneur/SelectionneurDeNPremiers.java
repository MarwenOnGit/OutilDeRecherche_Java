package selectionneur;

import inputs.CoupleAvecScore;
import inputs.Nom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectionneurDeNPremiers implements Selectionneur<List<Nom>> {
    private int nombreDeResultats;
    public SelectionneurDeNPremiers(int nombreDeResultats){
        this.nombreDeResultats = nombreDeResultats;
    }
     public List<Nom> selectionner(List<CoupleAvecScore> couplesAvecScores){
        Collections.sort(couplesAvecScores);
        Collections.reverse(couplesAvecScores);
        List<Nom> resultatsSelectionnes= new ArrayList<Nom>();
        for(CoupleAvecScore couple : couplesAvecScores.subList(0,this.nombreDeResultats)){
            resultatsSelectionnes.add(couple.coupleDeNoms.nom2());
        }
        return resultatsSelectionnes;
     }
}
