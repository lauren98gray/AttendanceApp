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
        ArrayList<Integer> absences = initialize(name.length(), 11);
        System.out.println("The elements are " + absences);


        // Number of students with perfect attendance
        System.out.println(countNumAbsences(absences, 0) + " student(s) had perfect attendance.");


        // Average of all absences
        System.out.println("The average of all the absences is " + average(absences) + ".");


        // % of students with fewer than 3 absences & perfect attendance
        // keeps outputting 0.0%
        System.out.println(percentLessThan(absences, 3) + "% of students who had fewer than 3 absences also had perfect attendance.");

        // students that had [X] absences
        System.out.print("Enter the number of absences you'd like to check: ");
        int numAbsences = sc.nextInt();
        ArrayList<Integer> indexAbsence = findAbsencesIndex(absences, numAbsences);
        if (!indexAbsence.isEmpty()) {
            System.out.println("The students at indices " + indexAbsence + " had " + numAbsences + " absences.");
        } else {
            System.out.println("No students have " + numAbsences + " absences.");
        }


        // Which and what percentage of students have FE'd the course
            System.out.print("How many times per week does this course meet? ");
            int numFE = sc.nextInt() * 2;
            ArrayList<Integer> indexFE = findFE(absences, numFE);

        // always outputs 0.0%
        if(indexFE.size()>0) {
        System.out.println("The index(es) of the student(s) who have FE'd this course are: " + indexFE);
        double percentFE = (numFE / absences.size()) * 100.0;
        //System.out.printf(" %d divided by %d is %.2f%%", indexFE.size(), absences.size(), percentFE);
        System.out.println(percentFE + "% of students have FE'd this course.");

    }

    //average of only the non-FE'd absences
    //always outputs empty list
        ArrayList<Integer> nonFE = listNonFE(absences, numFE);
        System.out.println("the non- fe: " + nonFE);
        System.out.println("The average of only the non-FE'd absences is " + average(nonFE));

        //Add [X] to any absences greater than [Y]
        addAbsences(absences, 3, 2);
        System.out.println("Absences with 3 added to anything greater than 2: " + absences);
}

    private static void addAbsences(ArrayList<Integer> absences, int numToAdd, int minNum) {
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) > minNum){
                absences.set(i, absences.get(i) + numToAdd);
            }
            if (absences.get(i) < 0){
                absences.set(i, 0);
            }
            if (absences.get(i) > 15){
                absences.set(i, 15);
            }
        }
    }

    //function that returns ArrayList of nonFE's
    //returns empty list?
    // tried !indexFE.contains(i) & nested loop. same problem
    private static ArrayList<Integer> listNonFE(ArrayList<Integer> absences, int numFE) {
        ArrayList<Integer> indexFE = findFE(absences, numFE);
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i >= absences.size(); i++) {
            for (int j = 0; j < indexFE.size(); j++) {
                if (indexFE.get(j) == i){
                    answer.add(i);
                }
            }
            //if (! indexFE.contains(i)){
                //answer.add(i);
            //}
        }
        return answer;
    }

    //function that finds indices of specific num of absences
    private static ArrayList<Integer> findAbsencesIndex(ArrayList<Integer> absences, int numAbsences) {
        ArrayList<Integer> indexAbsence = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) == numAbsences) {
                indexAbsence.add(i);
            }
        }
        return indexAbsence;
    }

    // function that initializes ArrayLists of integers
    private static ArrayList<Integer> initialize(int numOfElements, int bound) {
        ArrayList<Integer> answer = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numOfElements; i++){
            answer.add(rand.nextInt(bound));
        }
        return answer;
    }

    //function that returns ArrayList of indices of FE's
    private static ArrayList<Integer> findFE(ArrayList<Integer> absences, int numFE) {
        ArrayList<Integer> indexFE = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) >= numFE) {
                indexFE.add(i);
            }
        }
        return indexFE;
    }





    // function that counts how many students have [X] absences
    private static int countNumAbsences(ArrayList<Integer> result, int numAbsences) {
        int numOfAbsences = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == numAbsences) {
                numOfAbsences++;
            }
        }
        return numOfAbsences;
    }

    // function that counts how many students have less than [X] absences
    private static int countLessThan(ArrayList<Integer> absences, int maxNum) {
        int lessThan = 0;
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) < maxNum){
                lessThan++;
            }
        }
        return lessThan;
    }

    // function that calculates percent of students that have less than [X] absences and perfect attendance
    private static double percentLessThan(ArrayList<Integer> absences, int maxNum) {
        double percent = (countNumAbsences(absences, 0) / countLessThan(absences, maxNum)) * 100.0;
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
