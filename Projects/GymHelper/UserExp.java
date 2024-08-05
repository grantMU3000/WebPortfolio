/**
 * This is a simple interface that is made to help implement the Gym Helper
 * app. The methods within this interface must be implemented because they all
 * help with key aspects of the program. The menu is will help the user make
 * decisions, the display method will help with showing the results of their
 * selections, and the save method will help with saving things within a file.
 *
 * @author grantrobinson
 *
 */

public interface UserExp {
    /**
     * This method creates a menu for the user to interact with. This helps
     * with the flow of the program.
     *
     * @return An integer value that represents the option that the user
* selects.
     */
    public abstract int makeMenu();
    /**
     * This method is meant to display the object's values that were generated
     * by the user's selections from within a file.
     *
     * @return A String value that represents what's in the file.
     */
    public abstract String display();
    /**
     * This method is supposed to help save all of the user's values generated
     * by the user to a file. If the user has any values, they will be saved to
     * a file.
     *
     * @return A boolean value that represents whether or not the user has any
     *      data stored.
     */
    public abstract boolean save();
}