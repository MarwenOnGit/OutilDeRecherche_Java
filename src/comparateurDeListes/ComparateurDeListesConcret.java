package comparateurDeListes;

import config.indexeur.Indexeur;
import config.indexeur.Mapper;
import inputs.Nom;
import moteur.MoteurDeRecherche;

import java.util.*;

public class ComparateurDeListesConcret implements ComparateurDeListes {
    MoteurDeRecherche moteur;
    public ComparateurDeListesConcret(MoteurDeRecherche moteur){
        this.moteur = moteur;
    }
    public List<Nom> comparerListes(List<Nom> liste1, List<Nom> liste2){
        this.moteur.executerPretraitement(liste1);
        this.moteur.executerPretraitement(liste2);
        Indexeur<Map<Integer, List<Nom>>> indexeur = new Mapper();
        Map<Integer, List<Nom>>  liste1Indexee = indexeur.indexer(liste1);
        Map<Integer, List<Nom>> liste2Indexee = indexeur.indexer(liste2);

        List<Nom> resultatsCommuns = new ArrayList<>();

        for (Integer taille : liste1Indexee.keySet()) {
            if (liste2Indexee.containsKey(taille)) {
                List<Nom> noms1 = liste1Indexee.get(taille);
                List<Nom> noms2 = liste2Indexee.get(taille);

                Set<Nom> setNoms2 = new HashSet<>(noms2);

                for (Nom nom1 : noms1) {
                    if (setNoms2.contains(nom1)) {
                        resultatsCommuns.add(nom1);
                    }
                }
            }
        }

        return resultatsCommuns;
    }

}
