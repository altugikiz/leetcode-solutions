public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // dp[i][j] -> text1[0...i-1] ve text2[0...j-1] arasındaki LCS
        // +1 boyutu "boş string" (base case) kolaylığı sağlar.
        int[][] dp = new int[m + 1][n + 1];

        // i ve j, 1'den başlar çünkü 0. indeks "boş string"i temsil eder.
        // Bu yüzden karakter kontrolü yaparken text.charAt(i-1) kullanırız.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // Karakterler eşleşiyorsa:
                // Sol-üst köşedeki değere (önceki karakterlerin LCS'si) 1 ekle.
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                // Eşleşmiyorsa:
                // Ya üstten (dp[i-1][j]) ya da soldan (dp[i][j-1]) gelen en büyük değeri al.
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Tablonun en sağ alt köşesi cevaptır.
        return dp[m][n];
    }
}
