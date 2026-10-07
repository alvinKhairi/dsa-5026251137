package lw03.unguided;
 
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Set<String> registered = new LinkedHashSet<>();
        Set<String> checkedIn = new LinkedHashSet<>();

        Scanner sc1 = new Scanner(
        Main.class.getResourceAsStream("registrations.txt")
        );

        while (sc1.hasNextLine()) {
        String studentId = sc1.nextLine();
        registered.add(studentId);
        }

        sc1.close();

        Scanner sc2 = new Scanner(
        Main.class.getResourceAsStream("checkins.txt")
        );

        int rejectedAttempts = 0;

        System.out.println("===== Event Check-In Results =====");

        while (sc2.hasNextLine()) {
        String studentId = sc2.nextLine();

            if (!registered.contains(studentId)) {
                System.out.println(studentId + ": Rejected (not registered)");
                rejectedAttempts++;
            } else if (checkedIn.contains(studentId)) {
                System.out.println(studentId + ": Rejected (already checked in)");
                rejectedAttempts++;
            } else {
                checkedIn.add(studentId);
                System.out.println(studentId + ": Checked in");
            }
        }

        sc2.close();

        int absentStudents = registered.size() - checkedIn.size();

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}