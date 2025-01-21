import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Item - a item in a game.
 * 
 * This class is part of the "FRIENDS: An Interactive Videogame" application. 
 * "FRIENDS: An Interactive Videogame" is an interactive application based on the TV show "Friends".  
 *
 * An "Item" represents an object that can exist within the Game. Items can be carried by Players.
 * Each Item has the following: 
 * -A name to identify the item.
 * - A weight that influences gameplay, such as a character's carrying capacity.
 * - A color 
 * - A location that indicates where the item is within the game.
 * 
 * @author Ana Clara Monteiro
 * @version 27/11/2024
 */
public class Item
{
    private String name;
    private float weight;
    private String color;
    private String location; 
    /**
     * Creates an item known as "itemName"
     * @param itemName String
     */
    public Item(String itemName, float itemWeight, String itemColor)
    {
        weight = itemWeight; 
        color = itemColor;
        name = itemName;
    }

    /**
     * @return the item's name (String)
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return the item's room location (String)
     */
    public String getLocation()
    {
        return location;
    }
    
    /**
     * @return the item's weight (float)
     */
    public float getWeight()
    {
        return weight;
    }
    
    
}