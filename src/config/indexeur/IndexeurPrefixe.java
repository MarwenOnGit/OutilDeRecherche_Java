package config.indexeur;

import inputs.Nom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexeurPrefixe implements Indexeur<Map<String,List<Nom>>> {
    public Map<String,List<Nom>> indexer ( List<Nom> listeDeNoms){
        Map<String,List<Nom>> indexeur = new HashMap<String,List<Nom>>();
        for (Nom nom : listeDeNoms) {
            String nomAIndexer= nom.getNomEnString();
            if ( nomAIndexer.length()< 3 ) continue ;
            String prefixe = nomAIndexer.substring(0,3).toLowerCase();
            indexeur.computeIfAbsent(prefixe,k -> new ArrayList<Nom>()).add(nom);

        }
        return indexeur ;
    }
}
