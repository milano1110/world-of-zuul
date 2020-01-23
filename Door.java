
/**
 * A door that connects two rooms.
 * Some doors are locked and requires a key to be opened.
 *
 * @author M. Schuringa
 * @version 0.1
 */
public class Door
{
    private Room room1;
    private Room room2;
    private String direction1;
    private String direction2;
    private Item key;
    private boolean locked = false;
    
    /**
     * Create a new door between two rooms.
     * It also takes a key as parameter which can be used to unlock the door.
     */
    public Door(Room room1, String direction1, Room room2, String direction2, Item key)
    {
        this.room1 = room1;
        this.room2 = room2;
        this.direction1 = direction1;
        this.direction2 = direction2;
        room1.setDoor(direction1, this);
        room2.setDoor(direction2, this);
        this.key = key;
        lock(key);
    }
    
    public String getDirection1()
    {
        return direction1;
    }
    
    public String getDirection2()
    {
        return direction2;
    }
    
    /**
     * Try to lock the door with the key.
     * @return True if the door was successfully locked.
     */
    public boolean lock(Item key)
    {
        if (this.key == key && key != null)
        {
            locked = true;
        }
        return locked;
    }
    
    /**
     * Try to unlock the door with the key.
     * @return True if the door was successfully unlocked.
     */
    public boolean unlock(Item key)
    {
        if (this.key == key && key != null)
        {
            locked = false;
        }
        return !locked;
    }
    
    /**
     * Try to go through the door from one room to the other room.
     * @param fromRoom The room from which side we open the door.
     * @return The room on the other side, or null if the door is locked.
     */
    public Room open(Room fromRoom)
    {
        if (locked)
        {
            return null;
        }
        
        if (fromRoom == room1)
        {
            return room2;
        }
        else if (fromRoom == room2)
        {
            return room1;
        }
        else
        {
            return null;
        }
    }
}
