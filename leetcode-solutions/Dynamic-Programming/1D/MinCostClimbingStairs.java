public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        // dp dizisi: i. basamağa ulaşmanın minimum maliyeti
        // n+1 boyutunda açıyoruz çünkü zirve (n. indeks) dizinin dışındadır.
        int[] dp = new int[n + 1];

        // 1. Temel Durumlar (Base Cases)
        // Soruda 0. veya 1. indeksten başlamak serbesttir.
        // Yani bu basamaklara ulaşmanın maliyeti daha üzerine basmadan 0'dır.
        dp[0] = 0;
        dp[1] = 0;

        // 2. Tabloyu Doldur (Bottom-Up)
        // 2. basamaktan başlayıp zirveye (n) kadar gidiyoruz.
        for (int i = 2; i <= n; i++) {
            // Bir adım geriden gelmenin maliyeti: dp[i-1] + cost[i-1]
            // İki adım geriden gelmenin maliyeti: dp[i-2] + cost[i-2]
            // Hangisi küçükse onu seç!
            dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                             dp[i - 2] + cost[i - 2]);
        }

        // Zirveye ulaşmanın maliyeti
        return dp[n];
    }
}
