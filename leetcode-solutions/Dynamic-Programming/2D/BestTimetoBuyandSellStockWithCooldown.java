public class BestTimetoBuyandSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        // 1. Boyut: Günler (0'dan n-1'e kadar)
        // 2. Boyut: Durumlar (0: Hold, 1: Sold, 2: Rest)
        int[][] dp = new int[n][3];

        // BAŞLANGIÇ DURUMLARI (GÜN 0)

        // Durum 0 (Hold): İlk gün hisseyi aldık
        dp[0][0] = -prices[0];

        // Durum 1 (Sold): İlk gün satamayız (mantıken 0, teknik olarak -sonsuz ama 0
        // yeterli)
        dp[0][1] = 0;

        // Durum 2 (Rest): İlk gün hiçbir şey yapmadık
        dp[0][2] = 0;

        // TABLOYU DOLDURMA
        for (int i = 1; i < n; i++) {

            // 1. Sütun: HOLD (Elimde Var)
            // Dün de elimde vardı (dp[i-1][0]) YA DA Dün dinleniyordum bugün aldım
            // (dp[i-1][2] - fiyat)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);

            // 2. Sütun: SOLD (Sattım)
            // Dün elimde vardı, bugün o fiyata sattım.
            dp[i][1] = dp[i - 1][0] + prices[i];

            // 3. Sütun: REST (Dinlen)
            // Dün zaten dinleniyordum (dp[i-1][2]) YA DA Dün satmıştım (dp[i-1][1])
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }

        // SONUÇ:
        // Son gün (n-1) elimizde hisse kalması (dp[n-1][0]) mantıksızdır.
        // Ya satmışızdır (index 1) ya da dinleniyoruzdur (index 2).
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }
}
