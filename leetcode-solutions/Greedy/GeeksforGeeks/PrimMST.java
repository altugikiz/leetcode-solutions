public class PrimMST {

    // Grafın düğüm sayısı (örnek için 5 seçtik)
    private static final int V = 5;

    // Henüz MST'ye dahil edilmemiş düğümler arasından
    // en küçük anahtar (key) değerine sahip düğümü bulan fonksiyon
    int minKey(int key[], Boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // MST'yi yazdıran yardımcı fonksiyon
    void printMST(int parent[], int graph[][]) {
        System.out.println("Kenar \tAğırlık");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    // Prim Algoritması Fonksiyonu
    void primMST(int graph[][]) {
        // MST'yi saklamak için parent array'i
        int parent[] = new int[V];

        // Kenar ağırlıklarını tutmak için key array'i
        // (O anki düğüme ulaşmanın minimum maliyeti)
        int key[] = new int[V];

        // Düğümün MST'ye dahil edilip edilmediğini tutan array
        Boolean mstSet[] = new Boolean[V];

        // Tüm key'leri sonsuz yap, mstSet'i false yap
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // İlk düğümün key değerini 0 yap ki ilk olarak o seçilsin
        key[0] = 0;
        parent[0] = -1; // İlk düğüm kök olduğu için ebeveyni yok

        // Tüm düğümler için döngü (V-1 kenar olacak)
        for (int count = 0; count < V - 1; count++) {

            // 1. ADIM (GREEDY): MST setine dahil olmayan ve en küçük key değerine sahip
            // düğümü seç
            int u = minKey(key, mstSet);

            // Seçilen düğümü MST setine ekle
            mstSet[u] = true;

            // 2. ADIM: Seçilen düğümün (u) komşularını güncelle
            for (int v = 0; v < V; v++) {
                // graph[u][v] != 0 -> Komşuluk var mı?
                // mstSet[v] == false -> Düğüm zaten ekli mi?
                // graph[u][v] < key[v] -> Yeni yol daha mı kısa?
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {
        PrimMST t = new PrimMST();

        // Örnek Graf (Komşuluk Matrisi)
        // 0 ile 1 arasında ağırlık 2, 0 ile 3 arasında ağırlık 6 vb.
        int graph[][] = new int[][] {
                { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 }
        };

        // Algoritmayı çalıştır
        t.primMST(graph);
    }
}