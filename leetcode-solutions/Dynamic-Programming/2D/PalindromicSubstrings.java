public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        // Boyut (len) üzerinden döngü
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // 1. Karakterler eşit mi?
                // 2. İç kısım (dp[i+1][j-1]) palindrom mu? (veya uzunluk < 3 mü?)
                if (s.charAt(i) == s.charAt(j)) {
                    // len == 1 (örn: "a") veya len == 2 (örn: "aa") ise direkt true
                    // len > 2 ise iç kısma bakılır
                    if (len <= 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++; // Palindrom bulduk, sayacı artır
                    }
                }
            }
        }
        return count;
    }
}
