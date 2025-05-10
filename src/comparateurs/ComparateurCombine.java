package comparateurs;

import inputs.Nom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
public class ComparateurCombine implements ComparateurDeNom{
    public double comparerNom ( Nom nom1 , Nom nom2 ){
        if(nom2.getMots().size() == 0 || nom1.getMots().size() == 0){
            return 0.0;
        }
        ComparateurDeChaine comparateurLev = new ComparateurJaroWinkler();
        List<Double> listeFinaleDeScore = new ArrayList<>();
        for ( String n1 : nom1.getMots()){
        List<Double> listeDeScores = new ArrayList<Double>();
            for ( String n2 : nom2.getMots() ){
                listeDeScores.add (comparateurLev.comparer(n1,n2));
            }
//            System.out.println(listeDeScores);
            listeFinaleDeScore.add (Collections.max(listeDeScores));
        }
        double valFinale = 0.0 ;
        for ( double d : listeFinaleDeScore ){
            valFinale += d;
        }
        return (double) valFinale/listeFinaleDeScore.size();

    }

}
