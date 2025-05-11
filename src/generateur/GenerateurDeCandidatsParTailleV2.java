package generateur;

import config.indexeur.*;
import inputs.*;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurDeCandidatsParTailleV2 implements Generateur {
    private Indexeur indexeur  = new Mapper();
    public List<Couple> generer (List<Nom> liste1 , List<Nom> liste2) {
        List<Couple> listeFinale = new ArrayList<>();
        Map<Integer,List<Nom>> resultatIndexage=(Map<Integer, List<Nom>>)indexeur.indexer(liste2);
        for (Nom nom : liste1) {
            int tailleNomBrut = String.join("", nom.getMots()).length();
            for (int cle=(int)(tailleNomBrut * 0.8); cle <= (int) (tailleNomBrut * 1.2); cle++) {
                for (Nom candidat : resultatIndexage.get(cle)) {

                    listeFinale.add(new Couple(nom, candidat));
                }
            }
        }
        return listeFinale;
    }
}
