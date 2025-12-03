import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        // Edge Case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];

        // [1,1,1,....,1]
        Arrays.fill(dp, 1);

        int maxLen = 1; // Sonucu tutacak değişken (en az 1'dir)

        // Bottom-Up Döngüsü
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Eğer artan bir yapı varsa
                if (nums[i] > nums[j]) {
                    // dp[i] değerini güncelle:
                    // Ya mevcut değerini koru ya da dp[j] + 1 yap
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Döngü içinde maksimum değeri takip edebiliriz
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}
