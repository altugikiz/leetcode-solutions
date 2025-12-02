public class BestTimetoBuyandSellStockII {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;

        int[][] dp = new int[n][2];

        // Başlangıç durumu
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            // Hisse yok durumu (Satış veya bekleme) - Değişmedi
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // Hisse var durumu (Alış veya bekleme) - KRİTİK DEĞİŞİKLİK BURADA
            // Artık alım yaparken önceki kârımızdan (dp[i-1][0]) maliyeti düşüyoruz.
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }
}
