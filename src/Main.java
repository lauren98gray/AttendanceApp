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
        for (int i = 0; i < name.length(); i++) {
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

        // students that had [X] absences
        System.out.print("Enter the number of absences you'd like to check: ");
        int numAbsences = sc.nextInt();
        ArrayList<Integer> indexAbsence = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) == numAbsences) {
                indexAbsence.add(i);
            }
        }
        if (!indexAbsence.isEmpty()) {
            System.out.println("The students at indices " + indexAbsence + " had " + numAbsences + " absences.");
        } else {
            System.out.println("No students have " + numAbsences + " absences.");
        }


        // Which and what percentage of students have FE'd the course
            System.out.print("How many times per week does this course meet? ");
            double numFE = sc.nextInt() * 2;
            ArrayList<Integer> indexFE = new ArrayList<>();
            for (int i = 0; i<absences.size();i++) {
        if (absences.get(i) >= numFE) {
            indexFE.add(i);
        }
    }
        if(indexFE.size()>0) {
        System.out.println("The index(es) of the student(s) who have FE'd this course are: " + indexFE);
        double percentFE = (numFE / absences.size()) * 100.0;
        //System.out.printf("Formatted %d divided by %d is %.2f%%", indexFE.size(), absences.size(), percentFE);
        System.out.println(percentFE + "% of students have FE'd this course.");

    }
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
