public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        // 1. ADIM: Tüm sayıları topla
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 2. ADIM: Eğer toplam tek sayıysa, iki eşit parçaya bölünemez.
        // (Örneğin toplam 11 ise, 5.5 ve 5.5 diye bölemezsin, tam sayı lazım)
        if (sum % 2 != 0) {
            return false;
        }

        // Hedef toplamın yarısı
        int target = sum / 2;

        // 3. ADIM: DP Tablosunu oluştur (boolean dizisi)
        // Varsayılan olarak Java'da hepsi 'false' başlar.
        boolean[] dp = new boolean[target + 1];

        // 4. ADIM: Başlangıç durumu
        // 0 toplamına ulaşmak her zaman mümkündür (hiçbir sayı seçmeyerek).
        dp[0] = true;

        // 5. ADIM: Tabloyu Doldurma (Bottom-Up)
        for (int num : nums) {
            // DİKKAT: Tersten gidiyoruz (Target'tan num'a kadar)
            // Sebebi: Aynı sayıyı aynı turda tekrar kullanmamak için.
            for (int i = target; i >= num; i--) {
                // Mantık: Şu anki toplama (i) zaten ulaşılmış mı?
                // VEYA (i - num) toplamına daha önce ulaşılmış mı?
                if (dp[i - num] == true) {
                    dp[i] = true;
                }
            }
        }

        // 6. ADIM: Hedef kutusu True mu False mu?
        return dp[target];
    }
}
