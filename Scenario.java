import java.util.List;
/**
 * The Scenario represents the world we move in. It holds all locations
 * we can enter in this game.
 * 
 * @author M. Schuringa
 * @version 0.2
 */
public class Scenario
{
    //private List<Room> rooms;     // List of all rooms in the game
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
        fortress = new Room("Fortress of Remembrance");
        tower = new Room("ground floor of the Duskmoon Tower");
        tower1f = new Room("first floor of the Duskmoon Tower.");
        tower2f = new Room("second floor of the Duskmoon Tower.");
        vault = new Room("Vault of Defiled Truth");
        cathedral = new Room("Forsaken Cathedral");
        city = new Room("Fallen City"); 
        sanctum = new Room("Bitterblack Sanctum");
        corridor = new Room("Corridor of Emptiness, a strange, dark place");
        
        // create an item
        Item key1 = new Item("vaultkey", "Vault key", 0.1);
        Item key2 = new Item("harborkey", "Harbor key", 0.1);
        Item key3 = new Item ("corridorkey", "Corridor key", 0.1);
        Item key4 = new Item ("sanctumkey", "Sanctum key", 0.1);
        Item bread = new Item("bread", "tasty looking bread", 0.5);
        Item book = new Item("book", "an ancient book", 2.5);
        Item armour = new Item("armour", "Abyssal armour", 7.5);
        Item weapon = new Item("whip", "Abyssal whip", 7);
        
        // put items in the room
        garden.addItem(bread);
        tower2f.addItem(key1);
        vault.addItem(key4);
        vault.addItem(armour);
        vault.addItem(weapon);
        cathedral.addItem(key2);
        cathedral.addItem(book);
        sanctum.addItem(key3);
        
        // initialise room exits
        new Door(harbor, "east", garden, "west", null);
        new Door(harbor, "south", fortress, "north", key2);
        
        new Door(garden, "south", city, "north", null);
        
        new Door(city, "east", cathedral, "west", null);
        new Door(city, "west", fortress, "east", null);
        
        new Door(fortress, "west", tower, "east", null);
        new Door(fortress, "down", vault, "up", key1);
        
        new Door(tower, "up", tower1f, "down", null); 
        new Door(tower1f, "up", tower2f, "down", null);
        
        new Door(cathedral, "south", sanctum, "north", key4);
        
        new Door(sanctum, "east", corridor, "west", key3);       
        
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
