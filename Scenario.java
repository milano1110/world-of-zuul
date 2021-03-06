
/**
 * The Scenario represents the world we move in. It holds all locations
 * we can enter in this game.
 * 
 * @author M. Schuringa
 * @version 0.2
 */
public class Scenario
{
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
        Item key1 = new Item("vaultkey", "Vault key", 0.25);
        Item key2 = new Item("harborkey", "Harbor key", 0.25);
        Item key3 = new Item ("corridorkey", "Corridor key", 0.25);
        Item key4 = new Item ("sanctumkey", "Sanctum key", 0.25);
        Item bread = new Item("bread", "tasty looking bread", 0.5);
        Item book = new Item("book", "an ancient book", 2.5);
        Item armour = new Item("armor", "Abyssal armor", 10);
        Item weapon = new Item("sword", "Abyssal sword", 4);
        Item statue = new Item("statue", "a beautiful statue shaped after a goddess", 50);
        Item pillar = new Item("pillar", "four giant stone pillars with glowing orbs" , 150); //Eva made this
        Item stone = new Item("stone", "Victory Stone", 1.5);
        
        // put items in the room
        garden.addItem(bread);
        garden.addItem(statue);
        garden.addItem(pillar);
        
        tower1f.addItem(key4);
        
        tower2f.addItem(key1);
        
        vault.addItem(armour);
        vault.addItem(weapon);
        
        cathedral.addItem(key2);
        cathedral.addItem(book);
        
        //sanctum.addItem(key3);
        
        corridor.addItem(stone);
        
        // set boss
        Stats boss = new Stats("demon", key3);
        
        // put NPC's in the room
        harbor.addNPC("Fisherman", "a friendly fisherman");
        
        garden.addNPC("Ayalin", "a young woman drawing the statue"); //Eva made this
        
        tower2f.addNPC("King", "the King of the fortress");
        
        // put boss in the room
        sanctum.addBoss(boss);
        
        // initialise room exits
        new Door(harbor, "south", garden, "north", null);
        new Door(harbor, "west", fortress, "east", key2);
        //new Door(harbor, "north", sanctum, "south", null);
        
        new Door(garden, "south", city, "north", null);
        
        new Door(city, "east", cathedral, "west", null);
        new Door(city, "west", fortress, "east", null);
        
        new Door(fortress, "west", tower, "east", null);
        new Door(fortress, "down", vault, "up", key1);
        
        new Door(tower, "up", tower1f, "down", null); 
        new Door(tower1f, "up", tower2f, "down", null);
        
        new Door(cathedral, "east", sanctum, "west", key4);
        
        new Door(sanctum, "east", corridor, "", key3);
        
        /*
        new Door(corridor, "north", harbor, "", null);
        new Door(corridor, "east", harbor, "", null);
        new Door(corridor, "south", harbor, "", null);
        new Door(corridor, "west", harbor, "", null);
        */
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
