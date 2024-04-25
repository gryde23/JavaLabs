import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

public class KruskalAlgorithm {
    int V; // Количество вершин
    int E; // Количество ребер
    Edge edge[]; // Массив ребер

    // Функция для поиска родителя вершины
    int find(int parent[], int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    // Функция для объединения двух вершин
    void union(int parent[], int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);
        parent[xroot] = yroot;
    }

    // Функция для создания минимального остовного дерева
    void kruskalMST() {
        Edge result[] = new Edge[V]; // Массив для хранения ребер остовного дерева
        int e = 0; // Индекс для result[]
        int i = 0; // Индекс для edge[]

        // Сортируем все ребра по весу
        Arrays.sort(edge);

        // Создаем множества для каждой вершины
        int parent[] = new int[V];
        for (int v = 0; v < V; ++v)
            parent[v] = v;

        // Проходим по каждому ребру в отсортированном массиве
        while (e < V - 1 && i < E) {
            Edge next_edge = edge[i++];

            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            // Если добавление ребра не создает цикл
            if (x != y) {
                result[e++] = next_edge;
                union(parent, x, y);
            }
            int sum = 0;
            for (i = 0; i < e; ++i) {
                System.out.println((result[i].src + 1) + " -- " + (result[i].dest + 1) + " == " + result[i].weight);
                sum += result[i].weight;
            }
            System.out.println();
        }
        int sum = 0;
        // Выводим ребра остовного дерева
        System.out.println("Минимальное остовное дерево:");
        for (i = 0; i < e; ++i) {
            System.out.println((result[i].src + 1) + " -- " + (result[i].dest + 1) + " == " + result[i].weight);
            sum += result[i].weight;
        }
        System.out.println("Вес дерева: " + sum);
    }

    public static void main(String[] args) {
        KruskalAlgorithm graph = new KruskalAlgorithm();

        // Количество вершин
        graph.V = 5;

        // Количество ребер
        graph.E = 8;
        graph.edge = new Edge[graph.E];

        // Добавляем ребра
        graph.edge[0] = new Edge();
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 7;

        graph.edge[1] = new Edge();
        graph.edge[1].src = 0;
        graph.edge[1].dest = 3;
        graph.edge[1].weight = 5;

        graph.edge[2] = new Edge();
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 2;

        graph.edge[3] = new Edge();
        graph.edge[3].src = 1;
        graph.edge[3].dest = 2;
        graph.edge[3].weight = 3;

        graph.edge[4] = new Edge();
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.edge[5] = new Edge();
        graph.edge[5].src = 2;
        graph.edge[5].dest = 4;
        graph.edge[5].weight = 6;

        graph.edge[6] = new Edge();
        graph.edge[6].src = 3;
        graph.edge[6].dest = 4;
        graph.edge[6].weight = 3;

        graph.edge[7] = new Edge();
        graph.edge[7].src = 1;
        graph.edge[7].dest = 4;
        graph.edge[7].weight = 5;
        graph.kruskalMST();
    }
}
