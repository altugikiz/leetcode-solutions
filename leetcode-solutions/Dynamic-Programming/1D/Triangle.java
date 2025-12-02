import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        
        // DP tablosu 
        int[] dp = new int[n + 1];

        // En alttan başlayıp yukarı çıkacağız
        // Son satırdan başlayarak her bir üst satır için en kısa yolu hesaplıyoruz.
        for (int row = n - 1; row >= 0; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                // Şimdiki değer + (altındaki veya sağ çaprazındaki en küçük değer)
                // dp[col] -> row+1'deki col (aşağısı)
                // dp[col+1] -> row+1'deki col+1 (sağ çaprazı)
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }

        // Sonuç en tepedeki (0. indeks) hücrede birikecektir.
        return dp[0];
    }
}


// Örnek Üçgen:

//    2
//   3 4
//  6 5 7
// Başlangıç: dp dizisi (n=3 ise size=4): [0, 0, 0, 0]

// - 1. Döngü (row = 2, yani 6 5 7 satırı): Kod min(dp[col], dp[col+1])'e bakar. Şu an hepsi 0.

// dp[0] = 6 + min(0,0) = 6

// dp[1] = 5 + min(0,0) = 5

// dp[2] = 7 + min(0,0) = 7 dp durumu: [6, 5, 7, 0] (Gördüğün gibi en alt satırı kopyalamaya gerek kalmadı, döngü halletti).

// - 2. Döngü (row = 1, yani 3 4 satırı):

// dp[0] = 3 + min(6, 5) = 3 + 5 = 8

// dp[1] = 4 + min(5, 7) = 4 + 5 = 9 dp durumu: [8, 9, 7, 0] (Artık sondaki değerlerle işimiz bitti).

// - 3. Döngü (row = 0, yani 2 satırı):

// dp[0] = 2 + min(8, 9) = 2 + 8 = 10 dp durumu: [10, 9, 7, 0]

// Sonuç: dp[0] yani 10 döner.