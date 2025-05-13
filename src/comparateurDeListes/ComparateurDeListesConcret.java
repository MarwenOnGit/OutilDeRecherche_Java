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
        Map<Integer, List<Nom>> liste1Indexee = indexeur.indexer(liste1);
        Map<Integer, List<Nom>> liste2Indexee = indexeur.indexer(liste2);

        Set<Nom> resultatsCommuns = Collections.synchronizedSet(new HashSet<>());

        liste1Indexee.entrySet().parallelStream().forEach(entry -> {
            Integer key = entry.getKey();
            List<Nom> nomsListe1 = entry.getValue();
            List<Nom> nomsListe2 = liste2Indexee.get(key);

            if (nomsListe2 != null) {
                for (Nom nom : nomsListe1) {
                    List<Nom> correspondances = this.moteur.search(nom, nomsListe2);
                    if (correspondances != null) {
                        resultatsCommuns.addAll(correspondances);
                    }
                }
            }
        });

        return new ArrayList<>(resultatsCommuns);
    }


}
