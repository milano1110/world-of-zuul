import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
/**
 * Class Room - a room in an adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  M. Schuringa
 * @version 0.2
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Items items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new Items();
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction The direction of the exits.
     * @return The room in the given direction.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Returns a string describing the room's exits, for example
     * "Exits: north west".
     * @return The exits of a room.
     */
    public String getExitString()
    {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
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
     *     Exits: north west
     * 
     * @return A long decription of the room and the exits.
     */
    public String getLongDescription()
    {
        return "You are in the " + description + ".\n" + getExitString() + "\nItems in the room: " + items.getLongDescription();
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
