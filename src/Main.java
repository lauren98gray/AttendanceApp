import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n\nHello, AttendanceApp!\n");

        // Customized welcome message
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Welcome, " + name + "!");

        // Make & output ArrayList of absences
        ArrayList<Integer> absences = new ArrayList<>();
        Random rand = new Random();
        for (int i=0; i < name.length(); i++){
            int num = rand.nextInt(11);
            absences.add(num);
        }
        System.out.println("The elements are " + absences);


        // Number of students with perfect attendance
        System.out.println(perfAttend(absences) + " student(s) had perfect attendance.");


        // Average of all absences
        System.out.println("The average of all the absences is " + average(absences) + ".");


        // % of students with fewer than 3 absences & perfect attendance
        System.out.println(percent(absences) + "% of students who had fewer than 3 absences also had perfect attendance.");



    }

    // function that counts how many students have perfect attendance
    private static double perfAttend(ArrayList<Integer> absences) {
        int perfAttend = 0;
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) == 0) {
                perfAttend++;
            }
        }
        return (double) perfAttend;
    }

    // function that calculates percent of students that have less than 3 absences and perfect attendance
    private static double percent(ArrayList<Integer> absences) {
        int lessThan3 = 0;
        for (int i=0; i < absences.size(); i++) {
            if (absences.get(i) < 3) {
                lessThan3++;
            }
        }
        double percent = (perfAttend(absences) / lessThan3) * 100;
        return percent;
    }

    // function that calculates sum
    private static int sum(ArrayList<Integer> absences) {
        int sum = 0;
        for (int i=0; i < absences.size(); i++){
            sum += absences.get(i);
        }
        return sum;
    }

    // function that calculates average
    private static double average(ArrayList<Integer> absences) {
        int avg = sum(absences) / absences.size();
        return avg;
    }
}
