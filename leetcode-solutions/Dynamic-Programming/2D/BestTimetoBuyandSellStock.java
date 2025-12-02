public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;

        // dp[i][0] -> i. gün sonunda hisse yok
        // dp[i][1] -> i. gün sonunda hisse var
        int[][] dp = new int[n][2];

        // Başlangıç durumu (0. gün)
        dp[0][0] = 0; // Hiçbir şey yapmadık
        dp[0][1] = -prices[0]; // İlk gün hisseyi aldık, bakiyemiz eksiye düştü

        for (int i = 1; i < n; i++) {
            // Bugün hissem yoksa: Ya dün de yoktu ya da bugün sattım
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // Bugün hissem varsa: Ya dün de vardı ya da bugün yeni aldım
            // Not: Tek işlem olduğu için alırken önceki kârı (dp[i-1][0]) kullanamayız,
            // sıfırdan alırız.
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        // Son gün elimizde hisse kalması mantıksız olduğu için dp[n-1][0] sonucumuzdur.
        return dp[n - 1][0];
    }
}
