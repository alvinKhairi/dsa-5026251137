package lw01.unguided;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new File("src/lw01/unguided/washes.txt"));

        int n = sc.nextInt();

        WashService[] services = new WashService[n];

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int units = sc.nextInt();

            if (type.equals("MOTORCYCLE")) {
                services[i] = new MotorcycleWash(id, days);
            } else if (type.equals("CAR")) {
                services[i] = new CarWash(id, days);
            }

            System.out.println(
                services[i].getId() + " | " +
                services[i].label() + " | " +
                services[i].calculateCharge(units)
            );
        }

        sc.close();
    }
}