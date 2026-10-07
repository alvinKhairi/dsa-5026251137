package lw03.prelab;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> playlist = new LinkedList<>();

        Scanner playlistScanner = new Scanner(
                Main.class.getResourceAsStream("playlist.txt")
        );

        while (playlistScanner.hasNextLine()) {
            String line = playlistScanner.nextLine();
            String[] parts = line.split(" ", 3);

            if (parts[0].equals("ADD")) {
                playlist.add(parts[1]);
            } else if (parts[0].equals("INSERT")) {
                int index = Integer.parseInt(parts[1]);
                playlist.add(index, parts[2]);
            } else if (parts[0].equals("REMOVE")) {
                playlist.remove(parts[1]);
            }
        }

        playlistScanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        Scanner participantScanner = new Scanner(
                Main.class.getResourceAsStream("participants.txt")
        );

        while (participantScanner.hasNextLine()) {
            String name = participantScanner.nextLine();

            if (!participants.add(name)) {
                duplicateRegistrations++;
            }
        }

        participantScanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int participantNumber = 1;
        for (String name : participants) {
            System.out.println(participantNumber + ". " + name);
            participantNumber++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner inventoryScanner = new Scanner(
                Main.class.getResourceAsStream("inventory.txt")
        );

        while (inventoryScanner.hasNextLine()) {
            String line = inventoryScanner.nextLine();
            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    inventory.put(product, inventory.get(product) + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product)
                        && inventory.get(product) >= quantity) {
                    inventory.put(product, inventory.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }

        inventoryScanner.close();

        System.out.println("===== Problem 3 =====");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Failed sales: " + failedSales);
    }
}