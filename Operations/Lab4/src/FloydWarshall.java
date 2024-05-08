import java.util.Arrays;

public class FloydWarshall {

    public static void floydWarshall(int[][] W, int[][] D, int[][] Ft) {
        int n = W.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = W[i][j];
                Ft[i][j] = i;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (D[i][j] > D[i][k] + D[k][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        Ft[i][j] = Ft[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] W = {
                {0, 3, -1, 2},
                {4, 0, 5, 1},
                {3, -1, 0, 6},
                {4, 5, 1, 0}
        };

        int n = W.length;
        int[][] D = new int[n][n];
        int[][] Ft = new int[n][n];

        floydWarshall(W, D, Ft);

        for (int[] l: D){
            for (int i = 0; i < D.length; i++){
                for (int j = 0; j < D.length; j++){
                    if (l[j] > l[i] + D[i][j]) {
                        System.out.println("Цикл отрицательного веса");
                        return;
                    }
                }
            }
        }
        for (int[] row: D){
            for (int elem: row){
                System.out.print(elem + "  ");
            }
            System.out.println();
        }
        System.out.println();
        for (int[] row: Ft){
            for (int elem: row){
                System.out.print((elem + 1) + "  ");
            }
            System.out.println();
        }
    }
}
