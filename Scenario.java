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
        Room harbor, garden, tower, tower1f, tower2f, vault, cathedral, city, sanctum, corridor, fortress;
      
        // create the rooms
        harbor = new Room("Bitterblack Harbor");
        garden = new Room("Garden of Ignominy");
        tower = new Room("Ground floor of the Duskmoon Tower");
        vault = new Room("Vault of Defiled Truth");
        cathedral = new Room("Forsaken Cathedral");
        city = new Room("Fallen City"); 
        sanctum = new Room("Bitterblack Sanctum");
        corridor = new Room("Corridor of Emptiness, \na strange, dark place");
        fortress = new Room("Fortress of Remembrance");
        tower1f = new Room("First floor of the Duskmoon Tower.");
        tower2f = new Room("Second floor of the Duskmoon Tower.");
        
        // create a items
        Item key1 = new Item("key", "Vault key", 0.01);
        Item key2 = new Item("key", "opens shortcut", 0.01);
        Item key3 = new Item ("key", "opens teleporter", 0.01);
        Item key4 = new Item ("key", "opens sanctum", 0.01);
        Item bread = new Item("bread", "tasty looking bread", 0.5);
        Item book = new Item("book", "an ancient book", 2);
        Item armour1 = new Item("armour", "Silver armour", 5);
        Item armour2 = new Item("armour", "Knight armour", 7.5);
        Item armour3 = new Item("armour", "Demon slayer armour", 10);
        Item armour4 = new Item("armour", "Abyssal armour", 7.5);
        Item weapon1 = new Item("sword", "Silver sword",4);
        Item weapon2 = new Item("sword", "Knight sword",6);
        Item weapon3 = new Item("sword", "Demon slayer sword",8);
        Item weapon4 = new Item("whip", "Abyssal whip",7);
        
        // put items in the room
        garden.addItem(bread);
        cathedral.addItem(book);
        city.addItem(key1);
        
        // initialise room exits
        new Door(harbor, "east", garden, "west", null);
        new Door(harbor, "south", fortress, "north", key2);
        
        new Door(garden, "south", city, "north", null);
        
        new Door(city, "east", cathedral, "west", null);
        new Door(city, "west", fortress, "east", null);
        
        new Door(cathedral, "south", sanctum, "north", key4);
        
        new Door(fortress, "west", tower, "east", null);
        new Door(fortress, "down", vault, "up", key1);
        
        new Door(tower, "up", tower1f, "down", null); 
        new Door(tower1f, "up", tower2f, "down", null);
        
        new Door(sanctum, "east", corridor, "", key3);
        
        new Door(corridor, "north", harbor, "", null);
        new Door(corridor, "east", harbor, "", null);
        new Door(corridor, "south", harbor, "", null);
        new Door(corridor, "west", harbor, "", null);

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
