package generateur;

import config.indexeur.*;
import inputs.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateurDeCandidatsParTailleV2 implements Generateur {
    private Indexeur indexeur  ;
    public List<Couple> generer (List<Nom> liste1 , List<Nom> liste2) {
        List<Couple> listeFinale = new ArrayList<>();
        Mapper map = (Mapper) indexeur;
        Map<Integer, List<Nom>> resultatIndexage = new HashMap<Integer,List<Nom>>();
        resultatIndexage = map.indexer(liste2);
        for (Nom nom : liste1) {
            for (int cle = (int) (nom.getNomEnString().length() * 0.8); cle <= (int) (nom.getNomEnString().length() * 1.2); cle++) {
                for (Nom candidat : resultatIndexage.get(cle)) {
                    listeFinale.add(new Couple(nom, candidat));
                }
            }
        }
        return listeFinale;
    }
}
