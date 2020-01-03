import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
/**
 * Class Room - a room in an adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via doors.  The doors are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  M. Schuringa
 * @version 0.2
 */
public class Room 
{
    private String description;
    private HashMap<String, Door> doors;
    private Items items;

    /**
     * Create a room described "description". Initially, it has
     * no doors. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        doors = new HashMap<>();
        items = new Items();
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setDoor(String direction, Door door)
    {
        doors.put(direction, door);
    }
    
    /**
     * Define the doors of this room.  Every direction either leads
     * to another room or is null (no door there).
     * @param direction The direction of the doors.
     * @return The room in the given direction.
     */
    public Door getDoor(String direction)
    {
        return doors.get(direction);
    }
    
    /**
     * Returns a string describing the room's exits, for example
     * "Doors: north west".
     * @return The doors of a room.
     */
    public String getExitString()
    {
        String returnString = "Doors: ";
        Set<String> keys = doors.keySet();
        for (Iterator<String> iter = keys.iterator(); iter.hasNext(); )
        {
            returnString += " " + iter.next();
        }
        return returnString;
    }
    
    /**
     * @return The short description of the room.
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     doors: north west
     * 
     * @return A long decription of the room and the doors.
     */
    public String getLongDescription()
    {
        String returnString = "You are in the " + description + ".\n" + getExitString(); 
        if (description == "a strange, dark place")
        {
            returnString += "";
        }
        else
        {
            if (items.empty() == true)
            {
                returnString += "\nThere are no items in the room.";
            }
            else
            {
                returnString += "\nItems in the room: " + items.getLongDescription();
            }
        }
        return returnString;
    }
       
    /**
     * Puts an item in the room.
     * @param item The item put into the room.
     */
    public void addItem(Item item)
    {
        items.put(item.getName(), item);
    }
    
    /**
     * Returns the item if it is available, otherwise it returns null.
     * @param name The name of the item to be returned.
     * @return The named item, or null if it is not in the room.
     */
    public Item getItem(String name)
    {
        return items.get(name);
    }
    
    /**
     * Removes and returns the item if it is available, otherwise returns null.
     * @param name The item to be removed.
     * @return The item if removed, otherwise null.
     */
    public Item removeItem(String name)
    {
        return items.remove(name);
    }
}
