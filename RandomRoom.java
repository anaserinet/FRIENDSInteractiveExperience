import java.util.Random;
import java.util.Iterator;

/**
 * Write a description of class RandomRoom here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomRoom extends Room
{

    /**
     * Create a room described "description" if the room is a "random room", 
     * meaning it has a different attribute and act differently to regular rooms. 
     * Initially, it has no exits. "description" is something like "a kitchen" or
     * "an open court yard".    
     * @param description (String) The room's description.
     */
    public RandomRoom(String description)
    {
        super(description);
    }
    
    @Override
    public Room getNextRoom(String direction)
    {
        Random random = new Random();
        int roomSelectedInt = random.nextInt(allRooms.size());
        Iterator<Room> roomIterator = allRooms.values().iterator();       //create an iterator for all of the Rooms (values in allRooms)
        
        Room randomRoom = null;
        for (int i = 0; i <= roomSelectedInt; i++) {                     //iterate a random num of time and the Room it lands on is the new current_room
            randomRoom = roomIterator.next();
        }   
        System.out.println(getLongDescription());
        return randomRoom;
    }
    
    public String getLongDescription()
    {
        return "You are " + description + "... but not for long.\n Teletransporting Player to a random Room in the map.......\n DONE, can you guess where you are? ";
    }
    
    
}
