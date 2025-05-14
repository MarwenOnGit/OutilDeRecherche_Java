package config.preprocessor;

import inputs.Nom;

import java.util.List;

public class PretraiteurPhonetiqueSimple implements Pretraiteur{
    public void  pretraiter(List<Nom> noms) {
        for(Nom nom: noms){
            // Convertir en minuscules
            String nomStr = nom.getNomEnString().toLowerCase();

            // Suppression des caractères non pertinents (voyelles et lettres muettes)
            nomStr = nomStr.replaceAll("[aeiouy]", ""); // Supprimer les voyelles
            nomStr = nomStr.replaceAll("[hw]", "");     // Supprimer H et W
            nomStr = nomStr.replaceAll("[^a-z]", "");    // Supprimer tout caractère non alphabetique

            // Simplification de certains groupes de lettres
            nomStr = nomStr.replaceAll("ph", "f");      // Remplacer 'ph' par 'f'
            nomStr = nomStr.replaceAll("ch", "k");      // Remplacer 'ch' par 'k'
            nom.setMots(nomStr);
        }
    }
}
