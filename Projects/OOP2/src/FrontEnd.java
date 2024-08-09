import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class is meant to facilitate a Gym Helper app. To do this, it makes
 * strategic calls to methods from the StoreExercises, StrengthExercises, and
 * OneRep classes.
 *
 * @author grantrobinson
 *
 */
public class FrontEnd {
    /*
     * StrengthExercises object for the user to select workouts.
     */
    static StrengthExercises strength = new StrengthExercises();
    /*
     * This object is for the user to save their workouts and access their full
     * routine from a file.
     */
    static StoreExercises store = new StoreExercises();
    /*
     * This object is for the user to calculate their one rep max in three
     * different exercises (Deadlift, Squat, and Bench).
     */
    static OneRep one = new OneRep();
    /**
     * Method for running the app.
     *
     * @param args Arguments for the program.
     *
     */
    public static void main(String[] args) {
        // Beginner message to welcome user to app
        System.out.println("Welcome to the Gym Helper!\n");
        // Do while loop to run the menu as long as the user doesn't click "5"
        do {
            mainMenu();
        } while (run(new Scanner(System.in)));
        System.out.print("Thank you for visiting the Gym Helper! :)");
    } // End of main method
    /**
     * This method is meant display a starting menu that prompts the
     * user to run various aspects of the program.
     *
     */
public static void mainMenu() {
    System.out.println("1. Build a workout routine");
    System.out.println("2. Calculate your one-rep max");
    System.out.println("3. See your previous routine");
    System.out.println("4. See your previous maxes");
    System.out.println("5. Exit\n");
    System.out.println("Pick a number between 1 & 5 to select what you"
            + " want to do: ");
} // End of mainMenu method
/**
 * This method is responsible for getting the user's input and running
 * methods based on that. If the user enters a number between 1 and 4, a
 * one of the object's methods will be ran.
 *
 * @param in A scanner object that will read the user's input.
 * @return A boolean value that represents whether or not the menu loop
 *      should have another iteration or not.
 */
public static boolean run(Scanner in) {
    /*
     * Try catch is to make sure that the user inputs an integer. Otherwise
     * an execption is thrown, and a message telling the user that letters
     * aren't allowed is shown.
     */
    try {
        // Checks the user's input and calls various methods based on that
        switch (in.nextInt()) {
        // Runs the Strength segment of the program if the user presses
        // 1
        case 1: strengthMenu();
            break;
            /*
             *  Runs the One Rep segment of the program if the user
             *  presses two.
             */
        case 2: oneMenu();
            break;
            // Will display what's saved inside the file
        case 3: System.out.print(store);
break;
        case 4: System.out.print(one);
            break;
        case 5: print();  // Will try to print elements to files
            return false; // Case 5 ends the program
/*

         *  This message is displayed if a user enters a number that
         *  isn't between 1 & 5.
         */
        default: System.out.println("Only numbers between 1 & 5!\n");
            break;
        } // End of switch case
    } catch (InputMismatchException e) {
        System.out.println("Letters aren't allowed!\n");
    } // End of try-catch
    // True is returned to keep the main loop running
    return true;
} // End of run method
/**
 * This method is for getting the user's data saved within the appropriate
 * files. It will also check if the user wants to save those things into
 * their own file.
 */
public static void print() {
    // Creates a clone StoreExercises object if the user wants to print
    // elements within the file. This will also add those elements to the
    // user's file.
    if (store.printFile() && store.duplicate()) {
        StoreExercises own = store.clone();
        own.setFile();
    } // End of strength store
    // Creates a clone OneRep object if the user wants to print
    // elements within the file. This will also add those elements to the
    // user's file.
    if (one.save() && one.separate()) {
        OneRep copy = one.clone();
        copy.setFile();
    } // End of if/else
}
/**
 * This menu represents the front end for calling the StrengthExercises
 * menu.
 */
public static void strengthMenu() {
    // Workout Builder header
    System.out.println("\nWelcome to the Workout Builder.\n");
    boolean run = true;
    /*
     *  This do while loop will call the StrengthExercises menu until the
     *  user clicks 3, which is the done button for the StrengthExercises
     *  segment.
     */
    do {
        // Switch case for checking the user's input within the menu. Then,
        // An appropriate method will be ran.
        switch (strength.makeMenu()) {
        case 1: strength.workoutQuestions();
// Prints the workout routine

            System.out.println(strength + "\n");
            // If this segment is true, call the save method for store
            // exercises.
            if (strength.save()) {
                /*
                 *  This method will store the current workout to an
                 *  ArrayList.
                 */
                store.saveWorkout(strength);
            } // End of if statement
            break;
        case 2:
            store.displayLast();
            break;
        case 3: run = false;
            break;
        // Filler default to make Java happy
        default: System.out.println("Make a selection");
        } // End of switch case
    } while (run);
} // End of strengthMenu
/**
 * This method will help the user work through the one rep max section of
 * this program.
 */
public static void oneMenu() {
    // Beginner message for the user
    System.out.println("\nWelcome to the One-Rep Max Calculator.\n");
    boolean run = true;
    /*
     *  This do while loop will call the OneRep menu until the user clicks
     *  5, which is the done button for the OneRep Max segment.
     */
    do {
        // Switch case for checking the user's input within the menu. Then,
        // An appropriate method will be ran.
        switch (one.makeMenu()) {
        case 1: one.exAsker();
            break;
        case 2: one.showOne("Bench Press");
            break;
        case 3: one.showOne("Squat");
            break;
        case 4: one.showOne("Deadlift");
            break;
        case 5: run = false;
            break;
        // Filler default case to make java happy
        default: System.out.println("Make a selection");

} // End of switch case
        } while (run);
    } // End of oneMenu method
} // End of FrontEnd class