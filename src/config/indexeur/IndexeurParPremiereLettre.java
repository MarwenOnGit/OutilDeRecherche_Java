package config.indexeur;

import inputs.Nom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexeurParPremiereLettre implements Indexeur<Map<Character, List<Nom>>> {

        public Map<Character, List<Nom>> indexer (List<Nom> input) {
            Map<Character, List<Nom>> dictionnaire = new HashMap<>();
            for (Nom nom : input) {
                String nomEnString = nom.getNomEnString();
                if(!nomEnString.isEmpty()){
                    dictionnaire.computeIfAbsent(nomEnString.charAt(0),k -> new ArrayList<>()).add(nom);
                }
            }
            return dictionnaire;

        }

}
