import java.util.HashMap;
import java.util.Iterator;
/**
 * A list of bosses.
 *
 * @author M. Schuringa
 * @version 0.1
 */
public class Boss
{
    private HashMap<String, Stats> boss;
    
    public Boss()
    {
        boss = new HashMap<>();
    }
    
    /**
     * Return an Iterator over the bosses.
     * @return An Iterator
     */
    public Iterator<Stats> iterator()
    {
        return boss.values().iterator();
    }
    
    /**
     * Remove the given boss
     * @param name The name of the boss to be removed.
     */
    public Stats removeBoss(String name)
    {
        return boss.remove(name);
    }
    
    /**
     * Checks if there is an boss in the list.
     * @return True if it is empty.
     */
    public boolean noBoss()
    {
        boolean noBoss = false;
        if (boss.isEmpty())
        {
            noBoss = true;
        }
        return noBoss;
    }
    
    /**
     * Put the given boss in the list.
     * @param name The name of the boss.
     * @param value The boss.
     */
    public void putBoss(String name, Stats value)
    {
        boss.put(name, value);
    }
    
    /**
     * Return the boss with the given name
     * @param name The name of the boss to return.
     * @return The named boss, or null if it is not in the list.
     */
    public Stats getBoss(String name)
    {
        return boss.get(name);
    }
    
    /**
     * Return a string listing the names of the bosses in the list.
     * @return The description.
     */
    public String getDescription()
    {
        String returnString = "";
        for (Iterator<Stats> iter = boss.values().iterator(); iter.hasNext(); )
        {
            returnString += " \n" + iter.next().getDescription();
        }
        return returnString;
    }
}
