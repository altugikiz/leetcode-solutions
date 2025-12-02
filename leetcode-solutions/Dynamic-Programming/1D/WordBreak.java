import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 1. Sözlüğü HashSet'e çevir (Hızlı arama için şart)
        Set<String> wordSet = new HashSet<>(wordDict);

        // 2. DP Tablosunu oluştur
        // Uzunluk + 1 çünkü 0. indeks "boş string"i temsil eder.
        boolean[] dp = new boolean[s.length() + 1];

        // 3. Başlangıç noktası
        dp[0] = true; // Hiçbir şey seçmemek geçerli bir durumdur.

        // 4. Tabloyu Doldur (i: stringin uzunluğu 1'den sona kadar)
        for (int i = 1; i <= s.length(); i++) {

            // j: Kesme noktası (0'dan i'ye kadar deneriz)
            for (int j = 0; j < i; j++) {

                // KURAL:
                // 1. dp[j] true olmalı (j'ye kadar olan kısım geçerli mi?)
                // 2. j'den i'ye kalan parça sözlükte var mı?
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Bulduysak diğer j'leri denemeye gerek yok, döngüyü kır.
                }
            }
        }

        // 5. Sonuç: Tablonun en son kutusu
        return dp[s.length()];
    }
}
