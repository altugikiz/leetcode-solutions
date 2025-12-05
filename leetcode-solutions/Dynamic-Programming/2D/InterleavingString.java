public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        // 1. Basit Kontrol: Uzunluklar tutmuyorsa imkansızdır.
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int n = s1.length();
        int m = s2.length();

        // DP Tablosu (Varsayılan hepsi false)
        boolean[][] dp = new boolean[n + 1][m + 1];

        // Başlangıç noktası: Hiçbir şey yapmamak geçerli bir durumdur.
        dp[0][0] = true;

        // Tabloyu dolduruyoruz
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {

                // Zaten başlangıç noktasını (0,0) elle true yaptık, onu atla.
                if (i == 0 && j == 0)
                    continue;

                // s3'te hangi harfe bakıyoruz? (i + j - 1)
                // Robotun şu an s3 üzerinde ulaşmaya çalıştığı karakter indexi.
                int k = i + j - 1;

                // SEÇENEK 1: Üstten Gelmek (s1'den harf alarak)
                // i > 0 olmalı (s1'den alacak harf var mı?)
                // dp[i-1][j] true olmalı (yukarısı sağlam yol mu?)
                // s1'in şu anki harfi (i-1), s3'ün harfine (k) eşit mi?
                if (i > 0 && dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(k)) {
                    dp[i][j] = true;
                }

                // SEÇENEK 2: Soldan Gelmek (s2'den harf alarak)
                // j > 0 olmalı (s2'den alacak harf var mı?)
                // dp[i][j-1] true olmalı (sol taraf sağlam yol mu?)
                // s2'nin şu anki harfi (j-1), s3'ün harfine (k) eşit mi?
                // Not: Yukarıda dp[i][j] zaten true olduysa tekrar bakmaya gerek yok (||
                // mantığı)
                if (j > 0 && dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(k)) {
                    dp[i][j] = true;
                }
            }
        }

        // En sağ alt köşe sonucu verir
        return dp[n][m];
    }
}
