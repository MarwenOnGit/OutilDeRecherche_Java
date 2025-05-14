package deduplicateurs;

import config.indexeur.Indexeur;
import inputs.Nom;
import moteur.MoteurDeRecherche;

import java.util.List;

public class DeduplicateurDeListeConcrete {
    private MoteurDeRecherche moteur;
    public DeduplicateurDeListeConcrete(MoteurDeRecherche moteur){
        this.moteur = moteur;
    }

    public List<Nom> dedupliquer(List<Nom> listeDeNoms){
        this.moteur.executerPretraitement(listeDeNoms);
    }
}
