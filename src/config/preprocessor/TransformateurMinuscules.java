package config.preprocessor;

import inputs.Nom;

import java.util.ArrayList;
import java.util.List;

public class TransformateurMinuscules implements Pretraiteur{
    public void pretraiter ( List<Nom> noms ) {
        for ( Nom nom : noms ) {
            nom.setMots(nom.getNomEnString().toLowerCase());
        }
    }
}
