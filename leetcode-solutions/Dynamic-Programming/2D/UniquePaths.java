public class UniquePaths {
    public int integerBreak(int n) {
        // 1. ADIM: Hafıza Defterini Oluştur
        // n+1 dedik çünkü 0. kutuyu kullanmıyoruz, kafamız karışmasın.
        // dp[i] demek: "i" sayısını parçaladığımda elde edebileceğim en büyük çarpım
        // sonucu.
        int[] dp = new int[n + 1];

        // 2. ADIM: Base Case (Başlangıç)
        // 1'i parçalayamayız ama başlangıç değeri olarak 1 verelim.
        dp[1] = 1;

        // 3. ADIM: Merdiveni Çıkmaya Başla (Bottom-Up)
        // 2'den başlayıp n'e kadar (hedef sayıya kadar) tek tek çözeceğiz.
        for (int i = 2; i <= n; i++) {

            // Burası "Kesme" Döngüsü.
            // i sayısını j yerinden kesiyoruz.
            // Örnek: i=4 ise, j=1 (1 ve 3), j=2 (2 ve 2)... diye deniyoruz.
            for (int j = 1; j < i; j++) {

                // KRİTİK NOKTA:
                // Elimizde iki parça var: "j" ve "geriye kalan (i-j)"
                // İki ihtimali kıyaslıyoruz:

                // İhtimal A: j * (i - j)
                // -> Yani (i-j)'yi daha fazla parçalama, olduğu gibi çarp.
                // (Örnek: 3'ü parçalamak yerine 3 olarak kullanmak genelde daha iyidir çünkü
                // dp[3]=2'dir).

                // İhtimal B: j * dp[i - j]
                // -> Yani (i-j)'yi de daha önce bulduğun en iyi yöntemle parçala.

                int buKesimIcinEnIyi = Math.max(j * (i - j), j * dp[i - j]);

                // Şu ana kadar bulduğumuz en büyük sonuçla kıyasla ve kaydet.
                dp[i] = Math.max(dp[i], buKesimIcinEnIyi);
            }
        }

        // 4. ADIM: Sonucu Ver
        // Döngü bittiğinde dp[n] kutusunda en büyük çarpım bizi bekliyor olacak.
        return dp[n];
    }
}
