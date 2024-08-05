import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class is meant to help the user store their workouts into a File. The
 * class will also enable the user to access their last saved file, if they
 * have one.
 * @author grantrobinson
 *
 */
public class StoreExercises extends StrengthExercises implements Cloneable {
    /**
     * This ArrayList stores the workouts that the user created.
     */
    private ArrayList<StrengthExercises> workoutPlan =
            new ArrayList<StrengthExercises>();
    /**
     * This file will be used to save the workouts for printing. The file is
     * also useful if the user wants to see what workouts are already saved
     * into a file.
     */
    private File file = new File("workouts.txt");
    /**
     * This is a simple default constructor.
     */
    public StoreExercises() {
    } // End of StoreExercises
    /**
     * This constructor creates a deep copy of the workoutPlan ArrayList.
     *
     * @param ste A StoreExercises object that will be deep copied.
     */
    public StoreExercises(StoreExercises ste) {
        // For loop that adds a workoutPlan elements from Ste to the current
        // object's ArrayList
        for (StrengthExercises str: ste.workoutPlan) {
            this.workoutPlan.add(str.clone());
        } // End of for loop */
    } // End of clone constructor
    /**
     * This method should copy elements from within the file that holds
     * workouts. If nothing is within the file, then nothing will be displayed.
     * This method should only work if the user has used this program before,
     * and saved their workouts to a file that time.
     *
     * @return A String value that either holds the elements of the array, a
     *      "workouts not found message", or a File can't be fount message.
     */

    private String display() {
    try {
        // Scanner for reading the file elements
        Scanner inF = new Scanner(file);
        /* Checks if the file has elements in it. If so, then the file
         * workouts will be displayed. Otherwise, a "nothing is saved"
         * message will appear.
         */
        if (inF.hasNext()) {
            String res = "";
            // While loop for adding the elements from within the file to
            // a String
            while (inF.hasNextLine()) {
                res += inF.nextLine() + "\n";
            } // End of while loop
            return res;
        } else {
            return "Your previous workouts cannot be found.";
        } // End of if/else
    } catch (FileNotFoundException e) {
        // File not found message displays if the file can't be found
        return "File can't be found.\n";
    } // End of trycatch
} // End of display method
/**
 * This method is meant to display the last saved workout. This happens
 * by accessing the workoutPlan ArrayList. If nothing is in the ArrayList,
 * the user will be notified.
 */
public void displayLast() {
    // If no workouts are saved, a nothing message is printed. Otherwise,
    // The last workout saved is printed.
    if (workoutPlan.size() == 0) {
        System.out.println("No workouts saved yet.\n");
    } else {
        // Temporary object that's used for displaying the last workout.
        StrengthExercises temp = workoutPlan.get(workoutPlan.size() - 1);
        System.out.printf("Your last saved workout (%s):\n", temp.type);
        // Uses the parent display method to show the last saved workout
        System.out.println(
                workoutPlan.get(workoutPlan.size() - 1).display(
    } // End of if/else
} // End of displayLast
temp.workout) + "\n");
/**
 * This method will print elements of the workoutPlan ArrayList to a file.
 * If the ArrayList has elements, the elements will be printed.
 *
 * @return A boolean value that represents whether or not workoutPlan has
 *      elements.
 */

public boolean printFile() {
    // If the workoutPlan arrayList has elements, printElem is called and
    // the elements will be printed into a file. Otherwise, false is
    // returned.
    if (workoutPlan.size() > 0) {
        // Will catch a file not found exception if the file cannot be
        // found.
        try {
            printElem();
        } catch (FileNotFoundException w) {
            // This message is displayed if the file cannot be found
            System.out.print("The file cannot be found");
            return false;
        } finally {
            return true;
        } // End of try/catch/finally
    } else {
        return false;
    } // End of if/else
} // End of printFile method
/**
 * This method will ask the user of they want to save their workouts  to
 * their own file.
 *
 * @return A boolean value. If the user wants to use their own file for
 *      printing, true is returned. Otherwise, false is returned.
 */
public boolean duplicate() {
    // Variable for running the loop
    boolean valid = false;
    /*
     *  This do while loop will ask the user if they want to save their
     *  workouts. If they type 1 (For yes) or 2 (for no), the loop ends.
     *  Otherwise, they will be asked to retype their answer.
     */
do {
// Menu
        System.out.println("Would you like to save the workouts to your "
                + "own separate file?");
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
    return false; // Filler return for no error
} // End of duplicate method
/**
 * This method is meant to be a helper method that prints the elements to a
 * file.
 *
 * @throws FileNotFoundException An exception is thrown if the file cannot
 *      be found.
 */
private void printElem() throws FileNotFoundException {
    // PrintWriter for writing within the file
    PrintWriter inF = new PrintWriter(file);
    // This saved message will be at the top of the file
    inF.printf("Your workouts saved: %d\n\n",  workoutPlan.size());
    // For loop for printing elements within the file
    for (int i = 0; i < workoutPlan.size(); ++i) {
        // Temporary object for writing within the file
        StrengthExercises temp = workoutPlan.get(i);
        inF.printf("Workout %d (%s): This workout has %d exercises"
                + " and should take %d minutes\n", (i + 1),
                temp.type, temp.exercises, temp.time);
        inF.println(temp.display(temp.workout) + "\n");
    } // End of for loop
    inF.close();
} // End of printElem
/**
 * This method is supposed to get the file name from the user. After that,
 * Elements from the current WorkoutPlan ArrayList will be printed into the
 * user's file. The purpose of the method is to help give the user an
 * opportunity to save their work into another file in case they don't want
 * to use the default workouts.txt file.
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
    System.out.println("Your workouts have been saved to a new file.\n");

} // End of setFile().
    /**
     * This method is meant to save a workout to the workoutPlan ArrayList. To
     * do this, the current StrengthExercises object is cloned and then added
     * to the ArrayList.
     *
     * @param str A StrengthExercises object that will be copied into the
     *      ArrayList.
     */
    public void saveWorkout(StrengthExercises str) {
        workoutPlan.add(new StrengthExercises(str.clone()));
    } // End of saveWorkout
    @Override
    public StoreExercises clone() {
        return new StoreExercises(this);
    } // End of clone method
    @Override
    public String toString() {
        // One-liner method to keep its implementation hidden
        return this.display();
    } // End of toString
} // End of StoreExercises