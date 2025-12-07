package GeeksforGeeks;
import java.util.Arrays;
import java.util.Comparator;

public class ActSelectionIntScheduling {
    public static void selectActivities(int[][] events) {
        // ADIM 1: Etkinlikleri BİTİŞ sürelerine (index 1) göre sırala (Küçükten büyüğe)
        // a[1] -> bitiş süresi
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));

        System.out.println("Sıralanmış Liste: " + Arrays.deepToString(events));
        System.out.println("\n--- Seçilen Etkinlikler ---");

        // ADIM 2: İlk etkinliği (sıralandıktan sonraki ilkini) her zaman seç
        // i = En son seçilen etkinliğin indeksi
        int i = 0;
        System.out.println("Seçildi: [" + events[i][0] + ", " + events[i][1] + "]");
        int count = 1; // Sayacı başlat

        // ADIM 3: Diğer etkinlikleri gez
        // j = Şu an incelediğimiz aday etkinlik
        for (int j = 1; j < events.length; j++) {

            // KURAL: Adayın başlangıcı (events[j][0]),
            // son seçilenin bitişinden (events[i][1]) büyük veya eşit olmalı.
            if (events[j][0] >= events[i][1]) {
                System.out.println("Seçildi: [" + events[j][0] + ", " + events[j][1] + "]");

                // Bu etkinliği seçtik, artık "son seçilen" (i) bu oldu.
                i = j;
                count++;
            } else {
                // Çakışma var, pas geç (Log sadece görmek için)
                // System.out.println("Çakıştı, Atlandı: [" + events[j][0] + ", " + events[j][1]
                // + "]");
            }
        }

        System.out.println("Toplam Etkinlik Sayısı: " + count);
    }

    public static void main(String[] args) {
        // Senin senaryondaki veri seti
        int[][] events = {
                { 1, 2 },
                { 2, 3 },
                { 3, 4 },
                { 1, 2 }
        };

        selectActivities(events);
    }
}
