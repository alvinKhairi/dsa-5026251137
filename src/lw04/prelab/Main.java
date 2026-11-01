package lw04.prelab;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getResourceAsStream("Branches.txt"));

        int n = input.nextInt();
        int[] branches = new int[n];

        for (int i = 0; i < n; i++) {
            branches[i] = input.nextInt();
        }

        int m = input.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = input.nextInt();
        }

        input.close();

        insertionSort(branches);

        for (int i = 0; i < n; i++) {
            System.out.print(branches[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int index = linearSearch(branches, targets[i]);

            if (index != -1) {
                System.out.println(targets[i] + ": FOUND at index " + index);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i <= 3; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}