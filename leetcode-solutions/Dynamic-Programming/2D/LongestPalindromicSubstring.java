public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();

        // Edge Case: Boş veya tek harf ise kendisini döndür
        if (n <= 1)
            return s;

        // dp[i][j] -> s[i...j] aralığı palindrom mu?
        boolean[][] dp = new boolean[n][n];

        // En uzun palindromu takip etmek için değişkenler
        int start = 0;
        int maxLen = 1;

        // BOTTOM-UP YAKLAŞIM:
        // i: Başlangıç noktası (Sondan başa doğru geliyoruz)
        // j: Bitiş noktası (i'den sona doğru gidiyoruz)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                // 1. Kural: Uçtaki karakterler eşit olmalı
                if (s.charAt(i) == s.charAt(j)) {

                    // 2. Kural:
                    // (j - i < 2): Harfler yan yanaysa veya aynı harfse (aa, a) -> True
                    // dp[i+1][j-1]: İç kısım (sol alt çapraz) palindromsa -> True
                    if ((j - i < 2) || dp[i + 1][j - 1]) {
                        dp[i][j] = true;

                        // ÖNCEKİ SORUDAN FARK:
                        // Eğer şu an bulduğumuz palindrom, elimizdeki rekor uzunluktan büyükse
                        // güncelle.
                        if (j - i + 1 > maxLen) {
                            start = i; // Yeni başlangıç noktası
                            maxLen = j - i + 1; // Yeni uzunluk
                        }
                    }
                }
            }
        }

        // En uzun palindromu kesip döndür
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