public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums.length == 0)
            return 0;

        int n = nums.length;

        // Tabloları oluşturuyoruz (Bottom-Up mantığı)
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];

        // 1. ADIM: Başlangıç Noktası (Base Case)
        // İlk eleman için seçenek yoktur, kendisidir.
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        int result = nums[0];

        // 2. ADIM: Tabloyu Doldurma (Bottom-Up)
        for (int i = 1; i < n; i++) {
            int current = nums[i];

            // Üç ihtimali karşılaştırıyoruz:
            // A) Sayının kendisi (öncekileri çöpe atıp yeni başlarsak)
            // B) Kendisi * Öncekinin Maksimumu (pozitif * pozitif)
            // C) Kendisi * Öncekinin Minimumu (negatif * negatif = süper pozitif)

            // Java'da Math.max sadece iki sayı alır, o yüzden iç içe yazarız:
            int tempMax = Math.max(current, Math.max(current * dpMax[i - 1], current * dpMin[i - 1]));
            int tempMin = Math.min(current, Math.min(current * dpMax[i - 1], current * dpMin[i - 1]));

            // Hesaplanan değerleri tabloya yaz
            dpMax[i] = tempMax;
            dpMin[i] = tempMin;

            // Global rekoru güncelle
            result = Math.max(result, dpMax[i]);
        }

        return result;
    }
}
