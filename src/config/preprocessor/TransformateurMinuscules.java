package config.preprocessor;

import inputs.Nom;

import java.util.ArrayList;
import java.util.List;

public class TransformateurMinuscules implements Pretraiteur{
    public List<Nom> pretraiter ( List<Nom> noms ) {
//        List<Nom> listeTraitee = new ArrayList<Nom>();
        for ( Nom nom : noms ) {
//            List<String> cleanMots = new ArrayList<>();
//            for (String mt :nom.getMots()) {
//                String motTraite = mt.toLowerCase();
//                cleanMots.add(motTraite);
//            }
//            Nom cleanNom = new Nom(cleanMots);
//            listeTraitee.add(cleanNom);
            nom.setMots(nom.getNomEnString().toLowerCase());
        }
        return noms;
    }


}
