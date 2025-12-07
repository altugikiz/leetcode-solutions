import java.util.PriorityQueue;
import java.util.Comparator;

// Huffman Ağacı Düğümü
class HuffmanNode {
    int item;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

// Düğümleri frekanslarına göre karşılaştıran sınıf
class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.item - y.item; // Küçükten büyüğe sırala
    }
}

public class HuffmanCoding {

    // Ağacı dolaşarak kodları yazdıran fonksiyon (Recursive)
    public static void printCode(HuffmanNode root, String s) {
        // Base case: Eğer yaprak düğüme geldiysek (harf içeriyorsa)
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }

        // Sola giderken "0" ekle
        printCode(root.left, s + "0");
        // Sağa giderken "1" ekle
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) {

        int n = 6;
        char[] charArray = { 'A', 'B', 'C', 'D', 'E', 'F' };
        int[] charfreq = { 5, 9, 12, 13, 16, 45 };

        // Min-Priority Queue oluştur (En küçük frekans en üstte)
        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator());

        // Tüm karakterleri düğüm olarak kuyruğa ekle
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.item = charfreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }

        HuffmanNode root = null;

        // Kuyrukta tek bir düğüm kalana kadar (Kök düğüm) devam et
        while (q.size() > 1) {

            // 1. ADIM (GREEDY): En küçük frekanslı ilk düğümü çıkar
            HuffmanNode x = q.peek();
            q.poll();

            // 2. ADIM (GREEDY): En küçük frekanslı ikinci düğümü çıkar
            HuffmanNode y = q.peek();
            q.poll();

            // 3. ADIM: Bu iki düğümü toplayarak yeni bir ebeveyn (parent) düğüm oluştur
            HuffmanNode f = new HuffmanNode();
            f.item = x.item + y.item; // Frekansların toplamı
            f.c = '-'; // Ara düğüm olduğu için karakteri yok
            f.left = x; // İlk çıkarılan (küçük olan) sola
            f.right = y; // İkinci çıkarılan sağa
            root = f;

            // 4. ADIM: Yeni düğümü kuyruğa geri at
            q.add(f);
        }

        // Sonuçları yazdır
        System.out.println("Karakter : Huffman Kodu");
        printCode(root, "");
    }
}