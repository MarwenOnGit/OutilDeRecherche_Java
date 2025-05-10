package config.preprocessor;

import inputs.Nom;

import java.util.ArrayList;
import java.util.List;

public class NettoyeurDeListe implements Pretraiteur{
    public void pretraiter ( List<Nom> listeDeNoms ) {
        List<Nom> cleanNomsList =  new ArrayList<>();
        for ( Nom nom : listeDeNoms ) {
            nom.setMots(nom.getNomEnString().replaceAll("[^a-zA-z]", " "));
        }
    }
}
