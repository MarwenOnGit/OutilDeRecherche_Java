package config.preprocessor;

import inputs.Nom;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class PretraiteurDeCharSpecial implements Pretraiteur {
    private static final Pattern NON_LETTERS = Pattern.compile("[^a-zA-Z]");
    private static final Pattern DIACRITICS = Pattern.compile("\\p{M}");

    public void pretraiter(List<Nom> listeDeNoms) {
        for (Nom nom : listeDeNoms) {
            String original = nom.getNomEnString();
            if (original == null) continue;
            String normalized = Normalizer.normalize(original, Normalizer.Form.NFD);
//            String withoutAccents = DIACRITICS.matcher(normalized).replaceAll("");
            String cleaned = NON_LETTERS.matcher(normalized).replaceAll(" ");

            nom.setMots(cleaned);
        }
    }
}
