package deduplicateur;

import config.indexeur.Indexeur;
import config.indexeur.IndexeurPrefixe;
import config.indexeur.Mapper;
import inputs.Nom;
import moteur.MoteurDeRecherche;

import java.util.List;
import java.util.Map;

public class DeduplicateurConcret implements Deduplicateur {
    private MoteurDeRecherche moteur;
    public List<Nom> dedupliquer (List<Nom> noms) {
        this.moteur.executerPretraitement(noms);
        Indexeur<Map<String, List<Nom>>> indexeur = new IndexeurPrefixe();
        Map<String, List<Nom>> listeIndexee = indexeur.indexer(noms);
        for ( Nom nom : noms ) {
            if()
        }

    }
    public DeduplicateurConcret(MoteurDeRecherche moteur) {
        this.moteur = moteur;
    }
}
