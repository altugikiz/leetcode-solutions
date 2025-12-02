public class ClimbingStairs {
    public int climbStairs(int n) {
        // 1. Temel Durumlar (Base Cases)
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        // 2. Tabloyu Oluştur (Tabulation)
        int[] dp = new int[n + 1];

        // Bilinen başlangıç değerleri
        dp[1] = 1; // 1. basamağa 1 farklı şekilde çıkılır.
        dp[2] = 2; // 2. basamağa 2 farklı şekilde çıkılır (1+1 veya 2).

        // 3. Bottom-Up Döngüsü
        // 3. basamaktan başlayıp n'e kadar gidiyoruz
        for (int i = 3; i <= n; i++) {
            // Formül: Bir öncekilerin toplamı
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Sonuç n. kutucukta birikmiştir
        return dp[n];
    }
}
