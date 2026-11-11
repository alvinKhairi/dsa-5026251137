package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void bubbleSort(int[] arr, int passes) {
        for (int i = 0; i < passes && i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
            }
        }
    }
}

 public static int[] binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (arr[mid] == target) {
                return new int[]{mid, steps};
            } else if (target < arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return new int[]{-1, steps};
    }

 public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

public static void main(String[] args) {

    Scanner input = new Scanner(
         Main.class.getResourceAsStream("parcels.txt")
        );

        int n = input.nextInt();
        int[] parcels = new int[n];

        for (int i = 0; i < n; i++) {
            parcels[i] = input.nextInt();
        }

        int m = input.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = input.nextInt();
        }

        input.close();

       // ini 2 pass pertama
        bubbleSort(parcels, 2);

        int[] afterTwoPasses = new int[n];

        for (int i = 0; i < n; i++) {
            afterTwoPasses[i] = parcels[i];
        }

        // lanjut sorting full nya 
        bubbleSort(parcels, n - 3);

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);

        System.out.println();

        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        System.out.println();

        System.out.println("==== Search Results ====");

        for (int i = 0; i < m; i++) {
            int[] result = binarySearch(parcels, targets[i]);

            if (result[0] != -1) {
                System.out.println(targets[i] + ": FOUND at index "
                    + result[0] + " (steps: " + result[1] + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: "
                    + result[1] + ")");
            }
        }
    }
}
