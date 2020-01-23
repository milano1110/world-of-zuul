import java.util.Random;
/**
 * The stats for the boss.
 *
 * @author M. Schuringa
 * @version 0.1
 */
public class Stats
{
    private String name;
    private int health;
    private int maxHealth = 100;
    private Item item;
    
    public Stats(String name, Item item)
    {
        this.name = name;
        health = maxHealth;
        this.item = item;
    }
    
    /**
     * Getter for the name of the boss.
     * @return The name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Getter for the current health of the boss.
     * @return The current health.
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Getter for the max health of the boss.
     * @return The max health.
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    /**
     * Getter for the item of the boss.
     * @return The item.
     */
    public Item getItem()
    {
        return item;
    }
    
    /**
     * Getter for the description of the boss.
     * @return The description.
     */
    public String getDescription()
    {
        return "Name: " + name + "\nHealth: " + printHealth();
    }
    
    /**
     * Checks if the boss is dead.
     * @return True if dead.
     */
    public boolean isDead()
    {
        boolean dead = false;
        if (health == 0)
        {
            dead = true;
        }
        return dead;
    }
    
    /**
     * Prints the health string of the boss in the form currenthealth/maxhealth.
     * @return The health of the boss.
     */
    public String printHealth()
    {
        return health + "/" + maxHealth;
    }
    
    /**
     * Damages the health of the boss.
     * @param damage The damage dealt.
     */
    public void removeHealth(int damage)
    {
        health = health - damage;
        if (health < 0)
        {
            health = 0;
        }
    }
    
    /**
     * The damage that the boss deals.
     * @return The damage.
     */
    public int damage()
    {
        Random rand = new Random();
        int low = 15;
        int high = 26;
        int damageBoss = rand.nextInt(high-low) + low;
        
        return damageBoss;
    }
}
