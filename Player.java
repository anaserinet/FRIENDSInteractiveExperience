import java.util.HashSet;

/**
 * Class Player - a Player that plays the game 
 * 
 * This class is part of the "FRIENDS: An Interactive Videogame" application. 
 * "FRIENDS: An Interactive Videogame" is an interactive application based on the TV show "Friends".
 *
 *A "Player" represents the real-life person who is playing this game as the protagonist.
 *
 *A Player has the following:
 * - a money balance 
 * - a backpack where they can add their items
 * 
 * @author Ana Clara Monteiro 
 * @version 27/11/2024
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private Backpack backpack;
    private int moneyPocket;

    /**
     * Create a player in your game
     * @param playerName (String) the player's name
     */
    public Player(String playerName)
    {
        // initialise instance variables
        name = playerName;
        moneyPocket = 0;
        backpack = new Backpack();
    }
    /**
     * Adds item to a player's backpack 
     * @param item   the item that will be added to the backpack
     */
    public void addItemPlayer(Item item)
    {
         backpack.addItem(item);   
    }
    
    /**
     * Checks if an item can be added to your backpack 
     * @param item (Item) the item that we are checking
     * @return true if item can be added to backpack, falase otherwise
     */
    public boolean checkIfItemCanAdd(Item item)
    {
         boolean canAdd = backpack.checkIfItemCanAddBackpack(item);   
         return canAdd;
    }
    /**
     * @return the maximum weight that the player's backpack can carry (float)
     */
    public float maxBackpackWeight()
    {
        return backpack.getMaxWeight();
    }
    
    /**
     * @return a HashSet<Item>  of the items that are in the player's backpack
     */
    public HashSet<Item> getItemsWithPlayer()
    {
        return backpack.getItemsInBackpack();
    }
    
    /**
     * @return a String of the items that are in the player's backpack (String)
     */
    public String getStringItemsWithPlayer()
    {
        String backpackItems = backpack.getItemsInBackpackString();
        return backpackItems;
    }
    
    /**
     * Checks if the item exists in the Player's backpack
     * @param itemName (String) name of the item we are looking for
     * @return true if the item is in the backpack, false if it is not
     */
    public boolean isItemWithPlayer(String itemName)
    {
        boolean IsInBackpack = backpack.isItemInBackpack(itemName);
        return IsInBackpack;
    }
    
    /**
     * Looks for an item in the Player's Backpack 
     * @return itemInBackpack (Item) item in Player's backpack (if does not exists = null)
     */
    public Item findItemPlayer(String itemName)
    {
        Item itemInBackpack = backpack.findItemByName(itemName); //if item is in backpack = item, if not = null
        return itemInBackpack;
    }
    
    /**
     * ELiminates an item from the Player's backpack if such item is there
     * @param itemName (String) the name of the item to eliminate
     */
    public void dropItem(String itemName)
    {
        if(backpack.findItemByName(itemName) != null)   //if the item is in the backpack
        {
            backpack.removeItem(itemName);
        }
    }
    
    /**
     * Adds money to Player's pocket
     * @param money (int) amount of money that is going to be added
     */
    public void addMoney(int money)
    {
        moneyPocket = moneyPocket + money;
    }
    
    /**
     * @return the amount of money that the Player has (int)
     */
    public int getMoney()
    {
        return moneyPocket;
    }
}
