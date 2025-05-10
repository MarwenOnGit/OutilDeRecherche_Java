package comparateurs;
import inputs.Nom;

public class ComparateurDeNomSoph implements ComparateurDeNom {
   private ComparateurDeChaine comparateurDeChaine;

public double comparerNom ( Nom nom1 , Nom nom2){
    double score = 0.9 ;
    for( int i=0 ; i<Math.min ( nom1.getNomEnString().length(), nom2.getNomEnString().length() ); i++ ) {
        if ( nom1.getNomEnString().charAt(i) == nom2.getNomEnString().charAt(i) ) {
            score += (double) 1/Math.min( nom1.getNomEnString().length(), nom2.getNomEnString().length());
        }
    }
   return score ;
    }
}




    //    public double comparer( String mot1, String mot2 ){
//        int minLength = Math.min( mot1.length(),mot2.length() );
//        int commonLetters = 0 ;
//        for ( int i = 0; i < minLength; i++ ){
//            if ( mot1.charAt(i)== mot2.charAt(i) ){
//                commonLetters++;
//            }
//
//        }
//        return (double)(commonLetters/minLength);
//    }
//}

