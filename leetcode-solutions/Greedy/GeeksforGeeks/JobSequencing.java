import java.util.ArrayList;
import java.util.Collections;

class Job {
    char id;
    int deadline;
    int profit;

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {

    public static void printJobScheduling(ArrayList<Job> arr, int maxDeadline) {
        // 1. ADIM: İşleri kârlarına göre azalan sırada (Büyükten küçüğe) sırala
        // Lambda expression kullanarak sıralama yapıyoruz.
        Collections.sort(arr, (a, b) -> b.profit - a.profit);

        // Zaman slotlarını tutacak array (maxDeadline kadar yer var)
        // İşlerin ID'sini tutacağız.
        char[] result = new char[maxDeadline];
        // Hangi slotun dolu olduğunu takip etmek için boolean array
        boolean[] slot = new boolean[maxDeadline];

        // Başlangıçta tüm slotlar boş
        for (int i = 0; i < maxDeadline; i++) {
            slot[i] = false;
        }

        int totalProfit = 0;

        // 2. ADIM: Sıralı işleri gez
        for (Job job : arr) {
            // Bir işi bulduğumuzda, deadline'ından başlayarak geriye doğru
            // (min(maxDeadline, job.deadline))
            // boş bir yer ararız.
            // i'yi deadline-1'den başlatıyoruz çünkü array 0-indexli.
            // Örneğin deadline 2 ise, index 1 (Slot 2) ve index 0 (Slot 1)'a bakmalıyız.

            for (int i = Math.min(maxDeadline - 1, job.deadline - 1); i >= 0; i--) {
                // Eğer slot boşsa
                if (slot[i] == false) {
                    result[i] = job.id; // İşi o slota ata
                    slot[i] = true; // Slotu dolu işaretle
                    totalProfit += job.profit;
                    break; // Yerleştirdik, diğer slota bakmaya gerek yok, döngüden çık
                }
            }
        }

        // Sonuçları yazdır
        System.out.println("Seçilen İşler:");
        for (char id : result) {
            if (id != '\0') { // Boş olmayanları yazdır
                System.out.print(id + " ");
            }
        }
        System.out.println("\nToplam Kâr: " + totalProfit);
    }

    public static void main(String[] args) {
        ArrayList<Job> arr = new ArrayList<Job>();
        arr.add(new Job('A', 2, 100));
        arr.add(new Job('B', 1, 19));
        arr.add(new Job('C', 2, 27));
        arr.add(new Job('D', 1, 25));
        arr.add(new Job('E', 3, 15));

        // Sorudaki örnekte max deadline 3 olduğu için parametre olarak veriyoruz
        // Gerçek senaryoda listedeki en büyük deadline'ı bulan bir fonksiyon
        // yazılabilir.
        printJobScheduling(arr, 3);
    }
}