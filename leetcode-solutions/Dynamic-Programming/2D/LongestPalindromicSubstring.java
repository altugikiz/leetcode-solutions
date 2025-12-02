public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0)
            return "";

        // DP Tablosu: Varsayılan değerler false'tur.
        boolean[][] dp = new boolean[n][n];

        int start = 0; // En uzun palindromun başlangıcı
        int maxLen = 1; // En uzun palindromun uzunluğu

        // ADIM 1: Tek harfli palindromlar (Köşegen)
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // ADIM 2: İki harfli palindromlar (Özel durum kontrolü)
        // Bunu ayrı yapıyoruz çünkü "içerisi" diye bir şey yok.
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }

        // ADIM 3: 3 ve daha fazla harfli palindromlar
        // len: şu an kontrol ettiğimiz uzunluk
        for (int len = 3; len <= n; len++) {
            // i: başlangıç indeksi
            for (int i = 0; i <= n - len; i++) {

                // j: bitiş indeksi (başlangıç + uzunluk - 1)
                int j = i + len - 1;

                // KURAL:
                // 1. Uçtaki harfler (s[i] ve s[j]) eşit mi?
                // 2. Aradaki parça (dp[i+1][j-1]) palindrom mu?
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;

                    // Eğer şu anki uzunluk (len) rekorumuzdan büyükse güncelle
                    if (len > maxLen) {
                        start = i;
                        maxLen = len;
                    }
                }
            }
        }

        // Sonucu kesip döndür
        return s.substring(start, start + maxLen);
    }
}

// Expand Around Center Çözümü

// public String longestPalindrome(String s) {
// if (s == null || s.length() < 1) return "";

// int start = 0;
// int end = 0;

// for (int i = 0; i < s.length(); i++) {
// // 1. Durum: Tek Sayılı Palindrom (örn: "aba", merkez 'b')
// int len1 = expandAroundCenter(s, i, i);

// // 2. Durum: Çift Sayılı Palindrom (örn: "abba", merkez 'bb' arası)
// int len2 = expandAroundCenter(s, i, i + 1);

// // Hangisi daha uzunsa onu al
// int len = Math.max(len1, len2);

// // Eğer bulduğumuz yeni uzunluk, elimizdeki rekoru geçiyorsa güncelle
// if (len > end - start) {
// // Yeni başlangıç ve bitiş noktalarını hesapla
// // Örn: len=3, i=1 (merkez) -> start = 1 - (2)/2 = 0
// start = i - (len - 1) / 2;
// end = i + len / 2;
// }
// }

// // Java'da substring(baslangic, bitis_haric) mantığıyla çalışır
// return s.substring(start, end + 1);
// }

// // Yardımcı Metot: Verilen merkezden sağa ve sola genişle
// private int expandAroundCenter(String s, int left, int right) {
// // Sınırlar içinde kaldığımız ve harfler eşleştiği sürece genişle
// while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
// {
// left--;
// right++;
// }
// // Döngü bittiğinde left ve right birer adım fazladan gitmiş olur.
// // Uzunluk formülü: (right - 1) - (left + 1) + 1 => right - left - 1
// return right - left - 1;
// }