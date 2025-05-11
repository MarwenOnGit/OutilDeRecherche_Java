package config.preprocessor;

import inputs.Nom;

import java.util.List;
import java.util.regex.Pattern;

public class PretraiteurDeCharSpecial implements Pretraiteur {
    // Precompile pattern once
    private static final Pattern NON_LETTERS = Pattern.compile("[^a-zA-Z]");

    public void pretraiter(List<Nom> listeDeNoms) {
        for (Nom nom : listeDeNoms) {
            String original = nom.getNomEnString();
            if (NON_LETTERS.matcher(original).find()) { // Only process if necessary
                String cleaned = NON_LETTERS.matcher(original).replaceAll(" ");
                nom.setMots(cleaned);
            }
        }
    }

}
