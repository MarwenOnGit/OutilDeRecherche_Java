package config.indexeur;
import java.util.* ;
import inputs.Nom ;
public class Mapper implements Indexeur<Map<Integer,List<Nom>>>{
    public Map<Integer, List<Nom>> indexer (List<Nom> input) {
        Map<Integer, List<Nom>> dictionnaire = new HashMap<>();
        for (Nom nom : input) {
//            int taille = nom.getMots().toString().length();
            int taille = String.join("",nom.getMots()).length();
            dictionnaire.computeIfAbsent(taille, k -> new ArrayList<>()).add(nom);
        }
        return dictionnaire;

    }

}



