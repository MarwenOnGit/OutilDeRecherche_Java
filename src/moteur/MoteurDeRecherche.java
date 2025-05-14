package moteur;
import comparateurs.ComparateurCombine;
import comparateurs.ComparateurLevenshtein;

import comparateurs.ComparateurDeChaine;
import config.indexeur.IndexeurPrefixe;
import config.indexeur.Mapper;
import config.preprocessor.PretraiteurPhonetiqueSimple;
import config.preprocessor.TransformateurMinuscules;
import generateur.*;
import comparateurs.ComparateurDeNom;
import selectionneur.*;
import config.indexeur.Indexeur;
import config.preprocessor.Pretraiteur;
import inputs.*;
import java.util.*;
public class MoteurDeRecherche {
    private ComparateurDeNom comparateurUtilise ;
    private ComparateurDeNom comparateurDeChaine ;
    private Generateur generateur ;
    private Selectionneur selectionneur ;
    public List<Pretraiteur> pretraiteurs ;
    public MoteurDeRecherche(Generateur generateur, ComparateurCombine comparateur , Selectionneur selectionneur  ) {
        this.generateur = generateur ;
        this.comparateurUtilise = comparateur;
        this.selectionneur = selectionneur ;
    }
    public MoteurDeRecherche() {

    }
    public void executerPretraitement(List<Nom> listeDeNoms){
        for(Pretraiteur pretraiteur : pretraiteurs){
            pretraiteur.pretraiter(listeDeNoms);
        }
    }
    public List< Nom > search (Nom cible, List<Nom> listeDeNoms) {
        List<CoupleAvecScore> listCouplesScores = new ArrayList<>();
        List<Nom> cibleList = new ArrayList<>();
        cibleList.add(cible);
//        ComparateurLevenshtein comparateur =(ComparateurLevenshtein) comparateurUtilise ;
        ComparateurDeNom comparateur =comparateurUtilise;

        for (Pretraiteur pretraiteur : pretraiteurs){
            pretraiteur.pretraiter(cibleList);
        }
        for (Couple couple : generateur.generer ( cibleList, listeDeNoms ) ){
            CoupleAvecScore coupleAvecScore = new CoupleAvecScore(couple,comparateur.comparerNom(couple.nom1(),couple.nom2()));
            listCouplesScores.add(coupleAvecScore);
        }
        List<Nom> nomsSelectionnes = (List<Nom>)selectionneur.selectionner(listCouplesScores);
        return nomsSelectionnes;
    }

    public List<Nom> dedupliquerListe (List<Nom> listeDeNoms) {
        new PretraiteurPhonetiqueSimple().pretraiter(listeDeNoms);

        Indexeur<Map<String, List<Nom>>> indexeur = new IndexeurPrefixe();
        Map<String, List<Nom>> listeIndexee = indexeur.indexer(listeDeNoms);

        Set<Nom> uniques = new HashSet<>();

        for (Map.Entry<String, List<Nom>> entry : listeIndexee.entrySet()) {
            List<Nom> nomsDansGroupe = entry.getValue();

            for (Nom nom : nomsDansGroupe) {
                if (!nom.getNomEnString().isEmpty()) {
                    List<Nom> correspondances = this.search(nom, nomsDansGroupe);

                    if (correspondances == null || correspondances.size() <= 1) {
                            uniques.add(nom);
                    }
                } else {
                    uniques.add(nom);
                }
            }
        }

        return new ArrayList<>(uniques);
    }


    //comparateur de listes
        public List<Nom> comparer( List<Nom> liste1 , List<Nom> liste2){
            this.executerPretraitement(liste1);
            this.executerPretraitement(liste2);

            Indexeur<Map<Integer, List<Nom>>> indexeur = new Mapper();
            Map<Integer, List<Nom>> liste1Indexee = indexeur.indexer(liste1);
            Map<Integer, List<Nom>> liste2Indexee = indexeur.indexer(liste2);

            Set<Nom> resultatsCommuns = new HashSet<>();

            for (Map.Entry<Integer, List<Nom>> entry : liste1Indexee.entrySet()) {
                Integer key = entry.getKey();
                List<Nom> nomsListe1 = entry.getValue();
                List<Nom> nomsListe2 = liste2Indexee.get(key);

                if (nomsListe2 != null) {
                    for (Nom nom : nomsListe1) {
                        if (!nom.getNomEnString().isEmpty()) {
                            List<Nom> correspondances = this.search(nom, nomsListe2);
                            if (correspondances != null) {
                                resultatsCommuns.addAll(correspondances);
                            }
                        } else {
                            resultatsCommuns.add(nom);
                        }
                    }
                }
            }

            return new ArrayList<>(resultatsCommuns);

    }
    public void setComparateur(ComparateurCombine comparateurUtilise) {
        this.comparateurUtilise = comparateurUtilise;
    }

    public void setGenerateur(Generateur generateur) {
        this.generateur = generateur;
    }
    public void setSelectionneur(Selectionneur selectionneur) {
        this.selectionneur = selectionneur;

    }

    public void setPretraiteurs(List<Pretraiteur> pretraiteurs) {
            this.pretraiteurs = pretraiteurs;
    }
}
