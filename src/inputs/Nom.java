package inputs;
import java.lang.reflect.Array;
import java.util.*;
public class Nom {
    private final String nomOriginal;
    private List<String> mots;
    private  String id ;
//    public Nom() {
//        mots = new ArrayList<>();
//        nomOriginal = new ArrayList<String>();
//
//    }


    public Nom (String nom) {
        this.id = "";
        this.mots = new ArrayList<String>(Arrays.asList(nom.split(" ")));
        this.nomOriginal = nom;
    }

    public Nom (String id, String nom){
        this.id = id;
        this.mots = Arrays.asList(nom.split(" "));
        this.nomOriginal = nom;
    }
    public List<String> getMots() {
        return mots;
    }
    public String getNomOriginalString(){
        return String.join(" ",nomOriginal);
    }

    public void setMots(String nom) {
        this.mots = new ArrayList<String>(Arrays.asList(nom.split(" ")));
    }

    public void setMots(List<String> mots) {
//        List<String> nomsToAdd = new ArrayList<>();
//        for (String mot :mots){
//            nomsToAdd.add(mot.trim());
//        }
//        this.mots = nomsToAdd;
        this.mots = mots;
    }

    public boolean equals(Object o) {
        Nom nom = (Nom) o;
        return id.equals(nom.id);
    }
    public String getNomEnString() {
        return String.join(" ", mots);
    }
    public String toString (){
        return String.join(" ", mots);
    }

    public void addMot(String mot) {mots.add(mot);}
}
