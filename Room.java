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
    private NPC npcs;
    private Boss boss;
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
        npcs = new NPC();
        boss = new Boss();
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
            returnString += "\n- " + iter.next();
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
     *     You are in the harbor.
     *     Doors: north west
     * 
     * @return A long decription of the room and the doors.
     */
    public String getLongDescription()
    {
        String returnString = "";
        if (description.contains("Corridor of Emptiness"))
        {
            returnString += "You are in the " + description + "\nItems in the room: " + items.getLongDescription();
        }
        else
        {
            returnString = "You are in the " + description + "\n" + getExitString(); 
            if (npcs.noNPC())
            {
                returnString += "\nThere are no NPC's in the room.";
            }
            else
            {
                returnString += ".\n" + npcs.getShortDescription() + "," + npcs.getLongDescription();
            }
            
            if (items.noItem())
            {
                returnString += "\nThere are no items in the room.";
            }
            else
            {
                returnString += "\nItems in the room: " + items.getLongDescription();
            }
            
            if (!boss.noBoss())
            {
                returnString += "\nWatch out! The boss is here." + boss.getDescription();
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
        items.putItem(item.getName(), item);
    }
    
    /**
     * Returns the item if it is available, otherwise it returns null.
     * @param name The name of the item to be returned.
     * @return The named item, or null if it is not in the room.
     */
    public Item getItem(String name)
    {
        return items.getItem(name);
    }
    
    /**
     * Removes and returns the item if it is available, otherwise returns null.
     * @param name The item to be removed.
     * @return The item if removed, otherwise null.
     */
    public Item removeItem(String name)
    {
        return items.removeItem(name);
    }
    
    /**
     * Add a NPC to the room.
     * @param name The name of the NPC.
     * @param description The description of the NPC.
     */
    public void addNPC(String name, String description)
    {
        npcs.putNPC(name, description);
    }
    
    /**
     * Puts an boss in the room.
     * @param stats The boss put into the room.
     */
    public void addBoss(Stats stats)
    {
        boss.putBoss(stats.getName(), stats);
    }
    
    /**
     * Returns the boss if it is available, otherwise it returns null.
     * @param name The name of the boss to be returned.
     * @return The name of the boss, or null if it is not in the room.
     */
    public Stats getBoss(String name)
    {
        return boss.getBoss(name);
    }
    
    public Boss getBosses()
    {
        return boss;
    }
    
    /**
     * Removes and returns the boss if it is available, otherwise returns null.
     * @param name The boss to be removed.
     * @return The boss if removed, otherwise null.
     */
    public Stats removeBoss(String name)
    {
        return boss.removeBoss(name);
    }
}
