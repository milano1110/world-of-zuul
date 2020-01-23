import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
/**
 * A list of NPC's.
 *
 * @author M. Schuringa
 * @version 0.1
 */
public class NPC
{
    private HashMap<String, String> npcs;
    private Items items;
    
    public NPC()
    {
        npcs = new HashMap<>();
        items = new Items();
    }
    
    public Iterator<String> iterator()
    {
        return npcs.values().iterator();
    }
    
    /**
     * Get the description of the NPC.
     * @return The description.
     */
    public String getDescription(String description)
    {
        return npcs.get(description);
    }
    
    /**
     * Get the name of the NPC.
     * @return The name.
     */
    public String getName(String name)
    {
        return npcs.get(name);
    }
    
    /**
     * Add an item to the NPC's inventory.
     * @param item The item to be added.
     */
    public void addItem(Item item)
    {
        items.putItem(item.getName(), item);
    }
    
    public boolean noNPC()
    {
        boolean noNPC = false;
        if (npcs.isEmpty())
        {
            noNPC = true;
        }
        return noNPC;
    }
    
    /**
     * Get a short description of the NPC's in the room in the form:
     *      NPC's:
     *      - Fisherman
     * @return The names of the NPC's in the room.
     */
    public String getShortDescription()
    {
        String returnString = "NPC's: ";
        Set<String> keys = npcs.keySet();
        for (Iterator<String> iter = keys.iterator(); iter.hasNext(); )
        {
            returnString += "\n- " + iter.next();
        }
        return returnString;
    }
    
    /**
     * Get a long description of the NPC's in the room in the form:
     *      NPC's: 
     *      - A friendly fishermen
     * @return The descriptions of the NPC in the room.
     */
    public String getLongDescription()
    {
        String returnString = "";
        Iterator iter = npcs.values().iterator();
        while (iter.hasNext())
        {
            returnString += " " + iter.next();
        }
        return returnString;
    }
    
    /**
     * Put a NPC in the list.
     * @param name The name of the NPC.
     * @param description The description of the NPC.
     */
    public void putNPC(String name, String description)
    {
        npcs.put(name, description);
    }
}
