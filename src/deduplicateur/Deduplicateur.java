package deduplicateur;

import inputs.Nom;

import java.util.List;

public interface Deduplicateur {
    List<Nom> dedupliquer (List<Nom> listeDeNoms);

}
