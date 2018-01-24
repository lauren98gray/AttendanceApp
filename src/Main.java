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
        int perfAttend = 0;
        for (int i=0; i < absences.size(); i++){
            if (absences.get(i) == 0) {
                perfAttend++;
            }
        }
        System.out.println(perfAttend + " students had perfect attendance.");

        // Average of all absences
        System.out.println("The average of all the absences is " + average(absences) + ".");





    }

    private static int sum(ArrayList<Integer> absences) {
        int sum = 0;
        for (int i=0; i < absences.size(); i++){
            sum += absences.get(i);
        }
        return sum;
    }

    private static double average(ArrayList<Integer> absences) {
        int avg = sum(absences) / absences.size();
        return avg;
    }
}
