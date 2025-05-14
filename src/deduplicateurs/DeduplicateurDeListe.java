package deduplicateurs;

import inputs.Nom;

import java.util.List;

public interface DeduplicateurDeListe {
    List<Nom> dedupliquerListe(List<Nom> liste);
}
