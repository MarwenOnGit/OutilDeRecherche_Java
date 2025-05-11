package comparateurs;
import inputs.Nom;

public class ComparateurDeNomSoph implements ComparateurDeNom {
   private ComparateurDeChaine comparateurDeChaine;

public double comparerNom ( Nom nom1 , Nom nom2){
    double score = 0.0 ;
    String nom1String = nom1.getNomEnString();
    String nom2String = nom2.getNomEnString();
    int len1 = nom1String.length();
    int len2 = nom2String.length();
    int minLen = Math.min ( len1 , len2 );
    for( int i=0 ; i<minLen; i++ ) {
        if (nom1String.charAt(i) == nom2String.charAt(i) ) {
            score += 1;
        }
    }
    int originalMinLen = Math.min(
            nom1.getNomOriginalString().length(),
            nom2.getNomOriginalString().length()
    );
    return originalMinLen == 0 ? 0.0 : score / originalMinLen;
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

