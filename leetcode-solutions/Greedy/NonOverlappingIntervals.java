import java.util.Arrays;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)
            return 0;

        // 1. ADIM: Bitiş sürelerine göre sırala (Activity Selection ile aynı)
        // a[1] -> bitiş süresi
        // Integer.compare kullanmak, sayıların taşmasını (overflow) önler.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int removeCount = 0;
        // İlk aralığın bitişini referans alıyoruz
        int prevEnd = intervals[0][1];

        // 2. ADIM: 1. indeksten başlayarak gez
        for (int i = 1; i < intervals.length; i++) {

            // Şu anki aralığın başlangıcı (start), önceki bitişten (prevEnd) KÜÇÜKSE
            // Demek ki iç içe girdiler (Çakışma var).
            if (intervals[i][0] < prevEnd) {
                // GREEDY HAMLE:
                // Çakışma varsa, bu yeni geleni "silinmiş" sayıyoruz.
                // Çünkü listemiz bitişe göre sıralı olduğu için,
                // şu anki (i) muhtemelen prevEnd'den daha geç veya aynı anda bitiyor.
                // Alanı korumak için bunu feda ediyoruz.
                removeCount++;
            } else {
                // Çakışma yoksa, bu aralığı "tuttuk" demektir.
                // Yeni referans bitiş noktamız bu aralığın bitişi olur.
                prevEnd = intervals[i][1];
            }
        }

        return removeCount;
    }
}
