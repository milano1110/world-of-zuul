import java.util.Iterator;
/**
 * This is the representation of a player
 *
 * @author M. Schuringa
 * @version 0.2
 */
public class Player
{
    private String name;
    private Room currentRoom;
    private Items items = new Items();
    private double maxWeight = 20;
    private int health;
    private int maxHealth = 20;
    
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
    }
    
    /**
     * Return the health of the player in the form currenthealth/maxhealth.
     * @return Health of the player.
     */
    public String printHealth()
    {
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
            Item bread = items.get(itemName);
            Item player = items.remove(itemName);
            if (bread == null)
            {
                return player;
            }
            
            if (bread != null)
            {
                return bread;
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
