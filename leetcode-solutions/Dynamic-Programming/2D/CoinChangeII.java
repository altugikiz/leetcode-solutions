public class CoinChangeII {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]: ilk i adet coini kullanarak j tutarını oluşturma sayısı
        // Satır sayısı n+1 çünkü 0. satır "hiç coin yok" durumunu temsil eder.
        // Sütun sayısı amount+1 çünkü 0. sütun "tutar 0" durumunu temsil eder.
        int[][] dp = new int[n + 1][amount + 1];

        // Base Case: Tutar 0 ise, hiçbir şey vermemek 1 yoldur.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // DP Table
        for (int i = 1; i <= n; i++) {
            // i. coinin değeri (dizide i-1. indeks)
            int currentCoin = coins[i - 1];

            for (int j = 1; j <= amount; j++) {
                // Seçenek 1: Bu coini hiç kullanma (üst satırdan al)
                dp[i][j] = dp[i - 1][j];

                // Seçenek 2: Eğer tutar coine yetiyorsa, bu coini kullan
                if (j >= currentCoin) {
                    // Şu anki yollar += coini kullandıktan sonra kalan tutarın yolları
                    dp[i][j] += dp[i][j - currentCoin];
                }
            }
        }

        // Tablonun sağ alt köşesi cevabımızdır
        return dp[n][amount];
    }
}
