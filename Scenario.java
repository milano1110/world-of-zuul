import java.util.List;
/**
 * The Scenario represents the world we move in. It holds all locations
 * we can enter in this game.
 * 
 * @author M. Schuringa
 * @version 0.1
 */
public class Scenario
{
    private List<Room> rooms;     // List of all rooms in the game
    private Room startRoom;

    /**
     * Create a complete scenario (with all rooms).
     */
    public Scenario()
    {
        Room harbor, garden, tower, helix, vault, cathedral, city, sanctum, teleporter;
      
        // create the rooms
        harbor = new Room("Bitterblack Harbor");
        garden = new Room("Garden of Ignominy");
        tower = new Room("Duskmoon Tower");
        helix = new Room("Midnight Helix");
        vault = new Room("Vault of Defiled Truth");
        cathedral = new Room("Forsaken Cathedral");
        city = new Room("Fallen City"); 
        sanctum = new Room("Bitterblack Sanctum");
        teleporter = new TeleporterRoom("a strange, dark place", this);
        
        // create a key for a locked door
        Item key = new Item("key", "a special looking key", 0.01);
        
        // put items in the room
        garden.addItem(new Item("bread", "tasty looking bread", 0.5));
        helix.addItem(key);
        
        // initialise room exits
        new Door(harbor, "north", garden, "south", null);
        new Door(harbor, "west", city, "east", key);
        new Door(garden, "east", tower, "west", null);
        new Door(tower, "east", helix, "west", null);
        new Door(tower, "north", vault, "south", key);
        new Door(vault, "north", cathedral, "south", null);
        new Door(cathedral, "north", city, "south", null);
        new Door(city, "north", sanctum, "south", null);
        new Door(sanctum, "west", teleporter, "east", key);

        startRoom = harbor;  // start game in the harbor
    }

    /**
     * Return the room where players start the game.
     */
    public Room getStartRoom()
    {
        return startRoom;
    }
}
