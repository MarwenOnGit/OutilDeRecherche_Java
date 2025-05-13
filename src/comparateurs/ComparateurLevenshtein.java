package comparateurs;

public class ComparateurLevenshtein implements ComparateurDeChaine  {
    public double comparer(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) {
            return 0.0 ;
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
        int maxLength = Math.max(s1.length(), s2.length());
        return 1.0 - (double) dp[s1.length()][s2.length()] /maxLength;
    }
}
