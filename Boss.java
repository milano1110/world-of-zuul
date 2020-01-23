import java.util.HashMap;
import java.util.Iterator;
/**
 * A list of NPC's.
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
    
    public Iterator<Stats> iterator()
    {
        return boss.values().iterator();
    }
    
    public Stats removeItem(String name)
    {
        return boss.remove(name);
    }
    
    public boolean noBoss()
    {
        boolean noBoss = false;
        if (boss.isEmpty())
        {
            noBoss = true;
        }
        return noBoss;
    }
    
    public void putBoss(String name, Stats value)
    {
        boss.put(name, value);
    }
    
    public Stats getBoss(String name)
    {
        return boss.get(name);
    }
    
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
