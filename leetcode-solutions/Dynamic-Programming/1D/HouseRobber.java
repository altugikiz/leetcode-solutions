public class HouseRobber {
    class Solution {
        public int rob(int[] nums) {
            int n = nums.length;

            // Soyulacak ev yoksa (Edge Case)
            if (n == 0)
                return 0;

            // ilk i adet evden max kazanç dp[i] içerisinde tutulur
            // '0 ev' durumunu dp[0] içerisinde tutar
            int[] dp = new int[n + 1];

            // Base Cases:
            dp[0] = 0; // 0 ev varsa, çalınacak para 0'dır
            dp[1] = nums[0]; // Sadece 1 ev varsa, kazanç o evin içindeki paradır

            for (int i = 2; i <= n; i++) {

                // Decision Moment : Soy ya da Soyma
                // A: (dp[i-1]): Şu anki evi (i. evi) SOYMA
                // Max kazanç bir önceki (i-1) eve kadar toplanan para

                // B: (nums[i-1] + dp[i-2]): Şu anki evi SOY
                dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
            }

            // Max kazanç
            return dp[n];
        }
    }
}
