public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        // Base Case
        dp[0] = nums[0];
        int maxSoFar = dp[0];

        for (int i = 1; i < n; i++) {
            // Yineleme Bağıntısı: dp[i] = max(nums[i], nums[i] + dp[i-1])
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);

            // Cevabı güncelle
            maxSoFar = Math.max(maxSoFar, dp[i]);
        }

        return maxSoFar;
    }
}
