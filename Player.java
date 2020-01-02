
/**
 * This is the representation of a player
 *
 * @author M. Schuringa
 * @version 0.1
 */
public class Player
{
    private String name;
    private Room currentRoom;
    private Items items = new Items();
    private double maxWeight;
    
    /**
     * Initializing the player's name.
     * @param name Name of the player.
     */
    public Player(String name)
    {
        this.name = name;
        this.maxWeight = 10;
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
     * Returns a string describing the items that the player carries.
     * @return A description of the items held.
     */
    public String getItemsString()
    {
        return "You are carrying: " + items.getLongDescription();
    }
    
    /**
     * Returns a String describing the players current location and which
     * items the player carries.
     * @return A description of the room and items held.
     */
    public String getLongDescription()
    {
        String returnString = currentRoom.getLongDescription();
        //returnString += "\n" + getItemsString();
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
            if (bread == null)
            {
                bread = currentRoom.removeItem(itemName);
            }
            
            if (bread != null)
            {
                maxWeight += 1;
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
