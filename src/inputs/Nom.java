package inputs;
import java.util.*;
public class Nom {
    private List<String> mots;
    private  String id ;
    public int index;
    public Nom() {
        mots = new ArrayList<>();
    }
    public Nom (List<String> mots) {
        this.mots = mots;
    }
    public List<String> getMots() {
        return mots;
    }

    public void setMots(List<String> mots) {
        this.mots = mots;
    }

    public boolean equals(Object o) {
        Nom nom = (Nom) o;
        return id.equals(nom.id);
    }
    public String transformerEnString() {
        return String.join("", mots);
    }
    public String toString (){
        return String.join(" ", mots);
    }

    public void addMot(String mot) {mots.add(mot);}
}
