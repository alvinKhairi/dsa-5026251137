package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Restaurants {
    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> success = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner input = new Scanner(
            Restaurants.class.getResourceAsStream("orders.txt")
        );

        while (input.hasNextLine()) {
            String[] data = input.nextLine().split(" ");
            orders.add(data);
        }

        input.close();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        while (orders.size() > 0) {
            queue.add(orders.removeFirst());
        }

        while (queue.peek() != null) {

            String[] order = queue.poll();

            String food = order[1];
            String drink = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            if (!food.equals("-")) {
                for (String[] f : foods) {
                    if (f[0].equals(food)) {

                        int stock = Integer.parseInt(f[1]);

                        if (stock == 0) {
                            foodAvailable = false;
                        }

                        break;
                    }
                }
            }

            if (!drink.equals("-")) {
                for (String[] d : drinks) {
                    if (d[0].equals(drink)) {

                        int stock = Integer.parseInt(d[1]);

                        if (stock == 0) {
                            drinkAvailable = false;
                        }

                        break;
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {

                if (!food.equals("-")) {
                    for (String[] f : foods) {
                        if (f[0].equals(food)) {

                            int stock = Integer.parseInt(f[1]);
                            f[1] = String.valueOf(stock - 1);

                            break;
                        }
                    }
                }

                if (!drink.equals("-")) {
                    for (String[] d : drinks) {
                        if (d[0].equals(drink)) {

                            int stock = Integer.parseInt(d[1]);
                            d[1] = String.valueOf(stock - 1);

                            break;
                        }
                    }
                }

                success.add(order);

            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");

        for (String[] order : success) {
            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }

        System.out.println("=== Remaining Food Stock ===");

        for (String[] f : foods) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");

        for (String[] d : drinks) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println("=== Failed Orders ===");

        while (failed.size() > 0) {

            String[] order = failed.pop();

            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }
    }
}