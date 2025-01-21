import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Class Room - a room in a game.
 *
 * This class is part of the "FRIENDS: An Interactive Videogame" application. 
 * "FRIENDS: An Interactive Videogame" is an application based on the TV show "Friends".   
 * 
 * A "Room" can be of 2 types: 
 * -Normal Room-: A "Room" represents one location in the scenery of the game.  It is 
 *   connected to other rooms via exits.  For each existing exit, the room 
 *   stores a reference to the neighboring room.
 * -Random Room--:  A special type of room that initially exists as a placeholder 
 *   but transforms into a randomly chosen normal room during gameplay.
 * 
 * Each "Room" can have the following:
 * - Exits: Links to other rooms, mapped by directions (ex: "north" or "west").
 * - Items: Objects the player can interact with or collect (Items is a separate class).
 * - Characters: NPCs that may inhabit the room.
 * - Attributes: Descriptions and other unique identifiers (ex: type of room).
 *    
 * @author  Michael Kölling and David J. Barnes, Ana Clara Monteiro
 * @version 27/11/2024
 */

public class Room 
{
    protected String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList <Item> items;
    private String itemsString;
    private ArrayList<String> characters;
    protected static HashMap<String, Room> allRooms;
    
    
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".    
    * @param description (String) The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        characters = new ArrayList<>();
        itemsString = "";
    }
    
    /**
     * Define an exit from this room.
     * @param direction (String) The direction of the exit.
     * @param neighbor (Room) The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * @return A long description of this room
     * If the room has no roomType, return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * If the room is of roomType "random" return a description in the form:
     *      
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + "Items: " + getItems();
    }

    /**
     * @return a String describing the room's exits, for example
     * "Exits: north west".
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        if(exits!=null){
            Set<String> keys = exits.keySet();
            for(String exit : keys) {
                returnString += " " + exit;
            }
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Adds an Item to its respective Room 
     * @param newItem Item that is added to Room
     */
    public void addItem(Item newItem)
    {
        items.add(newItem);
    }
    
    /**
     * "@returns the Items in the Room (their names) in a String 
     */
    public String getItems()
    {
        String returnString = "";
            if(items!=null){
            for(Item item : items) {
                returnString = returnString + item.getName() + " ";
            }
        }
        return returnString;
    }
    
    /**
     * @return an Array List with all of the items in the Room 
     */
    public ArrayList getItemsArray()
    {
        return items;
    }
    
    /**
     * @return an Array List with the names of all of the Characters in the Room 
     */  
    public ArrayList getCharactersArray()
    {
        return characters;
    }
    
    /**
     * Adds a Character's name to the Character's  respective Room 
     * @param characterName (String) name of the character
     */
    public void addCharacter(String characterName)
    {
        characters.add(characterName);
    }
    
    /**
     * Checks if an item in the Room matches the name inputed. If such Item does not exist, it returns null
     * @param name (String)  name of the room you are trying to find 
     * @return (Item)   item that matches inputed name
     */
    public Item findItemByName(String name)
    {
        for(Item item : items) {             //loops through all items in room
            if(item.getName().trim().equals(name)){  //if the an item in the loop has the same name as the mame we're looking for, then we found our item
                return item;
            }    
        }
        return null;   
    }   

    /**
     * @returns the Characters in the Room (their names) in a String 
    */
    public String getStringCharactersInRoom()
    {
       String charactersInRoomString = "";
       for(String character: characters){
           charactersInRoomString = charactersInRoomString + character + " ";
       }
       return charactersInRoomString;
    }
    
    /**
     * @returns the Room that the user wants to go to. Returns null if the room does not exist 
    */
    public Room getNextRoom(String direction)
    {
        return getExit(direction);
    }
    
    public static void setRooms(HashMap<String, Room> Rooms)
    {
        allRooms = Rooms;
    }
    
    public HashMap<String, Room> getAllRooms()
    {
        return allRooms;
    }
}

