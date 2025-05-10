package config.preprocessor;

import inputs.Nom;

import java.util.ArrayList;
import java.util.List;

public class NettoyeurDeListe implements Pretraiteur{
    public void pretraiter ( List<Nom> listeDeNoms ) {
        List<Nom> cleanNomsList =  new ArrayList<>();
        for ( Nom nom : listeDeNoms ) {
            List<String> cleanWords = new ArrayList<>();
            for( String mt : nom.getMots()) {
                String motTraite = mt.replaceAll("[^a-zA-Z]", "");
                cleanWords.add(motTraite);
            }
            nom.setMots(cleanWords);
            System.out.println("Nom apres traitement Netoyeur de liste: " + nom );
        }
    }
}
