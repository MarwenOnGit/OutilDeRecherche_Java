package generateur;
import inputs.Couple;
import inputs.Nom;
import java.util.ArrayList;
import java.util.List;
public class GenerateurDeCandidatsSimple implements Generateur {
    public List<Couple> generer (List<Nom> liste1  , List<Nom> liste2){
        List<Couple> listeDeTousLesCouples = new ArrayList<>();
        for ( Nom nom1 : liste1){
            for ( Nom nom2 : liste2 ) {
                listeDeTousLesCouples.add( new Couple(nom1, nom2));
            }
        }
        return listeDeTousLesCouples;
    }
}
