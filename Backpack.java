import java.util.HashSet;
/**
 * Class Backpack - a backpack that a Player uses to carry the Items they grab
 * 
 * This class is part of the "FRIENDS: An Interactive Videogame" application. 
 * "FRIENDS: An Interactive Videogame" is an interactive application based on the TV show "Friends".
 *
 *A "BackPack"
 *...................................................
 *.....................
 * @author Ana Clara Monteiro 
 * @version 27/11/2024
 */
public class Backpack
{
    // instance variables - replace the example below with your own
    private float maxWeight;
    private float currentWeight;
    private HashSet<Item> itemsInBackPack;
    /**
     * Constructor for objects of class Backpack
     */
    public Backpack()
    {
        // initialise instance variables
        maxWeight = 50;
        currentWeight = 0;
        itemsInBackPack = new HashSet<>();
    }

    /**
     * Cheks if the weight of an item is small enough to fit it into the players backpack. 
     * If by adding the item weight to the backpack weight the maximum weight the backpack can carry is not exceeded, it returns true
     * If the limit weight is reached, it returns false
     *
     * @param newItemWeight  the weight of the Item we want to add to our backpack
     * @return   bool true or false
     */
    private boolean checkWeight(float newItemWeight)
    {
        float potentialWeight = currentWeight + newItemWeight;
        if(potentialWeight <= maxWeight){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Adds weight of an item into the backpack weight
     */
    
    private void addWeight(float newItemWeight)
    {
        currentWeight = currentWeight + newItemWeight;
    }
    
    /**
     * It checks if item can be added into the player's backpack when user grabs an item
     * If so, it adds it to the player's backpack.
     *
     * @param Item newItem  item that we want to add to our backpack
     * @return bool true if item was added, false if item wasn't added
     */
    
    public void addItem(Item newItem)
    {
        itemsInBackPack.add(newItem);
        addWeight(newItem.getWeight());
    }
    
    public boolean checkIfItemCanAddBackpack(Item newItem)
    {
        if(newItem != null){
            if(checkWeight(newItem.getWeight()) && !(itemsInBackPack.contains(newItem))){    //checks if weight of item doesnt exceed limit when added t backpack AND if the item doesnt already exist in the backpack
                itemsInBackPack.add(newItem);
                addWeight(newItem.getWeight());
                return true;
            }
        }
        return false;
    }
    
    public float getMaxWeight(){
        return maxWeight;
    }
    
    /**
     * Returns a HashSet with the Items in the Backpack
     * @return HashSet<Item>   Items in Backpack
     * */
    public HashSet<Item> getItemsInBackpack()
    {
        return itemsInBackPack;
    }
    
    /**
     * Returns a String with the Items in the Backpack separated by a space
     * @return String   Items in Backpack
     * */
    public String getItemsInBackpackString()
    {
        String itemsInBackpackString = "";
        for(Item item:itemsInBackPack){
            itemsInBackpackString = itemsInBackpackString + item.getName() + " ";
        }
        return itemsInBackpackString;
    }
    
    public boolean isItemInBackpack(String itemName)
    {
        if(getItemsInBackpackString().contains(itemName)){
            return true;
        }
        return false;
    }
    
    /**
     * Finds and returns an Item in the Backpack through the name inserted. If the item is not in the backpack, null is returned
     * @param name    name of the Item to look for in the Backpack
     * @return Item   Item in Backpack
     * */
    public Item findItemByName(String name)
    {
        for(Item item : itemsInBackPack) {             //loops through all items in backpack
            if(item.getName().trim().equals(name)){  //if the an item in the loop has the same name as the mame we're looking for, then we found our item
                return item;
            }    
        }
        return null;   
    }   
    
    public void removeItem(String itemName)
    {
        Item itemRemove = findItemByName(itemName);
        if(itemRemove != null){
            itemsInBackPack.remove(itemRemove);
            currentWeight = currentWeight - itemRemove.getWeight();      //remove the item's weight from the backpack
        }
    }
    
    
}
