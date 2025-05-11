import config.preprocessor.PretraiteurDeCharSpecial;
import config.preprocessor.Pretraiteur;
import config.preprocessor.TransformateurMinuscules;
import generateur.Generateur;
import generateur.GenerateurDeCandidatsParTailleV2;
import importer.DataImporter;
import importer.LocalCSVDataImporter;
import selectionneur.Selectionneur;
import selectionneur.SelectionneurSimple;
import comparateurs.*;
import inputs.*;
import moteur.*;


import java.util.*;
import java.util.Scanner ;

public class Main {
    public static void main(String[] args) {


        /////////////// Data importing
        DataImporter localCSVImporter = new LocalCSVDataImporter("src/data/peps_names_658k.csv");
        List<Nom> listeDeNoms = localCSVImporter.importData();
        /////////////////////////////




        /////////// engine pieces
        Selectionneur<List<Nom>> selectionneur = new SelectionneurSimple();
        Generateur generateur = new GenerateurDeCandidatsParTailleV2();
        ComparateurDeNom comparateur = new ComparateurDeNomCombine();
        /// /////////////////////


        /// //////////// engine init
        MoteurDeRecherche moteur = new MoteurDeRecherche(generateur, comparateur, selectionneur);
        /// //////////////////


        //////// Preprocessors
        Pretraiteur pretraiteur1 = new PretraiteurDeCharSpecial() ;
        Pretraiteur pretraiteur2 = new TransformateurMinuscules();
        List<Pretraiteur> pretraiteurs = new ArrayList<Pretraiteur>();
        pretraiteurs.add(new PretraiteurDeCharSpecial());
        pretraiteurs.add(new TransformateurMinuscules());
        moteur.pretraiteurs = pretraiteurs;
//        moteur.pretraiteurs = new ArrayList<>(); //DEBUG
        moteur.executerPretraitement(listeDeNoms); // execution du pretraitement

        /////////////////////////////////////////




        /////////// search input
        System.out.print("Enter the name you wanna search :");
        Scanner scannerObj = new Scanner(System.in);
        Nom nomToSearch = new Nom(scannerObj.nextLine());
        ////////////////////////


        //application timing start
        long startTime = System.currentTimeMillis();
        ////////////////////////////////////////


        /////////// search execution
        List<Nom> resultat = moteur.search(nomToSearch, listeDeNoms);
        List<String> resultatEnString  = new ArrayList<String>();
        for (Nom nom : resultat){
            resultatEnString.add(nom.getNomOriginalString());
        }
        ///////////////////////////////////


        // applcation timing end
        long endTime = System.currentTimeMillis();
        /////////////////////////////////////////////////


        ///////////////////// results and data
        System.out.println("nom recherché length: " + String.join("", nomToSearch.getMots()).length());
        System.out.println("nombre de resultats: "+ resultatEnString.size());
        System.out.println(resultatEnString);
        System.out.println("Exection time : " + (int)(endTime - startTime)/1000 + " seconds and " +(endTime - startTime) % 1000 + " ms");
        ///////////////////////////////////////////////


    }

}