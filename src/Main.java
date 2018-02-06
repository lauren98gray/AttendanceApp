import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

public class Main {

    static Random rand = new Random();

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
        System.out.println("The average of all the absences is " + average(absences));


        // % of students with fewer than 3 absences & perfect attendance
        System.out.printf("%.2f%% of students who had fewer than 3 absences also had perfect attendance.\n", percentLessThan(absences, 3));

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
            ArrayList<Integer> valuesFE = findFEvalues(absences, numFE);


            if(indexFE.size()>0) {
                System.out.println("The index(es) of the student(s) who have FE'd this course are: " + indexFE);
                double percentFE = ((double) numFE / (double) absences.size()) * 100.0;
                System.out.printf("%.2f%%" + " of students have FE'd this course.\n", percentFE);
            }

        //average of only the non-FE'd absences
        ArrayList<Integer> nonFE = listNonFE(absences, numFE);
        System.out.println("The average of only the non-FE'd absences is " + average(nonFE));

        //Add [X] to any absences greater than [Y]
        addAbsences(absences, 3, 2);
        System.out.println("Absences with 3 added to anything greater than 2: " + absences);

        // Sort absences using a library function
        Collections.sort(absences);
        System.out.println("Sorted absences: " + absences);

        // Shuffle absences using a library function
        Collections.shuffle(absences);
        System.out.println("Shuffled absences: " + absences);

        // how many absences are unique
        Set<Integer> uniqueAbsences = countUnique(absences);
        System.out.println(uniqueAbsences.size() + " absences are unique.");

        //How many of each absence value are there?

        // Sort the absences using a user-defined sort function
        bubbleSort(absences);
        System.out.println("Sorted absences: " + absences);

        // shuffle the absences using a user-defined shuffle function
        shuffle(absences);
        System.out.println("Shuffled absences: " + absences);

        // Create and output an ArrayList of 5 distinct names
        ArrayList<String> names = initializeNames();
        System.out.println("ArrayList of 5 names: " + names);

        //Shuffle the names using user-defined shuffle
        shuffleNames(names);
        System.out.println("Shuffled list of names: " + names);

        // Create list with same size as absences using the 5 names
        ArrayList<String> listOfSameAmountOfNamesAsAbsences = sameAmountOfNamesAsAbsences(absences);
        System.out.println("List of names with same amount as absences: " + listOfSameAmountOfNamesAsAbsences);

        // Were all 5 names used at least once?
        //checkIfUsed(listOfSameAmountOfNamesAsAbsences);

        // What are the names of the students with perfect attendance
        ArrayList<String> studentsPerfAttend = findStudentsByNumOfAbsences(absences, listOfSameAmountOfNamesAsAbsences, 0);
        System.out.println("The students who had perfect attendance are: " + studentsPerfAttend);

        // What are the names of the students who have FE'd some course
        Set<String> studentsFE = findStudentsFE(absences, listOfSameAmountOfNamesAsAbsences, numFE);
        System.out.println("The students who have FE'd some course: " + studentsFE);

        // How many courses does [name] have?
        System.out.print("Enter name of student to see how many courses he or she has: ");
        String nameFromInput = sc.next();
        int numCoursesFromName = countNumCourses(listOfSameAmountOfNamesAsAbsences, nameFromInput);
        System.out.println(nameFromInput + " has " + numCoursesFromName + " courses.");

        // Which courses did [name] FE?

        // Generate today's date and output it
        LocalDate today = LocalDate.now();
        System.out.println("Today's date is: " + today);

        // How many days have you been alive?
        //int numDaysAlive = numDaysAlive();


}

    private static int countNumCourses(ArrayList<String> names, String name) {
        int count = 0;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)){
                count++;
            }
        }
        return count;
    }

    /*private static int numDaysAlive(LocalDate birthYear, LocalDate birthMonth, LocalDate birthday) {

    }*/

    private static Set<String> findStudentsFE(ArrayList<Integer> absences, ArrayList<String> names, int numFE) {
        ArrayList<Integer> indicesFE = findFE(absences, numFE);
        Set<String> namesFE = new HashSet<>();
        for (int i = 0; i < indicesFE.size(); i++) {
            namesFE.add(names.get(indicesFE.get(i)));
        }
        return namesFE;
    }

    private static ArrayList<String> findStudentsByNumOfAbsences(ArrayList<Integer> absences, ArrayList<String> names, int numAbsences) {
        ArrayList<Integer> indicesPerfAttend = findAbsencesIndex(absences, numAbsences);
        ArrayList<String> namesPerfAttend = new ArrayList<>();
        for (int i = 0; i < indicesPerfAttend.size(); i++) {
            namesPerfAttend.add(names.get(indicesPerfAttend.get(i)));
        }
        return namesPerfAttend;
    }

    /*private static boolean checkIfUsed(ArrayList<String> listOfSameAmountOfNamesAsAbsences) {
        ArrayList<String> names = initializeNames();
        for (int i = 0; i < names.size(); i++) {
            for (int j = 0; j < listOfSameAmountOfNamesAsAbsences.size(); j++) {
                if (names.get(i).equals(listOfSameAmountOfNamesAsAbsences.get(j))){

                }
            }
        }
    }*/

    private static ArrayList<String> sameAmountOfNamesAsAbsences(ArrayList<Integer> absences) {
        ArrayList<String> names = initializeNames();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            int randIndex = rand.nextInt(names.size());
            String name = names.get(randIndex);
            result.add(name);
        }
        return result;
    }

    private static void shuffleNames(ArrayList<String> names) {
        for (int i = 0; i < names.size(); i++) {
            int randIndex = rand.nextInt(names.size());
            String temp = names.get(randIndex);
            names.set(randIndex, names.get(i));
            names.set(i, temp);
        }
    }

    private static ArrayList<String> initializeNames() {
        ArrayList<String> name = new ArrayList<>();
        name.add("Preston");
        name.add("Clarrette");
        name.add("Daniele");
        name.add("Maya");
        name.add("John");
        return name;
    }

    private static ArrayList<Integer> findFEvalues(ArrayList<Integer> absences, int numFE) {
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) >= numFE){
                answer.add(absences.get(i));
            }
        }
        return answer;
    }

    private static void shuffle(ArrayList<Integer> absences){
        Random rand = new Random();
        for (int i = 0; i < absences.size(); i++) {
            int randIndex = rand.nextInt(absences.size());
            int temp = absences.get(randIndex);
            absences.set(randIndex, absences.get(i));
            absences.set(i, temp);
        }
    }

    private static void bubbleSort(ArrayList<Integer> absences) {
        for (int i = 0; i < absences.size(); i++) {
            for (int j = 0; j < absences.size(); j++) {
                if (absences.get(i) < absences.get(j)){
                    int temp = absences.get(i);
                    absences.set(i, absences.get(j));
                    absences.set(j, temp);
                }
            }
        }
    }

    private static Set<Integer> countUnique(ArrayList<Integer> absences) {
        Set<Integer> solution = new HashSet<>();
        for (int i = 0; i < absences.size(); i++) {
            solution.add(absences.get(i));
        }
        return solution;
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
    private static ArrayList<Integer> listNonFE(ArrayList<Integer> absences, int numFE) {
        ArrayList<Integer> valueFE = findFEvalues(absences, numFE);
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            if (! valueFE.contains(absences.get(i))){
                answer.add(absences.get(i));
            }
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
        double percent = ((double) countNumAbsences(absences, 0) / (double) countLessThan(absences, maxNum)) * 100.0;
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
        double avg = (double) sum(absences) / (double) absences.size();
        return avg;
    }
}
