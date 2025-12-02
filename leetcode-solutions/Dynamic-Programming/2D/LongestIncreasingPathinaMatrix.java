import java.util.LinkedList;
import java.util.Queue;

public class LongestIncreasingPathinaMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] outDegree = new int[m][n]; // Benden büyük kaç komşu var?
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // 1. Adım: Out-Degree'leri hesapla
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    // Eğer komşu daha büyükse, oraya bir yol (kenar) vardır.
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                        outDegree[i][j]++;
                    }
                }
            }
        }

        // 2. Adım: "Zirve" noktalarını (gidecek yeri olmayanları) kuyruğa ekle
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (outDegree[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        int pathLength = 0;

        // 3. Adım: BFS ile katman katman ilerle (Kahn's Algorithm mantığı)
        while (!queue.isEmpty()) {
            pathLength++; // Bir katman daha derine iniyoruz
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                // Ters yöne bakıyoruz: Benden küçük olan komşulara haber ver
                for (int[] dir : dirs) {
                    int x = r + dir[0];
                    int y = c + dir[1];

                    // Eğer komşu benden küçükse, onun "büyük komşu" listesinden beni çıkar
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[r][c]) {
                        outDegree[x][y]--;
                        if (outDegree[x][y] == 0) {
                            queue.offer(new int[] { x, y });
                        }
                    }
                }
            }
        }

        return pathLength;
    }
}
