import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumNumberofEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        // 1. ADIM: Etkinlikleri BAŞLANGIÇ gününe göre sırala.
        // Eğer başlangıç günleri aynıysa, bitişe göre sıralamanın çok önemi yok,
        // çünkü Heap bunu zaten halledecek.
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        // Bitiş günlerini tutacak Min-Heap (En küçük sayı en tepede olur)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int i = 0; // events dizisindeki indeksimiz
        int n = events.length;
        int count = 0; // Katıldığımız etkinlik sayısı
        int d = 1; // Gün sayacı (1. günden başlıyoruz)

        // İşlenecek etkinlik olduğu sürece veya Heap'te bekleyen iş olduğu sürece devam
        // et
        while (i < n || !pq.isEmpty()) {

            // OPTİMİZASYON: Eğer Heap boşsa, gün sayacını (d) tek tek artırmak yerine
            // direkt sıradaki etkinliğin başladığı güne ışınla.
            // (Örn: d=5 ama sıradaki etkinlik 10. gün başlıyorsa, 6-7-8-9'u bekleme)
            if (pq.isEmpty()) {
                d = events[i][0];
            }

            // 2. ADIM: Bugün (d) veya öncesinde başlamış olan tüm etkinlikleri Heap'e at.
            // Heap'e sadece "Bitiş Tarihlerini" (events[i][1]) atıyoruz.
            while (i < n && events[i][0] <= d) {
                pq.offer(events[i][1]);
                i++;
            }

            // 3. ADIM: Süresi geçmiş ölü etkinlikleri temizle.
            // (Bitiş tarihi < d olanlar artık yapılamaz)
            while (!pq.isEmpty() && pq.peek() < d) {
                pq.poll();
            }

            // 4. ADIM (Greedy): Geçerli bir etkinlik varsa,
            // bitiş tarihi en yakın olanı seç (poll) ve yapıldı say.
            if (!pq.isEmpty()) {
                pq.poll(); // Etkinliğe katıldım
                count++;
            }

            // Bir sonraki güne geç
            d++;
        }

        return count;
    }
}
