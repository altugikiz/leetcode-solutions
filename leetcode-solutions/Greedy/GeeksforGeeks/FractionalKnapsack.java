import java.util.Arrays;
import java.util.Comparator;

// Eşya sınıfı: Her eşyanın bir ağırlığı ve değeri vardır.
class Item {
    int value;
    int weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    // Ana Greedy Fonksiyonu
    private static double getMaxValue(Item[] items, int capacity) {

        // 1. ADIM: Eşyaları (Değer / Ağırlık) oranına göre büyükten küçüğe sırala
        // Java'nın Comparator arayüzünü kullanarak özel bir sıralama yapıyoruz.
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double r1 = (double) o1.value / o1.weight;
                double r2 = (double) o2.value / o2.weight;

                // Azalan sıra (Büyükten küçüğe) için r2 ile r1'i kıyaslıyoruz
                return Double.compare(r2, r1);
            }
        });

        double totalValue = 0.0; // Toplam kâr ondalıklı olabilir
        int currentWeight = 0; // Şu anki çanta doluluğu

        // 2. ADIM: Sıralanmış eşyaları gez
        for (Item i : items) {

            // Eğer eşyanın tamamını alabiliyorsak (Çantada yer varsa)
            if (currentWeight + i.weight <= capacity) {
                currentWeight += i.weight;
                totalValue += i.value;
                System.out.println("Alınan (Tam): Değer=" + i.value + " Ağırlık=" + i.weight);
            }
            // Eğer eşyanın tamamı sığmıyorsa, sığdığı kadarını (kesirli) al
            else {
                int remain = capacity - currentWeight;
                // Oran hesabı: (Eşyanın Değeri / Eşyanın Ağırlığı) * Alınacak Miktar
                double fractionValue = ((double) i.value / i.weight) * remain;

                totalValue += fractionValue;
                System.out.println("Alınan (Kesirli): " + remain + " birim ağırlık, Değer=" + fractionValue);

                // Çanta doldu, döngüden çık
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        // Örnek Veri
        // Item(değer, ağırlık)
        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        };

        int capacity = 50; // Çantanın kapasitesi

        double maxValue = getMaxValue(items, capacity);
        System.out.println("---------------------------");
        System.out.println("Maksimum Toplam Değer: " + maxValue);
    }
}