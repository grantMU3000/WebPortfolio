import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class is supposed to help the user calculate their one rep max for
 * three exercises: the deadlift, squat, and bench press. The user will be able
 * to enter a weight between 1 and 1000 lbs and their reps will range from
 * 1-10. To calculate the max, the O'Conner Formula is used.
 *
 * @author grantrobinson
 *
 */
public class OneRep implements UserExp, Cloneable {
    /**
     * This will keep track of the weight that the user enters for a specific
     * exercise. This will be used to calculate the max.
     */
    private int weight;
    /**
     * This will keep track of the amount of repetitions that the user did for
     * a specific exercise. This will also be used to calculate the max.
     */
    private int reps;
    /**
     * This will keep track of the workout that the user is trying to get the
     * max of.
     */
    private String type;
    /**
     * This ArrayList will store all of the deadlift maxes that the user
     * calculates.
     */
    private ArrayList<Integer> allDeadLifts = new ArrayList<Integer>();
    /**
     * This ArrayList will store all of the bench press maxes that the user
     * calculates.
     */
    private ArrayList<Integer> allBenchPress = new ArrayList<Integer>();
    /**
     * This ArrayList will store all of the squat maxes that the user
     * calculates.
     */
    private ArrayList<Integer> allSquats = new ArrayList<Integer>();
    /**
     * This file will be used to store all of the One Rep maxes that the user
     * calculates. The file will be formatted so that all of the maxes are
     * presentable.
     */
    private File file = new File("oneMax.txt");
/**
 * This construcor is a default constructor. Its use is to create an object
 * that allows for the calculation of maxes. That's why all of the
 * variables are given default values.
 */
public OneRep() {
    weight = 0;
reps = 0;
    type = "";
} // End of OneRep constructor
/**
 * This constructor is a copy constructor that deep copies a given OneRep
 * object, and sets the values to a new one.
 *
 * @param one A OneRep object that will be used for setting a new OneRep
 *      copy object.
 */
public OneRep(OneRep one) {
    /*
     * The next three enhanced for loops will add elements from the given
     * OneRep object, and add them to a new ArrayList that matches the
     * workout performed.
     */
    for (int dedlft: one.allDeadLifts) {
        this.allDeadLifts.add(dedlft);
    } // End of deadlift ArrayList deep copy
    for (int bench: one.allBenchPress) {
        this.allBenchPress.add(bench);
    } // End of bench press ArrayList deep copy
    for (int squat: one.allSquats) {
        this.allSquats.add(squat);
    } // End of squat ArrayList deep copy
} // End of OneRep copy
@Override
public int makeMenu() {
    // Variable for running the loop
    boolean valid = false;
    /*
     * This do while loop will first display menu options. Then, the user
     * is prompted to select an option. The user input is then checked to
     * make sure that the  they enter a number from 1-5. This will then be
     * returned. Otherwise, the user is told to reenter the value.

*/ do {
        // Menu options
        System.out.println("1. Calculate a one-rep max");
        System.out.println("2. See your saved Bench Press maxes");
        System.out.println("3. See your saved Squat maxes");
        System.out.println("4. See your saved Deadlift maxes");
        System.out.println("5. Done\n");
        // Prompts the user to enter a number that matches
        // what they want to do
        System.out.print("Type a number from 1-5 to do"
                + " what you want to do: ");
        // Scanner for reading input
        Scanner in = new Scanner(System.in);
        // Switch case that will check the user's input.
        switch (in.next()) {
        case "1": return 1;
        case "2": return 2;
        case "3": return 3;
        case "4": return 4;
        case "5": return 5;
        default:
            System.out.println("Only enter numbers between 1 & 5!");
        } // End of switch case
    // End of try catch
    } while (!valid);
    return 0; // Filler return to satisfy method
} // End of makeMenu method
/**
 * This method will ask the user about what specific exercise they want to
 * calculate a one rep max for.
 */
public void exAsker() {
    // Variable for the loop
    boolean valid = false;
    /*
     * This do while loop will first display menu options. Then, the user
     * is prompted to select an option. The user input is then checked to
     * make sure that the  they enter a number from 1-3. This will then be
     * used for the type of workout done.
*/ do {
        // Menu options
        System.out.println("\nWhat type of exercises did you want to "
+ "calculate?\n");

        System.out.println("1. Bench Press");
        System.out.println("2. Squat");
        System.out.println("3. Deadlift");
        // Prompts the user to enter a number that matches
        // what they want to do
        System.out.print("Enter a number from 1-3 to pick an exercise: ");
        // Scanner for reading input
        Scanner in = new Scanner(System.in);
        // Switch case that will check the user's input and use their
        // answer to set the type of exercise they're calculating.
        switch (in.next()) {
        case "1": valid = true;
            type = "Bench Press";
            break;
        case "2": valid = true;
            type = "Squat";
            break;
        case "3": valid = true;
            type = "Deadlift";
            break;
            // This is run when the user enters anything that isn't 1,
            // 2, or 3
        default: System.out.println("Only enter numbers between 1 & 3!");
        } // End of switch case
    } while (!valid);
    weightAsker(); // Runs the weightAsker after getting the exercise type
} // End of exAsker method
/**
 * This method will get the amount of weight in pounds for the user's lift.
 * This lift must be between 1 & 1000 lbs.
 */
private void weightAsker() {
    boolean valid = true;
    // Do while loop that will run until the user enters a valid input.
    do {
        Scanner in = new Scanner(System.in);
        // Try/catch to get the user's weight for the lift. If the
        // user enters a value with more than just numbers, a message
        // will appear telling them to enter correct values.
        try {
            System.out.println("How much weight did you lift "
                    + "(Between 1 & 1000 lbs)?");
            int test = in.nextInt();
            /*
             * This if statement will check if the user's input is between
             * 1 and 1000. If it is, the weight is set to the input. Else,
             * an exception will be thrown
             */
            if (test >= 1 && test <= 1000) {
                valid = false;
                weight = test;
            } else {
throw new InputMismatchException();

} // End of if/else
        } catch (InputMismatchException e) {
            // Message prints if the user enters a value that has more than
            // numbers
            System.out.println("Only enter numbers between 1 & 1000!");
        } // End of try/catch
    } while (valid);
    oneRepAsker();
} // End of weightAsker method
/**
 * This method will get the amount of reps that the user performed during
 * the exercise.
 */
private void oneRepAsker() {
    boolean valid = false;
    // Do while loop that will run until the user enters a valid input.
    do {
        Scanner in = new Scanner(System.in);
        // Try/catch to get the user's reps for the lift. If the
        // user enters a value with more than just numbers, a message
        // will appear telling them to enter correct values.
        try {
            System.out.println("How many reps did you perform for the "
                    + "lift (Between 1 & 10 reps)?");
            int test = in.nextInt();
            /*
             * This if statement will check if the user's input is between
             * 1 and 10. If it is, the reps are set to the input. Else,
             * an exception will be thrown.
             */
            if (test >= 1 && test <= 10) {
                valid = true;
                reps = test;
            } else {
                throw new InputMismatchException();
            } // End of if/else
        } catch (InputMismatchException e) {
            // Message prints if the user enters a value that has more than
            // numbers
            System.out.println("Only enter numbers between 1 & 10!");
        } // End of try/catch
    } while (!valid);
    calculate();
} // End of oneRepAsker method
/**
 * This method will calculate the user's one rep max for a given exercise.
 * The result will be saved to an appropriate ArrayList that holds the
 * exercises. This method will also display the resulting max
 *

 * @return An Integer value that represents the one rep max.
 */
private void calculate() {
    // The O'conner formula is used on the weight and the max is
    // initialized.
    double max = weight *  (1 + (0.025 * reps));
    // Will check what type of exercise the user is calculating and saves
    // to the ArrayList associated with the exercises
    if (type.equals("Bench Press")) {
        allBenchPress.add((int) max);
    } else if (type.equals("Squat")) {
        allSquats.add((int) max);
    } else {
        allDeadLifts.add((int) max);
    } // End of if/else
    // This will display the user's one rep max for the given exercise.
    System.out.printf("Your approximate one rep max for %s: %.2f lbs\n\n",
            type, max);
    // return max;
} // End of calculate method
/**
 * This method is meant to help show one set of maxes that the user already
 * has saved.
 *
 * @param ex A string variable that represents the exercise that the user
 *      is trying to see.
 */
public void showOne(String ex) {
    // Switch case for checking what exercise the user is trying to find
    // the max of. Depending on the answer, the displayLast method is
    // called with the appropriate exercise ArrayList as the parameter.
    switch (ex) {
    case "Bench Press": displayLast(ex, allBenchPress);
        break;
    case "Squat": displayLast(ex, allSquats);
        break;
    case "Deadlift": displayLast(ex, allDeadLifts);
        break;
    default:
        System.out.print("No exercise selected.");
    } // End of switch case
} // End of showOne method
/**
 * This method will work to display the saved maxes for a particular
 * exercise in a formatted manner. If the user doesn't have any maxes
 * saved, then a "Nothing saved yet" message will appear.

 *
 * @param ex A String value that represents the exercise that will be
 *      displayed.
 * @param maxes An ArrayList of Integers that represents the one rep maxes
 *      that will be displayed to the user.
 */
private void displayLast(String ex, ArrayList<Integer> maxes) {
    // Checks if the maxes ArrayList has elements. If so, then the maxes
    // are printed. Otherwise, a message appears saying the user hasn't
    // saved any maxes.
    if (maxes.size() > 0) {
        System.out.printf("Your saved %s maxes (in lbs): ", ex);
        // For loop that goes through the maxes ArrayList and prints the
        // elements.
        for (int i = 0; i < maxes.size(); i++) {
            // If the element isn't the last one in the ArrayList, there's
            // a comma printed after it. Otherwise, there will be a period
            // and a new line.
            if (i < maxes.size() - 1) {
                System.out.print(maxes.get(i) + ", ");
            } else {
                System.out.println(maxes.get(i) + ".\n");
            } // End of if/else
        } // End of for loop
    } else {
        System.out.printf("You don't have any %s maxes saved.\n\n", ex);
    } // End of if/else
} // End of displayLast
@Override
public boolean save() {
    // Checks if all of the exercise ArrayLists are empty. If so, false is
    // returned.
    if (allSquats.size() == 0 && allBenchPress.size() == 0
            && allDeadLifts.size() == 0) {
        return false;
    } // End of if statement
    // Try catch block that will try to print elements to a file. If the
    // file cannot be found, an exception is thrown, and the user is
    // notified.
    try {
        printElem();
    } catch (FileNotFoundException f) {
        System.out.println("File cannot be found");
    } // End of try catch
    return true;
} // End of save method

/**
 * This method is supposed to print all of the maxes into a file. If the
 * file cannot be found or there are no maxes for a given exercise, the
 * user is notified.
 *
 * @throws FileNotFoundException An exception that's thrown when the file
 *     cannot be found.
 */
public void printElem() throws FileNotFoundException {
    // Initializes a PrintWriter object for printing within a file
    PrintWriter inF = new PrintWriter(file);
    inF.println("Your One-Rep Maxes (in lbs): \n");
    // Bench press segment
    inF.print("Bench Press: ");
    inF.println(printElem2("Bench Press", allBenchPress));
    // Squat segment
    inF.print("Squat: ");
    inF.println(printElem2("Squat", allSquats));
    // Deadlift segment
    inF.print("Deadlift: ");
    inF.println(printElem2("Deadlift", allDeadLifts));
    inF.close();
} // End of printElem
/**
 * This method is supposed to make sure that the ArrayList isn't empty, and
 * will print an empty message if there is. This method is meant to print
 * follows the exercise in the file line. e.g. this method saves what's
 * printed after deadlift.
 *
 * @param ex A String value that represents the exercise that is being
 *      printed.
 * @param maxes An ArrayList of Integers that stores the maxes for a given
 *      exercise. The elements within this ArrayList will be printed.
 * @return A String value that represents what will be printed to the file.
 */
private String printElem2(String ex, ArrayList<Integer> maxes) {
    // A variable for storing what's supposed to be printed to the file
    String result = "";
    // If the ArrayList has elements, they are printed to the file.
    // Otherwise, an empty message will appear.
    if (maxes.size() > 0) {
        // Calls another method that handles the printing of the ArrayList
        // elements.
        result += (printElem3(maxes));
    } else {
        result += String.format("No %s maxes calculated.\n", ex);

} // End of if/else
    return result;
} // End of printElem2
/**
 * This method is meant to add the elements from the appropriate exercise
 * ArrayList into a String for printing.
 *
 * @param maxes An ArrayList of Integer whose elements will be added to the
 *      String.
 * @return A String that has all of the elements of the maxes ArrayList.
 */
private String printElem3(ArrayList<Integer> maxes) {
    String result = "";
    // For loop that goes through the maxes ArrayList and adds the
    // elements to the result String.
    for (int i = 0; i < maxes.size(); i++) {
        // If the element isn't the last one in the ArrayList, there's
        // a comma printed after it. Otherwise, there will be a period
        // and a new line.
        if (i < maxes.size() - 1) {
            result += (maxes.get(i) + ", ");
        } else {
            result += (maxes.get(i) + ".\n");
        } // End of if/else
    } // End of for loop
    return result;
}  // End of printElem3
/**
 * This method will ask the user if they want to create a copy of the one
 * rep max file to keep for themselves. If the user says yes, then a
 * second copy is made. Otherwise, no copy is made.
 *
 * @return A boolean value that represents the user's decision to create
 *      another file.
 */
public boolean separate() {
    boolean valid = false;
    do {
        System.out.println("Would you like to save the one-rep maxes to "
                + "your own separate file?");
        System.out.printf("1. Yes\t2. No\n"); // Yes or no
        System.out.print("Type 1 for Yes or 2 for No: ");
        // Scanner for reading input
        Scanner in = new Scanner(System.in);
        /*
         * Switch case that checks the user's input. If it's 1 true is
         * returned. If it's two, false is returned. Otherwise, a message

         * telling the user to enter a new value will appear.
         */
        switch (in.next()) {
        case "1": return true;
        case "2": return false;
        default: System.out.print("You can only enter 1 or 2!");
        } // End of switch case
    } while (!valid);
    return false;
} // End of separate
@Override
public OneRep clone() {
    // Returns a new OneRep object that is a deep copy of the current one
    return new OneRep(this);
} // End of clone method
/**
 * This method is supposed to get the file name from the user. After that,
 * Elements from the current exercise ArrayLists will be printed into the
 * user's file. The purpose of the method is to help give the user an
 * opportunity to save their work into another file in case they don't want
 * to use the default oneMax.txt file.
 */
public void setFile() {
    /*
     * Gets the name of the file from the user.
     */
    System.out.println("What is the name of your file?");
    Scanner in  = new Scanner(System.in);
    file = new File(in.next());
    // Will try to print the elements into the file. If the file isn't
    // found, a message is displayed.
    try {
        printElem();
    } catch (FileNotFoundException w) {
        System.out.println("Your file cannot be found");
    } // End of try catch
    // Save message for the user
    System.out.println("Your maxes have been saved to a new file.\n");
} // End of setFile
@Override
public String toString() {
    return display();
} // End of toString
@Override
public String display() {
    String res = "";
    try {
Scanner inF = new Scanner(file);

// While loop that adds a line from a file to the String.
            while (inF.hasNextLine()) {
                res += inF.nextLine() + "\n";
            } // End of while loop
            return res;
        } catch (FileNotFoundException e) {
            return "File not found.";
        } // End of try catch
    } // End of display
} // End of OneRep class.