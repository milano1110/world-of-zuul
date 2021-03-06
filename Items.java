import java.util.HashMap;
import java.util.Iterator;
/**
 * A list of items.
 *
 * @author M. Schuringa
 * @version 0.1
 */
public class Items
{
    private HashMap<String, Item> items;
    
    /**
     * Create a new item list.
     */
    public Items()
    {
        items = new HashMap<>();
    }
    
    /**
     * Return an Iterator over the items.
     * @return An Iterator
     */
    public Iterator<Item> iterator()
    {
        return items.values().iterator();
    }
    
    /**
     * Remove the given item
     * @param name The name of the item to be removed.
     */
    public Item removeItem(String name)
    {
        return items.remove(name);
    }
    
    /**
     * Checks if there is an item in the list.
     * @return True if it is empty.
     */
    public boolean noItem()
    {
        boolean noItem = false;
        if (items.isEmpty())
        {
            noItem = true;
        }
        return noItem;
    }
    
    /**
     * Put the given item in the list.
     * @param name The name of the item.
     * @param value The item.
     */
    public void putItem(String name, Item value)
    {
        items.put(name, value);
    }
    
    /**
     * Return the item with the given name
     * @param name The name of the item to return.
     * @return The named item, or null if it is not in the list.
     */
    public Item getItem(String name)
    {
        return items.get(name);
    }
    
    /**
     * Return a string listing the descriptions of the items in the list.
     * @return A long description.
     */
    public String getLongDescription()
    {
        String returnString = "";
        for (Iterator<Item> iter = items.values().iterator(); iter.hasNext(); )
        {
            returnString += " \n- " + iter.next().getDescription();
        }
        return returnString;
    }
    
    /**
     * Return a string listing the names of the items in the list.
     * @return A short description.
     */
    public String getShortDescription()
    {
        String returnString = "";
        for (Iterator<Item> iter = items.values().iterator(); iter.hasNext(); )
        {
            returnString += " " + iter.next().getName();
        }
        return returnString;
    }
    
    /**
     * Return the total weight of the items in the list.
     * @return The total weight.
     */
    public double getTotalWeight()
    {
        double weight = 0;
        for (Iterator<Item> iter = items.values().iterator(); iter.hasNext(); )
        {
            weight += iter.next().getWeight();
        }
        return weight;
    }
}
