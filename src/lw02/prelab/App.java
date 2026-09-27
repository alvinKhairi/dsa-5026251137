package lw02.prelab;
//file ini dinamakan App.java karena di pdf nya tidak dispesifikasikan nama file nya, jadi saya menamakan file ini App.java// 

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        InputStream file = App.class.getResourceAsStream("transactions.txt");

        if (file == null) {
            System.out.println("File tidak ditemukan");
            return;
        }

        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {

            String line = input.nextLine();
            String[] data = line.split(" ");

            transactions.add(data);

            String name = data[0];
            boolean ada = false;

            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    ada = true;
                    break;
                }
            }

            if (!ada) {
                customers.add(new String[]{name, "0"});
            }
        }

        input.close();


        while (!transactions.isEmpty()) {
            queue.add(transactions.removeFirst());
        }


        while (!queue.isEmpty()) {

            String[] data = queue.poll();

            String name = data[0];
            String type = data[1];
            int amount = Integer.parseInt(data[2]);

            for (String[] c : customers) {

                if (c[0].equals(name)) {

                    int balance = Integer.parseInt(c[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        c[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {

                        if (amount > balance) {
                            failed.push(data);
                        } else {
                            balance -= amount;
                            c[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }


        System.out.println("=== Final Balances ===");

        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }


        System.out.println("=== Failed Transactions ===");

        while (!failed.isEmpty()) {

            String[] data = failed.pop();

            System.out.println(
                data[0] + " " + data[1] + " " + data[2]
            );
        }
    }
}