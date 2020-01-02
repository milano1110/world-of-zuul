import java.util.Stack;
/**
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  M. Schuringa
 * @version 0.3
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> roomHistory;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player("Myraelith");
        Room startRoom = createRooms();
        player.enterRoom(startRoom);
        parser = new Parser();
        roomHistory = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room harbor, garden, tower, helix, vault, cathedral, city, sanctum;
      
        // create the rooms
        harbor = new Room("Bitterblack Harbor");
        garden = new Room("Garden of Ignominy");
        tower = new Room("Duskmoon Tower");
        helix = new Room("Midnight Helix");
        vault = new Room("Vault of Defiled Truth");
        cathedral = new Room("Forsaken Cathedral");
        city = new Room("Fallen City"); 
        sanctum = new Room("Bitterblack Sanctum");
        
        // put items in the room
        garden.addItem(new Item("bread", "tasty looking bread", 0.5));
        helix.addItem(new Item("key", "a special looking key", 0.1));
        
        // initialise room exits
        harbor.setExit("north", garden);
        
        garden.setExit("south", harbor);
        garden.setExit("east", tower);
             
        tower.setExit("west", garden);
        tower.setExit("east", helix);
        tower.setExit("north", vault);
                
        helix.setExit("west", tower);

        vault.setExit("south", tower);
        vault.setExit("north", cathedral);

        cathedral.setExit("south", vault);
        cathedral.setExit("north", city);
        
        city.setExit("south", cathedral);
        city.setExit("north", sanctum);
        
        sanctum.setExit("south", city);
        sanctum.setExit("west", harbor); // after boss defeat, teleport back 

        return harbor;  // start game in the harbor
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome " + player.getName() + ", to the Bitterblack Isle.");
        System.out.println("The Bitterblack Isle is a remote island off the shore of the mainland");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) 
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) 
        {
            printHelp();
        }
        else if (commandWord.equals("go")) 
        {
            goRoom(command);
        }
        else if (commandWord.equals("look"))
        {
            look();
        }
        else if (commandWord.equals("back"))
        {
            goBack(command);
        }
        else if (commandWord.equals("take"))
        {
            take(command);
        }
        else if (commandWord.equals("drop"))
        {
            drop(command);
        }
        else if (commandWord.equals("items"))
        {
            printItems();
        }
        else if (commandWord.equals("eat"))
        {
            eat(command);
        }
        else if (commandWord.equals("quit")) 
        {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around the isle.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandList());
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        
        if (nextRoom == null) 
        {
            System.out.println("There is no door.");
        }
        else 
        {
            roomHistory.push(player.getCurrentRoom());
            player.enterRoom(nextRoom);
            System.out.println(player.getLongDescription());
        }
    }
    
    /**
     * Go back to the previous room.
     */
    private void goBack(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Back where?");
            return;
        }
        
        if (roomHistory.isEmpty())
        {
            System.out.println("You have nowhere to go back to.");
        }
        else
        {
            Room previousRoom = roomHistory.pop();
            player.enterRoom(previousRoom);
            System.out.println(player.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) 
        {
            System.out.println("Quit what?");
            return false;
        }
        else 
        {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Lets the player look around the room
     */
    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Try to take an item from the current room. If the item is there, pick it up.
     */
    private void take(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("What do you want to take?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Item item = player.pickUpItem(itemName);
        
        if (item == null)
        {
            System.out.println("Can't pick up the item: " + itemName);
        }
        else
        {
            System.out.println("You picked up " + item.getDescription());
        }
    }
    
    /**
     * Drops an item into the current room. If the player carries the item drop it.
     */
    private void drop(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("What do you want to drop?");
        }
        String itemName = command.getSecondWord();
        Item item = player.dropItem(itemName);
        
        if (item == null)
        {
            System.out.println("You don't carry the item: " + itemName);
        }
        else 
        {
            System.out.println("You dropped " + item.getDescription());
        }
    }
    
    /**
     * Prints out the items that the player is currently carrying.
     */
    private void printItems()
    {
        System.out.println(player.getItemsString());
    }
    
    /**
     * Try to eat an item from your inventory. 
     * If the item is in your inventory and edible, eat it.
     */
    private void eat(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("What do you want to eat?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Item item = player.eat(itemName);
        if (item == null)
        {
            System.out.println("Could not eat " + itemName);
        }
        else
        {
            System.out.println("You ate " + item.getDescription());
        }
    }
}
