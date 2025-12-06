public class EditDistance {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // Tablo oluştur
        int[][] dp = new int[n + 1][m + 1];

        // 1. Base Cases

        // İlk sütun: word1'den boş stringe gitmek için hepsini silmeliyiz.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        // İlk satır: boş stringden word2'ye gitmek için hepsini eklemeliyiz.
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        // 2. İçeriyi Doldur
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // Dikkat: String indexleri 0'dan başlar, dp tablosu 1'den.
                // O yüzden charAt(i-1) ve charAt(j-1) kullanıyoruz.
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);

                if (c1 == c2) {
                    // DURUM A: Harfler Aynı
                    // Çaprazdan (sol-üst) olduğu gibi al. Bedava!
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // DURUM B: Harfler Farklı
                    // Sol, Üst ve Çaprazın en küçüğünü bul, 1 ekle.
                    int sol = dp[i][j - 1]; // Ekleme
                    int ust = dp[i - 1][j]; // Silme
                    int capraz = dp[i - 1][j - 1]; // Değiştirme

                    dp[i][j] = 1 + Math.min(sol, Math.min(ust, capraz));
                }
            }
        }

        // Sonuç: Sağ alt köşe
        return dp[n][m];
    }
}
