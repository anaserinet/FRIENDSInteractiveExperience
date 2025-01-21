import java.util.HashSet;
import java.util.HashMap;

/**
 * Class Character - Non Playable Character in a game
 * 
 * This class is part of the "FRIENDS: An Interactive Videogame" application. 
 * "FRIENDS: An Interactive Videogame" is an interactive application based on the TV show "Friends"
 *
 *  Each Character can be interacted with and have the following:
 *  - a room location where the character is specifically located at
 *  - a dialogue that can change throughout the game
 *  
 * @author Ana Clara Monteiro
 * @version 27/11/2024
 */
public class Character
{
    private String name;
    private Room room;
    private static HashSet<String> allCharactersStrings = new HashSet<>();
    private static HashMap<String, Character> nameRelation = new HashMap<>();
    private String currentSpeach;
    
    /**
     * Create a Character named "name" that is located in a certain Room of the game
     * @param characterName (String) The character's name
     * @param inRoom (Room) The Room the character is in
     * @param defaultDialogue (String) The dialogue assigned initially to the character
     */
    public Character(String characterName, Room inRoom, String defaultDialogue)
    {
        name = characterName;
        room = inRoom;
        currentSpeach = defaultDialogue;
        allCharactersStrings.add(name);
        room.addCharacter(characterName);
    }
    
    /**
     * @return Character's name (String)
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return the room the Character is in (Room)
     */
    public Room getRoom()
    {
        return room;
    }
    
    /**
     * @return the dialogue/speach the Character has (String)
     */
    public String getSpeach()
    {
        return currentSpeach;
    }
    
    /** 
     * Static Class method to return the names of the characters created so far in a String
     * @return allCharactersStrings (String) names of all the characters
     */
    public static HashSet<String> getAllCharacters()
    {
        return allCharactersStrings;
    }
    
    /** 
     * Sets a new speech/dialogue for the character
     * @param characterSpeach (String) new speech for the character
     */
    public void setSpeech(String characterSpeach)
    {
        currentSpeach = characterSpeach;
    }
    
    
}
