public class UniquePaths {
    public int uniquePaths(int m, int n) {
        // 1. ADIM: Haritayı (Defteri) Oluştur
        // m satır, n sütunluk bir tablo açıyoruz.
        int[][] dp = new int[m][n];

        // 2. ADIM: Satır ve Sütunları Gez
        for (int i = 0; i < m; i++) { // i: satır numarası
            for (int j = 0; j < n; j++) { // j: sütun numarası

                // 3. ADIM: Başlangıç ve Duvarlar
                // Eğer en üst satırda (i=0) veya en sol sütundaysak (j=0),
                // oraya gelmenin tek bir yolu vardır (düz gitmek).
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                }
                // 4. ADIM: İçerdeki Kareler
                else {
                    // Buraya gelen yollar = Üstteki kutudaki yollar + Soldaki kutudaki yollar
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        // 5. ADIM: Sonucu Ver
        // Sağ alt köşe (m-1, n-1) bizim hedefimiz.
        return dp[m - 1][n - 1];
    }
}
