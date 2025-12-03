public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        // İlk elemanı başlangıç değeri olarak alıyoruz
        int currentMax = nums[0];
        int globalMax = nums[0];

        // Döngüye 2. elemandan (index 1) başlıyoruz
        for (int i = 1; i < nums.length; i++) {
            // Karar Anı: Ya yeni bir alt dizi başlat (nums[i])
            // ya da önceki toplama ekle (nums[i] + currentMax)
            currentMax = Math.max(nums[i], nums[i] + currentMax);

            // Genel maksimumu güncelle
            if (currentMax > globalMax) {
                globalMax = currentMax;
            }
        }

        return globalMax;
    }
}
