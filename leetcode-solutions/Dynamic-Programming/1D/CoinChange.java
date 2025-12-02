import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // 1. ADIM: DP tablosunu oluştur
        // İndeksler 0'dan amount'a kadar olmalı, o yüzden +1 ekliyoruz.
        int[] dp = new int[amount + 1];

        // 2. ADIM: Tabloyu "imkansız" bir değerle doldur.
        // Başlangıçta hepsine (amount + 1) diyoruz.
        // Bunu yapmazsak dizideki varsayılan 0'lar, Math.min işlemini bozar.
        Arrays.fill(dp, amount + 1);

        // 3. ADIM: Başlangıç Noktası (Base Case)
        // 0 lirayı oluşturmak için 0 coin gerekir. (Bu kural asla değişmez)
        dp[0] = 0;

        // 4. ADIM: Hesaplama Döngüsü
        // i = 1'den başlatıyoruz. (dp[1]'i elle yazmıyoruz, döngü hesaplasın diye)
        for (int i = 1; i <= amount; i++) {

            // Her bir tutar (i) için elimizdeki tüm coinleri dene
            for (int coin : coins) {

                // Eğer coin tutardan büyükse kullanamayız, kontrol et:
                if (i - coin >= 0) {
                    // Formül: Şu anki değer mi küçük, yoksa (coin kullandığım hal) mi?
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 5. ADIM: Sonucu Döndürme (Daha anlaşılır if-else yapısı ile)
        // Eğer dp[amount] hala başlangıçtaki "imkansız" sayıysa, çözüm bulunamamıştır.
        if (dp[amount] > amount) {
            return -1; // Çözüm yok
        } else {
            return dp[amount]; // Bulunan en küçük coin sayısı
        }
    }
}
