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
//        Map<Integer, List<Nom>> resultatIndexage = new HashMap<Integer,List<Nom>>();
        Map<Integer,List<Nom>> resultatIndexage=(Map<Integer, List<Nom>>)indexeur.indexer(liste2);
        for (Nom nom : liste1) {
            for (int cle=(int)(String.join("",nom.getMots()).length() * 0.8); cle <= (int) (String.join("",nom.getMots()).length() * 1.2); cle++) {
//                System.out.println("{generateur} cle : " + cle);
                for (Nom candidat : resultatIndexage.get(cle)) {
//                    if(cle == 23 ){
//                        System.out.println("nom de longuer 23 : " + candidat.getNomEnString());
//                    }
                    listeFinale.add(new Couple(nom, candidat));
                }
            }
        }
//        System.out.println("liste generée : " + listeFinale);
        return listeFinale;
    }

}
