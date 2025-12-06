public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        // Başlangıçta maksimum toplamı dizinin ilk elemanı yapıyoruz.
        // 0 yapmamamızın sebebi, dizinin sadece negatif sayılardan oluşma ihtimalidir
        // (Örn: [-1]).
        int maxSub = nums[0];
        int curSum = 0;

        for (int n : nums) {
            // Eğer bir önceki adımdan gelen toplam (curSum) negatifse,
            // onu sıfırla. Çünkü negatif sayı bize yük olur.
            if (curSum < 0) {
                curSum = 0;
            }

            // Şu anki sayıyı toplamımıza ekle
            curSum += n;

            // Hangisi daha büyük? Şu ana kadar bulduğum rekor mu (maxSub),
            // yoksa şu an elimdeki toplam mı (curSum)?
            maxSub = Math.max(maxSub, curSum);
        }

        return maxSub;
    }
}
