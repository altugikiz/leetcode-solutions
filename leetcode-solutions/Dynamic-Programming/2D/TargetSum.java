public class TargetSum {
    public int targetSum(int[] nums, int target) {
        // 1. ADIM: Matematiksel Hazırlık
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Eğer toplam hedef sayıdan küçükse veya (total + target) tek sayıysa
        // bu iş imkansızdır. (Matematiksel kural)
        if (totalSum < Math.abs(target) || (totalSum + target) % 2 != 0) {
            return 0;
        }

        // Bize lazım olan "Artı Kutusu"nun toplamı (P)
        // Formül: (Toplam + Hedef) / 2
        int S = (totalSum + target) / 2;
        int n = nums.length;

        // 2. ADIM: Tabloyu Oluştur (2 Boyutlu)
        // Satırlar (n+1): 0 karttan n karta kadar
        // Sütunlar (S+1): 0 puandan S puana kadar
        int[][] dp = new int[n + 1][S + 1];

        // 3. ADIM: Başlangıç (Base Case)
        // "Hiç kart yokken (i=0), 0 puanı (j=0) yapmanın 1 yolu vardır."
        dp[0][0] = 1;

        // 4. ADIM: Tabloyu Doldurma
        for (int i = 1; i <= n; i++) {
            // Elimizdeki kartın değeri (Diziler 0'dan başlar o yüzden i-1)
            int kart = nums[i - 1];

            for (int j = 0; j <= S; j++) {

                // Seçenek A: KARTI KULLANMA (Üstteki kutuya bak)
                // dp[i-1][j] -> Bir üst satırdaki değer
                dp[i][j] = dp[i - 1][j];

                // Seçenek B: KARTI KULLAN (Varsa sol-üst çapraza bak)
                // Sadece hedef puan (j), karttan büyük veya eşitse kullanabiliriz.
                if (j >= kart) {
                    // dp[i-1][j - kart] -> Kart kadar geriye (sola) git ve üst satıra bak
                    dp[i][j] += dp[i - 1][j - kart];
                }
            }
        }

        // 5. ADIM: Sonuç
        // Tablonun en sağ alt köşesi cevabımızdır.
        return dp[n][S];
    }
}
