package config.preprocessor;
import inputs.Nom;

import java.util.ArrayList;
import java.util.List;
public class PretraiteurSoundex implements Pretraiteur {
    public void pretraiter ( List<Nom> listeDeNoms){
        for ( Nom nom : listeDeNoms ){
        List<String> result = new ArrayList<>();
            for (String mot : nom.getMots()) {
            result.add(soundex(mot));
        }
            nom.setMots(result);

    }
}
    private String soundex(String s){
        if (s == null || s.isEmpty()) return "";

        s = s.toUpperCase();

        char firstLetter = s.charAt(0);


        String mapped = s.replaceAll("[BFPV]", "1")
                .replaceAll("[CGJKQSXZ]", "2")
                .replaceAll("[DT]", "3")
                .replaceAll("L", "4")
                .replaceAll("[MN]", "5")
                .replaceAll("R", "6")
                .replaceAll("[AEIOUYHW]", "");


        StringBuilder cleaned = new StringBuilder();
        char prev = ' ';
        for (char c : mapped.toCharArray()) {
            if (c != prev) {
                cleaned.append(c);
                prev = c;
            }
        }

        String code = firstLetter + cleaned.toString();
        code = code.replaceAll("[^A-Z0-9]", "");  // Sanitize

        // Pad or trim to 4 characters
        return (code + "0000").substring(0, 4);
    }
}
