package generateur;

import config.indexeur.Indexeur;
import config.indexeur.IndexeurPrefixe;
import config.indexeur.Mapper;
import inputs.Couple;
import inputs.Nom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateurCandidatsPhonetique {
    private Indexeur<Map<String,List<Nom>>> indexeur  = new IndexeurPrefixe();
    public List<Couple> generer (List<Nom> liste1 , List<Nom> liste2) {
        List<Couple> listeFinale = new ArrayList<>();
        Map<String,List<Nom>> resultatIndexage= indexeur.indexer(liste2);
        for (Nom nom : liste1) {

        }
        return listeFinale;
    }
}
