import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
* This class is meant to help the user build a workout routine. This will be
* done by asking the user various questions about the type of workout, how
* long the workout will be, and how many exercises the user wants within their
* workout. This class can also prompt the user to save these workouts into a
* file, and they can access one of their past workouts with this class as well.
*
* @author grantrobinson
*
*/
public class StrengthExercises implements Cloneable {
/**
* This variable will store the amount of time the workout will take.
*/
protected int time;
/**
* This variable stores the amount of exercises that the workout will have.
*/
protected int exercises;
/**
* The type of workout (Between push, pull & legs) that the user could do.
*/
protected String type;
/**
* This variable holds the amount of reps per exercise set that the user
* will perform in a workout.
*/
private final String reps = "8-12";
/**
* This array holds the chest exercises that the user could perform.
*/
private final String[] chestExercises = {"Incline Bench Press: ",
"DB Bench Press: ", "Chest Fly: "};
/**
* This array holds the shoulder exercises that the user could perform.
*/
private final String[] shoulderExercises = {"DB Side Raise: ",
"DB Shoulder Press: "};
/**
* This array holds the tricep exercises that the user could perform.
*/
private final String[] tricepExercises = {"Dips: ", "Cable Pulldowns: "};
/**
* This array holds the back exercises that the user could perform.
*/
private final String[] backExercises = {"Deadlift: ",
"Lat Chin-Pulldown: ", "Bent Row: ", "Low Row: ", "DB Shrugs: "};

/**
 * This array holds the bicep exercises that the user could perform.
 */
private final String[] bicepExercises = {"Hammer Curls: ",
    "Spider Curls: "};
/**
 * This array holds the leg exercises that the user could perform.
 */
private final String[] legExercises = {"Squats: ", "RDL: ",
    "Leg Extensions: ", "Hamstring Curls: ", "Standing Calf Raise: ",
    "Glute Drive: ", "Hip Abduction: "};
/**
 * String that will ask the user how many exercises they want.
 */
private final String workoutQuest = "How many exercises do you want to do?"
                + "\nEnter a number from %d-%d: ";
/**
 * This ArrayList will hold the user's workout plan. The values will come
 * from the final Array exercises. Push type will be from chestExercises,
 * shoulderExercises, and tricepExercises. Pull type backExercises and
 * bicepExercises; and leg type will be legExercises.
 */
protected ArrayList<String> workout = new ArrayList<String>();
/**
 * This constructor is the default constructor for the class. This will
 * declare the variables to empty values. The user can change these values
 * when using answering the prompts.
 */
public StrengthExercises() {
    this.time = 0;
    this.exercises = 0;
    this.type = "";
} // End of default constructor
/**
 * This constructor sets a new StrengthExercises object's variables to an
 * existing object's variables through deep copies.
 *
 * @param str A StrengthExercises object whose values will be copied and
 *      pasted to the new StrengthExercises object.
 */
public StrengthExercises(StrengthExercises str) {
    this.time = str.time;
    this.exercises = str.exercises;
    this.type = str.type;
    /**
     * This is an enhanced for loop that will go through the elements of
     * str's workout ArrayList and add each element to this current
     * object's ArrayList.
     */
    for (String s: str.workout) {
        this.workout.add(s);
    } // End of for loop
} // End of StrengthExercises clone constructor
/**
 * This method will create a menu with the options of creating a workout or
 * seeing the last saved workout. Users will have the option to pick a
 * number between 1 and 3 when this menu displays.
 *
 * @return An integer value that represents the user's selection.
 */
public int makeMenu() {
    // Variable for running the do while loop
    boolean valid = false;
    /**
     * A do while loop that displays the options and handles what the user
     * enters until they input a number between 1 & 3
     */
    do {
        System.out.println("1. Create a new workout");
        System.out.println("2. See last workout");
        System.out.println("3. Done");
        System.out.println("Pick a number between 1 & 3 to select what you "
                + "want to do: ");
        // Scanner for reading user's input
        Scanner in = new Scanner(System.in);
        // Tries to get the user's input. If the user doesn't enter a
        // number, an exception is thrown.
        try {
            int test = in.nextInt();
            /*
             * If the user entered a number between 1 & 3, it's returned.
             * Otherwise, a message appears asking for valid numbers
             */
            if (test < 1 || test > 3) {
                System.out.println("Only numbers between 1 & 3!\n");
            } else {
                return test;
            } // End of if/else
        } catch (InputMismatchException e) {
            System.out.println("Letters aren't allowed!\n");
        }  // End of try catch
    } while (!valid);
    return 3;
} // End of makeMenu
/**
 * This method will ask the user about what kind of workout that they want
 * to do. The user's selections are saved to approptiate variables.
 */
public void workoutQuestions() {
    boolean valid = false;
    // This do while loop will ask the user what type of workout they want
    // and if their answer is between 1 & 3, the loop stops. Otherwise, it
    // will repeat.

    do {
        System.out.println("\nWhat type of workout do you want?\n");
        System.out.println("1. Push\n2. Pull\n 3. Legs");
        System.out.print("Enter a number 1-3: ");
        Scanner in = new Scanner(System.in);
        // This switch case will take the value from the user and set type
        // to its appropriate value.
        switch (in.next()) {
        case "1": valid = true;
            type = "Push";
            break;
        case "2": valid = true;
            type = "Pull";
            break;
        case "3": valid = true;
            type = "Legs";
            break;
        default:
            System.out.println("Only enter numbers between 1 & 3!");
        } // End of switch case
    } while (!valid);
    workoutHelper(new Scanner(System.in)); // Sets the time of the workout
    setWorkout();
} // End of workoutQuestions
/**
 * This method is a helper method to workout Questions to keep the methods
 * concise in length. This method will ask the user how long they want the
 * workout to take. The value will then be stored in the time variable.
 *
 * @param in A Scanner object that will be used to read the user's input.
 */
private void workoutHelper(Scanner in) {
    boolean valid = false;
    do {
        // Starter questions
        System.out.println("\nHow long do you want your workout to "
                + "take?\n");
        System.out.println("1. 30 minutes\n2. 60 minutes\n3. 90 minutes");
        System.out.print("Enter a number 1-3: ");
        switch (in.next()) {
        case "1": valid = true;
time = 30;
            break;
        case "2": valid = true;
time = 60;
            break;
        case "3": valid = true;
time = 90;
            break;
        default:
            System.out.println("Only enter numbers between 1 & 3!");
        } // End of switch case
} while (!valid);

    workoutHelper2(false);
} // End of workoutHelper
/**
 * This method is a helper method to workout Questions to keep the methods
 * concise in length. This method will ask the user how many exercises they
 * want to do. The value will then be stored in the exercise variable.
 *
 * @param valid A boolean value that represents whether or not the user
 *      typed a valid integer. If this is true, the do while loop stops.
 */
private void workoutHelper2(boolean valid) {
    /**
     * Variables for displaying the amount of exercises available. This
     * will depend on the amount of time that the user wants.
     */
    int exMaxCount = calculateMaxCount();
    int exMinCount = calculateMinCount();
    // This is a do while loop that will ask the user how many exercises
    // they want to do. It will run until the user enters a valid integer.
    do {
        System.out.printf(workoutQuest, exMinCount, exMaxCount);
        // Scanner for checking user input
        Scanner in = new Scanner(System.in);
        // Try catch loop that will set the user's input to the exercises
        // variable if it's valid
        try {
            int count = in.nextInt();
            // If count is between the maximum and minimum exercises for
            // the time, exercises is set to count and the loop is stopped.
            // Otherwise, the user is told to enter a valid number.
            if (count >= exMinCount && count <= exMaxCount) {
                valid = true;
                exercises = count;
            } else {
                System.out.println("Enter a valid number!\n");
            } // End of if/else
        } catch (InputMismatchException e) {
            System.out.println("Enter valid numbers only!\n");
        }  // End of try/catch
    } while (!valid);
} // End of worhoutHelper2
/**
 * This method will calculate the amount of exercises that can be chosen by
 * the user at minimum. This calculation is based on the amount of time that
 * the workout will take. The more time, the higher the amount of exercises.
 *
 * @return An integer value that represents the amount of minimum exercises.
 */
private int calculateMinCount() {

    if (time == 30) {
        return 3;
    } else if (time >= 60) {
        return 5;
    } else {
        return 5;
    }  // End of if/else
} // End of time method
/**
 * This method will calculate the amount of exercises that can be chosen
 * by the user at maximum. This calculation is based on the amount of time that
 * the workout will take. The more time, the higher the amount of exercises.
 *
 * @return An integer value that represents the amount of maximum exercises.
 */
private int calculateMaxCount() {
    if (time == 30) {
        return 4;
    } else if (time == 60) {
        return 6;
    } else {
        return 7;
    }  // End of if/else
} // End of time method
/**
 * This method will set the workout ArrayList. To do this, it calls other
 * methods that form the exercises to go into the ArrayList.
 */
private void setWorkout() {
    workout.clear(); // Clears any elements that could be in the ArrayList
    /*
     * If the user picked a push workout, the push method is called. If
     * it's a pull workout, then the pull method is called. Otherwise, the
     * leg workout method is called.
     */
    if (type.equals("Push")) {
        pushWorkout();
    } else if (type.equals("Pull")) {
        pullWorkout();
    } else {
        legWorkout();
    } // End of if/else
} // End of setWorkout

/**
 * This method will go through the Arrays associated with the push workout
 * and add them to the workout ArrayList. This happens by checking how many
 * exercises the workout will have, and then running the method associated
 * with it. The methods have String parameters, which will take the
 * elements of the push workout Arrays.
 */
private void pushWorkout() {
    // Checks how many exercises there will be, and runs the method that
    // matches it. The parameter variables are the workouts that will be
    // added.
    switch (exercises) {
    case 3: threeExs(chestExercises[0], chestExercises[1],
            shoulderExercises[0]);
break;
    case 4: fourExs(chestExercises[0], chestExercises[1],
            shoulderExercises[0], tricepExercises[0]);
break;
    case 5: fiveExs(chestExercises[0], chestExercises[1],
            shoulderExercises[0], shoulderExercises[1],
            tricepExercises[0]);
break;
    case 6: sixExs(chestExercises[0], chestExercises[1],
            shoulderExercises[0], shoulderExercises[1],
            tricepExercises[0], chestExercises[2]);
break;
    case 7: sevExs(chestExercises[0], chestExercises[1],
            shoulderExercises[0], shoulderExercises[1],
            tricepExercises[0], chestExercises[2],
            tricepExercises[1]);
        break;
    // Filler default
    default: threeExs(chestExercises[0], chestExercises[1],
            shoulderExercises[0]);
    } // End of switch case
} // End of pushWorkout
/**
 * This method will go through the Arrays associated with the pull workout
 * and add them to the workout ArrayList. This happens by checking how many
 * exercises the workout will have, and then running the method associated
 * with it. The methods have String parameters, which will take the
 * elements of the pull workout Arrays.
 */
private void pullWorkout() {
    // Checks how many exercises there will be, and runs the method that
    // matches it. The parameter variables are the workouts that will be
    // added.
    switch (exercises) {
    case 3: threeExs(backExercises[0], backExercises[1], backExercises[2]);
        break;

    case 4: fourExs(backExercises[0], backExercises[1],
            backExercises[2], backExercises[3]);
break;
    case 5: fiveExs(backExercises[0], backExercises[1],
            backExercises[2], backExercises[3], bicepExercises[0]);
break;
    case 6: sixExs(backExercises[0], backExercises[1],
            backExercises[2], backExercises[3], bicepExercises[0],
            backExercises[4]);
break;
    case 7: sevExs(backExercises[0], backExercises[1],
            backExercises[2], backExercises[3], bicepExercises[0],
            backExercises[4], bicepExercises[1]);
break;
        // Filler Default case
    default: threeExs(backExercises[0], backExercises[1],
            backExercises[2]);
    } // End of switch case
} // End of pullWorkout
/**
 * This method will go through the Arrays associated with the leg workout
 * and add them to the workout ArrayList. This happens by checking how many
 * exercises the workout will have, and then running the method associated
 * with it. The methods have String parameters, which will take the
 * elements of the leg workout Arrays.
 */
private void legWorkout() {
    // Checks how many exercises there will be, and runs the method that
    // matches it. The parameter variables are the workouts that will be
    // added.
    switch (exercises) {
    case 3: threeExs(legExercises[0], legExercises[1], legExercises[2]);
        break;
    case 4: fourExs(legExercises[0], legExercises[1],
            legExercises[2], legExercises[3]);
break;
    case 5: fiveExs(legExercises[0], legExercises[1],
            legExercises[2], legExercises[3], legExercises[4]);
break;
    case 6: sixExs(legExercises[0], legExercises[1],
            legExercises[2], legExercises[3], legExercises[4],
            legExercises[5]);
break;
    case 7: sevExs(legExercises[0], legExercises[1],
                legExercises[2], legExercises[3], legExercises[4],
                legExercises[5], legExercises[6]);
        break;
        // Filler default
    default: threeExs(legExercises[0], legExercises[1], legExercises[2]);
    } // End of switch case

} // End of legWorkout
/**
 * This method adds 3 workouts to the workout ArrayList. It does this by
 * adding the parameter variables, and adds a message for the amount of
 * reps and sets.
 *
 * @param ex1 A String variable that represents an exercise that will be
 *      added to the Array.
 * @param ex2 A String variable that represents the second exercise that
 *      will be added to the Array.
 * @param ex3 A String variable that represents the third exercise that
 *      will be added to the Array.
 */
private void threeExs(String ex1, String ex2, String ex3) {
    workout.add(ex1 + "4 x " + reps);
    workout.add(ex2 + "4 x " + reps);
    workout.add(ex3 + "4 x " + reps);
} // End of threeExPush
/**
 * This method adds 4 workouts to the workout ArrayList. It does this by
 * adding the parameter variables, and adds a message for the amount of
 * reps and sets.
 *
 * @param ex1 A String variable that represents an exercise that will be
 *      added to the Array.
 * @param ex2 A String variable that represents the second exercise that
 *      will be added to the Array.
 * @param ex3 A String variable that represents the third exercise that
 *      will be added to the Array.
 * @param ex4 A String variable that represents the fourth exercise that
 *      will be added to the Array.
 */
private void fourExs(String ex1, String ex2, String ex3, String ex4) {
    workout.add(ex1 + "4 x " + reps);
    workout.add(ex2 + "4 x " + reps);
    workout.add(ex3 + "3 x " + reps);
    workout.add(ex4 + "3 x " + reps);
} // End of fourExPush
/**
 * This method adds 5 workouts to the workout ArrayList. It does this by
 * adding the parameter variables, and adds a message for the amount of
 * reps and sets.
 *
 * @param ex1 A String variable that represents an exercise that will be
 *      added to the Array.
 * @param ex2 A String variable that represents the second exercise that
 *      will be added to the Array.
 * @param ex3 A String variable that represents the third exercise that
 *      will be added to the Array.
 * @param ex4 A String variable that represents the fourth exercise that
 *      will be added to the Array.
 * @param ex5 A String variable that represents the fifth exercise that

 *      will be added to the Array.
 */
private void fiveExs(String ex1, String ex2, String ex3, String ex4,
        String ex5) {
    // If the workout will take 60 minutes, the 4th and 5th workouts will
    // have 3 sets. Otherwise, the workouts will take 4 sets.
    if (time == 60) {
        /*
         *  Calls the four Exercise method since it will add exercises
         *  with 4 sets.
         */
        fourExs(ex1, ex2, ex3, ex4);
        // If the workout is a push type, the workout ArrayList will add
        // an exercise in the 3rd index. Otherwise, it is added at the end.
        if (type.equals("Push")) {
            workout.add(3, ex5 + "3 x " + reps);
        } else {
            workout.add(ex5 + "3 x " + reps);
        }
} else { /*
         *  Calls the three Exercise method since it will add exercises
         *  with 4 sets.
         */
        threeExs(ex1, ex2, ex3);
        workout.add(ex4 + "4 x " + reps);
        workout.add(ex5 + "4 x " + reps);
    } // End of if/else
} // End of fiveExPush
/**
 * This method adds 6 workouts to the workout ArrayList. It does this by
 * adding the parameter variables, and adds a message for the amount of
 * reps and sets.
 *
 * @param ex1 A String variable that represents an exercise that will be
 *      added to the Array.
 * @param ex2 A String variable that represents the second exercise that
 *      will be added to the Array.
 * @param ex3 A String variable that represents the third exercise that
 *      will be added to the Array.
 * @param ex4 A String variable that represents the fourth exercise that
 *      will be added to the Array.
 * @param ex5 A String variable that represents the fifth exercise that
 *      will be added to the Array.
 * @param ex6 A String variable that represents the sixth exercise that
 *      will be added to the Array.
 */
private void sixExs(String ex1, String ex2, String ex3, String ex4,
        String ex5, String ex6) {
    fiveExs(ex1, ex2, ex3, ex4, ex5);
    // If time is 60, the workout added will have 3 sets. Otherwise, the
    // workout will have 4 sets.

    if (time == 60) {
        // If the workout is a push one, the chest fly exercise will be
        // added next to the other chest ones. Otherwise, the workout will
        // be added at the end.
        if (type.equals("Push")) {
            workout.add(2, ex6 + "3 x " + reps);
        } else {
            workout.add(ex6 + "3 x " + reps);
        } // End of if/else
    } else {
        // If the workout is a push type, the chest fly exercise will be
        // added next to the other chest ones. Otherwise, the workout will
        // be added at the end.
        if (type.equals("Push")) {
            workout.add(2, ex6 + "4 x " + reps);
        } else {
            workout.add(ex6 + "4 x " + reps);
        } // End of if/else
    }
} // End of sixExPush
/**
 * This method adds 7 workouts to the workout ArrayList. It does this by
 * adding the parameter variables, and adds a message for the amount of
 * reps and sets.
 *
 * @param ex1 A String variable that represents an exercise that will be
 *      added to the Array.
 * @param ex2 A String variable that represents the second exercise that
 *      will be added to the Array.
 * @param ex3 A String variable that represents the third exercise that
 *      will be added to the Array.
 * @param ex4 A String variable that represents the fourth exercise that
 *      will be added to the Array.
 * @param ex5 A String variable that represents the fifth exercise that
 *      will be added to the Array.
 * @param ex6 A String variable that represents the sixth exercise that
 *      will be added to the Array.
 * @param ex7 A String variable that represents the seventh exercise that
 *      will be added to the Array.
 */
private void sevExs(String ex1, String ex2, String ex3, String ex4,
        String ex5, String ex6, String ex7) {
    sixExs(ex1, ex2, ex3, ex4, ex5, ex6);
    workout.add(ex7 + "3 x " + reps);
} // End of sevExPush
@Override
public String toString() {
    return "\nYour created workout (" + this.type + "):\n"
            + display(this.workout);
} // End of toString
/**
 * This method will format the workout ArrayList in a way that's more

 * presentable to users.
 *
 * @param lst An ArrayList that will be formatted.
 * @return A new String that is the formatted version of the ArrayList.
 */
protected String display(ArrayList<String> lst) {
    String res = "";
    for (int i = 0; i < lst.size(); i++) {
        // If statement that checks if the index isn't equal to the
        // position of the last element. If it isn't, then a comma is added.
        // Otherwise, there's no comma after the arraylist element.
        if (i < lst.size() - 1) {
            res += lst.get(i) + ", ";
        } else {
            res += lst.get(i);
        } // End of if/else
    } // End of for loop
    return res;
} // End of display
/**
 * This method will ask the user of they want to save the workout that they
 * just created.
 *
 * @return A boolean value that represents the user's decision to save the
 *      workout. If yes, true is returned. If no, false is returned.
 */
public boolean save() {
    // Variable for running the loop
    boolean valid = false;
    /*
     *  This do while loop will ask the user if they want to save their
     *  workout. If they type 1 (For yes) or 2 (for no), the loop will end.
     *  Otherwise, they will be asked to retype their answer.
     */
do {
// Menu
        System.out.println("Would you like to save this workout?");
        System.out.printf("1. Yes\t2. No\n"); // Yes or no
        System.out.print("Type 1 for Yes or 2 for No: ");
        // Scanner for reading input
        Scanner in = new Scanner(System.in);
        /*
         * Switch case that checks the user's input. If it's 1 or 2, a
         * boolean value is returned. Otherwise, a message telling the
         * user to enter a new value will appear.
         */
        switch (in.next()) {
        case "1": return true;
        case "2": return false;
        default: System.out.print("You can only enter 1 or 2!");

} // End of switch case
        } while (!valid);
        return false; // Filler return for no error
    } // End of save method
    @Override
    public StrengthExercises clone() {
        return new StrengthExercises(this);
    } // End of clone method
} // End of StrengthExercises