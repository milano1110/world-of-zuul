import java.util.Random;
/**
 * Write a description of class BossStats here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    public String getName()
    {
        return name;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    public Item getItem()
    {
        return item;
    }
    
    public String getDescription()
    {
        return "Name: " + name + "\nHealth: " + health + "/" + maxHealth;
    }
    
    public boolean isDead()
    {
        boolean dead = false;
        if (health == 0)
        {
            dead = true;
        }
        return dead;
    }
    
    public void removeHealth(int damage)
    {
        health = health - damage;
        if (health < 0)
        {
            health = 0;
        }
    }
    
    public int bossAttack()
    {
        Random rand = new Random();
        int low = 15;
        int high = 26;
        int damageBoss = rand.nextInt(high-low) + low;
        
        return damageBoss;
    }
}
