
/**
 * Class Backpack - a backpack that a Player uses to carry the Items they grab
 * 
 * This class is part of the "FRIENDS: An Interactive Videogame" application. 
 * "FRIENDS: An Interactive Videogame" is an interactive application based on the TV show "Friends".
 *
 * A "Subtask" Represents a single step or action required to complete a Task.
 * 
 * There are two types of Subtasks:
 * - Character-related Subtasks: Require interaction with a specific Character.
 * - Item-related Subtasks: Require interaction with a specific Item.
 * 
 * @author Ana Clara Monteiro 
 * @version 27/11/2024
 */
public class Subtask
{
    private String description; 
    private Character characterRelated;
    private Item itemRelated;

    /**
     * Creates a Task described as "taskDescription" if it's related to a Character
     * @param taskDescription (String) description of the subtask that's going to be printed to the terminal
     * @param item (Character) Character with which the Player has to interact with in order to complete the subtask 
     */
    public Subtask(Character character)
    {
        characterRelated = character;
        description = "talk-to " + characterRelated.getName();
    }
    
    /**
     * Creates a SubTask described as "subtaskDescription" if it's related to a Character
     * @param taskDescription (String) description of the subtask that's going to be printed to the terminal
     * @param character (Character) Character with which the Player has to interact with in order to complete the subtask 
     */
    public Subtask(Item item)
    {
        itemRelated = item;
        description = "grab " + itemRelated.getName();
    }
    
    /**
     * @return the subtask's description (String)
     */
    public String getDescription()
    {
        return description;
    }
    
     /**
     * @return the Character associated with completing the subtask (Character)
     */
    public Character getCharacterRelated()
    {
        return characterRelated;
    }
    
     /**
     * @return the Item associated with completing the subtask (Item)
     */
    public Item getItemRelated()
    {
        return itemRelated;
    }
    
     /**
     * Updates the dialogue of the Character that is associated 
     * with completing this subtask (only for Character-related subtasks)
     */
    public void switchCharactersDialogue(String newDialogue)
    {
        characterRelated.setSpeech(newDialogue);
    }

}
