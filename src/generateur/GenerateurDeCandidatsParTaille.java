package generateur;
import inputs.*;

import java.util.*;
public class GenerateurDeCandidatsParTaille implements Generateur {
    public List<Couple> generer(List<Nom> liste1 , List<Nom> liste2 ){
        List<Couple> listeDeTousLesCouples = new ArrayList<>();
        for (Nom nom1 : liste1) {
            for ( Nom nom2 : liste2) {
                if ( nom1.transformerEnString().length() == nom2.transformerEnString().length()) {
                    listeDeTousLesCouples.add(new Couple(nom1, nom2));
                }
            }
        }
        return listeDeTousLesCouples;
    }}




