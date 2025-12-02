public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0; // "0" ile başlayanlar geçersizdir.
        }

        int n = s.length();
        // dp[i] => ilk i karakterin çözüm sayısı
        int[] dp = new int[n + 1];

        // Başlangıç değerleri
        dp[0] = 1; // Boş string için 1 yol (baz durumu)
        dp[1] = 1; // İlk karakter 0 değilse (yukarıda kontrol ettik), 1 yol var.

        for (int i = 2; i <= n; i++) {
            // Şu anki rakam (String indexi 0-tabanlı olduğu için i-1 kullanıyoruz)
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));
            // Şu anki ve bir önceki rakam (i-2'den i'ye kadar)
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));

            // KURAL 1: Tek hane geçerli mi? (1-9 arası)
            if (oneDigit >= 1) {
                dp[i] += dp[i - 1];
            }

            // KURAL 2: Çift hane geçerli mi? (10-26 arası)
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
