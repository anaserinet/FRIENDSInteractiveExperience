/**
 *  This class is part of the "FRIENDS: An Interactive Videogame" application. 
 * "FRIENDS: An Interactive Videogame" is an interactive application based on the TV show "Friends".  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes, Ana Clara Monteiro
 * @version 27/11/2024
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help" , "grab", "drop", "talk-to", "backtrack", "check", "get-finale",
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
