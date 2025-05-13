package generateur;

import config.indexeur.Indexeur;
import config.indexeur.IndexeurParPremiereLettre;
import config.indexeur.Mapper;
import inputs.Couple;
import inputs.Nom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateurDeCandidatsParPremiereLettre implements Generateur{
    private Indexeur<Map<Character,List<Nom>>> indexeur = new IndexeurParPremiereLettre();
    public List<Couple> generer (List<Nom> liste1 , List<Nom> liste2){
        List<Couple> listeFinale = new ArrayList<>();
        Map<Character, List<Nom>> liste2Indexee = indexeur.indexer(liste2);
        for(Nom nom1 : liste1){
            Character premiereLettre = nom1.getNomEnString().charAt(0);
            if(liste2Indexee.containsKey(premiereLettre)){
                for(Nom nom2 : liste2Indexee.get(premiereLettre)){
                    listeFinale.add(new Couple(nom1,nom2));
                }
            }
        }
        return listeFinale;
    }

}
