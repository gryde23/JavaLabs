
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[][] m1 = new int[5][4];
        int[][] m2 = new int[4][3];
        int[][] m3 = new int[3][2];
        int[][] m4 = new int[2][10];
        int[][] m5 = new int[10][15];
        int[][] m6 = new int[15][5];
        int[][][] matrixs = {m1, m2, m3, m4, m5, m6};
        Random random = new Random();
        for(int i = 0; i < matrixs.length; i++){
            for(int j = 0; j < matrixs[i].length; j++){
                for (int k = 0; k < matrixs[i][j].length; k++){
                    matrixs[i][j][k] = random.nextInt(10);
                }
            }
        }
        int[] p = new int[matrixs.length + 1];
        for (int i = 0; i < matrixs.length - 1; i++) {
            int rows1 = matrixs[i].length;
            int columns1 = matrixs[i][0].length;
            int columns2 = matrixs[i + 1][0].length;
            if (i == 0) {
                p[0] = rows1;
                p[1] = columns1;
                p[2] = columns2;
            } else {
                p[i + 2] = columns2;
            }
        }
        int n = p.length;
//        System.out.println("\n" + Arrays.toString(p));
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

        matrixChainOrder(p, m, s);

        System.out.println("Кол-во умножений: " + m[1][n - 1]);
        System.out.print("Оптимальное перемножение: ");
        StringBuilder buff = new StringBuilder();
        printOptimalParens(s, 1, n - 1, buff);
        int ind = 0;
        int ids = 0;
        int c = matrixs.length + 1;
        int[] indexies = new int[matrixs.length * 2 - 2];
        StringBuilder b = new StringBuilder();
        System.out.println();
            for (int i = 0; i < buff.length(); i++){
                if (buff.charAt(i) == '(') {
                    b = new StringBuilder();
                    continue;
                }
                if (buff.charAt(i) == ')' && b.length() >= 3) {
                    String[] pair = b.toString().split("[A,C]");
                    for (String pr: pair){
                        if (!pr.isEmpty()) {
                            indexies[ids] = Integer.parseInt(pr);
                            ids++;
                        }
                    }
                    buff.replace(ind - 1, i + 1, "A" + c);
//                    System.out.println(buff);
                    c++;
                    b = new StringBuilder();
                    i = 0;
                    continue;
                }
                if (buff.charAt(i) == ')') continue;
                b.append(buff.charAt(i));
                if (b.length() == 1) ind = i;
            }

//        System.out.println(Arrays.toString(indexies));
        int[][][] C = new int[c - 1][][];
        c = matrixs.length;
        Integer[] multiplications = {0};
        for (int i = 0; i < matrixs.length; i++){
            C[i] = matrixs[i];
        }
        for (int i = 0; i < indexies.length; i += 2){
            C[c] = matrixMultiply(C[indexies[i] - 1], C[indexies[i + 1] - 1],multiplications);
            c++;
        }
        System.out.println(multiplications[0]);
        int[][] res = C[c - 1];
        for (int[] row: res){
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] matrixMultiply(int[][] A, int[][] B, Integer[] multiplications) {
        int rowsA = A.length;
        int columnsA = A[0].length;
        int rowsB = B.length;
        int columnsB = B[0].length;

        if (columnsA != rowsB) {
            throw new IllegalArgumentException("Matrix dimensions do not match for multiplication.");
        }

        int[][] C = new int[rowsA][columnsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsB; j++) {
                for (int k = 0; k < columnsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                    multiplications[0]++;
                }
            }
        }
        return C;
    }

    public static void matrixChainOrder(int[] p, int[][] m, int[][] s) {
        int n = p.length;
        for (int i = 1; i < n; i++) {
            m[i][i] = 0;
        }
        for (int l = 2; l < n; l++) {
            for (int i = 1; i < n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public static void printOptimalParens(int[][] s, int i, int j, StringBuilder buff) {
        if (i == j) {
            System.out.print("A" + i);
            buff.append("A").append(i);
        } else {
            System.out.print("(");
            buff.append("(");
            printOptimalParens(s, i, s[i][j], buff);
            printOptimalParens(s, s[i][j] + 1, j, buff);
            System.out.print(")");
            buff.append(")");
        }
    }
}