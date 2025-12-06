public class LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        // 1. Toplam Ağırlığı Bul
        int totalSum = 0;
        for (int s : stones) {
            totalSum += s;
        }

        // 2. Hedef Belirle (Toplamın yarısı)
        // Amacımız bu hedefe en çok yaklaşan toplamı bulmak.
        int target = totalSum / 2;

        // 3. DP Dizisi Oluştur (1 Boyutlu)
        // dp[j] -> j kapasiteli çantaya sığan maksimum ağırlık
        int[] dp = new int[target + 1];

        // 4. Tabloyu Doldur
        for (int stone : stones) { // Her bir taş için (Satır mantığı)

            // DİKKAT: Diziyi TERSTEN (Sağdan Sola) geziyoruz.
            // Sebebi: Aynı taşı aynı turda iki kere kullanmamak için.
            // j >= stone olduğu sürece döngü döner.
            for (int j = target; j >= stone; j--) {

                // Formül:
                // Seçenek A: Taşı alma (dp[j] olduğu gibi kalır)
                // Seçenek B: Taşı al (stone + dp[j - stone])
                // Hangisi daha büyükse onu yaz.

                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }

        // 5. Sonuç
        // dp[target] bize takımın birinin alabileceği max ağırlığı verir (Mesela 11).
        // Diğer takım otomatikman (Total - 11) olur.
        // Fark = (Total - dp[target]) - dp[target]
        // Yani: Total - 2 * dp[target]
        return totalSum - 2 * dp[target];
    }
}
