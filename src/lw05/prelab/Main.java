package lw05.prelab;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
    public class Main {
    static int size;
    static String[] names;
    static int[][] graph;

    static void addEdge(int a, int b, int weight) {
        graph[a][b] = weight;
        graph[b][a] = weight;
    }

    static void printPath(int[] path) {
        for (int i = 0; i < size; i++) {
            System.out.print(names[path[i]]);

            if (i < size - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    static int[] bfs(int start) {
        int[] order = new int[size];
        int count = 0;

        Set<Integer> visited = new TreeSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[count] = current;
            count++;

            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }

        return order;
    }

    static int[] dfsOrder = new int[10];
    static int dfsCount = 0;

    static void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        dfsOrder[dfsCount] = current;
        dfsCount++;

        for (int i = 0; i < size; i++) {
            if (graph[current][i] != 0 && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    static int findTheNextNode(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int next = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && distance[i] < min) {
                min = distance[i];
                next = i;
            }
        }

        return next;
    }

    static void route(int current, int start, int[] prev) {
        if (current == start) {
            System.out.print(names[start]);
            return;
        }

        route(prev[current], start, prev);
        System.out.print(" -> " + names[current]);
    }

    static void dijkstraAlg(int start, int destination) {
        int[] distance = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        distance[start] = 0;

        for (int i = 0; i < size; i++) {
            int current = findTheNextNode(distance, visited);
            if (current == -1) {
            break; 
            }

            visited[current] = true;

            for (int j = 0; j < size; j++) {
                if (graph[current][j] != 0 && !visited[j]) {
                    int newDistance = distance[current] + graph[current][j];

                    if (newDistance < distance[j]) {
                        distance[j] = newDistance;
                        prev[j] = current;
                    }
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");

        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + distance[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start]
                + " to " + names[destination] + " ===");

        route(destination, start, prev);
        System.out.println();

        System.out.println("Total time: " + distance[destination] + " min");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("Campus.txt"));
        size = sc.nextInt();
        names = new String[size];
        graph = new int[size][size];

        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }

        int edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int weight = sc.nextInt();

            addEdge(a, b, weight);
        }

        int start = sc.nextInt();
        int destination = sc.nextInt();

        System.out.println("=== BFS from " + names[start] + " ===");
        printPath(bfs(start));

        dfsCount = 0;
        Set<Integer> visited = new TreeSet<>();

        dfs(start, visited);

        System.out.println("=== DFS from " + names[start] + " ===");
        printPath(dfsOrder);

        dijkstraAlg(start, destination);

        sc.close();
    }
}
