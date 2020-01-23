import java.util.Iterator;
/**
 * This is the representation of a player
 *
 * @author M. Schuringa
 * @version 0.3
 */
public class Player
{
    private String name;
    private Room currentRoom;
    private Items items = new Items();
    private double maxWeight = 20;
    private int health;
    private int maxHealth = 100;
    private int minDamage = 15;
    private int maxdamage = 21;
    
    /**
     * Initializing the player's name and start room.
     * @param name Name of the player.
     * @param startRoom Room where the player starts.
     */
    public Player(String name, Room startRoom)
    {
        this.name = name;
        this.currentRoom = startRoom;
        this.health = maxHealth;
    }
    
    /**
     * Goes through the door in the given direction.
     * If successful it will return true, if not it will return false.
     * Which could indicate that there is no door or that it is locked 
     * and that you need a key for it.
     * @param direction The direction that is given.
     * @return True or false.
     */
    public boolean goThrough(String direction)
    {
        Door door = currentRoom.getDoor(direction);
        if (door == null)
        {
            return false;
        }
        
        Room nextRoom = door.open(currentRoom);
        if (nextRoom == null)
        {
            Iterator iter = items.iterator();
            while(iter.hasNext() && !door.unlock((Item) iter.next()));
        }
        
        nextRoom = door.open(currentRoom);
        if (nextRoom != null)
        {
            enterRoom(nextRoom);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Enter the given room.
     * @param room The room entered.
     */
    public void enterRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Get the room in which the player is currently located.
     * @return The current room.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Set the name of the player.
     * @param name Player's name.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Get the name of the player.
     * @return The player's name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Restores player's health.
     * @param heal The amount of health healed.
     */
    public void addHealth(int heal)
    {
        if (health == maxHealth)
        {
            System.out.println("Your health is full.");
        }
        else
        {
            health = health + heal;
            if (health > maxHealth)
            {
                health = maxHealth;
            }
        }
    }
    
    /**
     * Checks the players inventory to see if the player
     * is wearing armour.
     * @return True if the player is wearing armour.
     */
    public boolean checkArmour()
    {
        boolean armour = false;
        if (items.getShortDescription().contains("armour"))
        {
            armour = true;
            maxHealth = 125;
        }
        else
        {
            armour = false;
            maxHealth = 100;
            if (health > maxHealth)
            {
                health += -25;
            }
        }
        
        return armour;
    }
    
    /**
     * Checks the players inventory to see if the player
     * is carrying a weapon.
     * @return True if the player is carrying a weapon.
     */
    public boolean checkWeapon()
    {
        boolean weapon = false;
        if (items.getShortDescription().contains("weapon"))
        {
            weapon = true;
        }
        else
        {
            weapon = false;
        }
        return weapon;
    }
    
    public void setMaxHealth(int health)
    {
        maxHealth += health;
    }
    
    /**
     * Get the current health of the player.
     * @return health The current health.
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Damages the health of the player.
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
     * Checks if the player is dead.
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
     * Return the health of the player in the form currenthealth/maxhealth.
     * @return Health of the player.
     */
    public String printHealth()
    {
        checkArmour();
        return health + "/" + maxHealth;
    }
    
    /**
     * Returns a string describing the items that the player carries.
     * @return A description of the items held.
     */
    public String getPlayer()
    {
        String returnString = "Your current health is: " + printHealth() +"\nYour total weight: " + items.getTotalWeight();
        if (items.empty() == true)
        {
            returnString += "\nYour inventory is empty.";
        }
        else
        {
            returnString += "\nYour inventory contains: " + items.getLongDescription();
        }
        return returnString;
    }
    
    /**
     * Returns a String describing the players current location and which
     * items the player carries.
     * @return A description of the room and items held.
     */
    public String getLongDescription()
    {
        String returnString = currentRoom.getLongDescription();
        return returnString;
    }
    
    /**
     * Tries to pick up the item from the current room.
     * @param itemName The item to be picked up.
     * @return If successful this will return that the item was picked up.
     */
    public Item pickUpItem(String itemName)
    {
        if (canPickItem(itemName))
        {
            Item item = currentRoom.removeItem(itemName);
            items.put(itemName, item);
            return item;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Tries to drop an item into the current room.
     * @param itemName The item to be dropped.
     * @return If successful this will return the item that was dropped.
     */
    public Item dropItem(String itemName)
    {
        Item item = items.remove(itemName);
        if (item != null)
        {
            currentRoom.addItem(item);
        }
        return item;
    }
    
    /**
     * Eats the item if possible.
     * Only bread is edible.
     * @param itemName The item to be eaten.
     */
    public Item eat(String itemName)
    {
        if (itemName.equals("bread"))
        {
            Item foodItem = items.get(itemName);
            Item player = items.remove(itemName);
            if (foodItem == null)
            {
                return player;
            }
            
            if (foodItem != null)
            {
                addHealth(5);
                return foodItem;
            }
        }
        return null;
    }
    
    /**
     * Reads the item if possible.
     * Only book is readable.
     * @param itemName The item to be read.
     */
    public Item read(String itemName)
    {
        if (itemName.equals("book"))
        {
            Item readItem = items.get(itemName);
            Item player = items.remove(itemName);
            if (readItem == null)
            {
                return player;
            }
            
            if (readItem != null)
            {
                return readItem;
            }
        }
        return null;
    }
    
    /**
     * Checks if we can pick up the given item. This depends on whether the item 
     * is actually in the current room and if it is not too heavy.
     * @param itemName The item to be picked up
     * @return true if the item can be picked up, false otherwise.
     */
    private boolean canPickItem(String itemName)
    {
        boolean canPick = true;
        Item item = currentRoom.getItem(itemName);
        if (item == null)
        {
            canPick = false;
        }
        else
        {
            double totalWeight = items.getTotalWeight() + item.getWeight();
            if (totalWeight > maxWeight)
            {
                canPick = false;
            }
        }
        return canPick;
    }
}
