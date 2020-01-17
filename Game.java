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
    private Stack<Room> roomHistory;
    private Player player;
    private Scenario scenario;
    private boolean wantToQuit = false;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        scenario = new Scenario();
        Room startRoom = scenario.getStartRoom();
        player = new Player("Myraelith", startRoom);
        parser = new Parser();
        roomHistory = new Stack<>();
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
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
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
        
        
        CommandWord commandWord = command.getCommandWord();
        
        switch (commandWord)
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            
            case HELP:
                printHelp();
                break;
            
            case GO:
                goRoom(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case BACK:
                goBack(command);
                break;
                
            case TAKE:
                take(command);
                break;
                
            case DROP:
                drop(command);
                break;
                
            case PLAYER:
                printPlayer();
                break;
                
            case EAT:
                eat(command);
                break;
              
            case ABOUT:
                about();
                break;
            
            case HEAL:
                heal();
                break;
            
            case DAMAGE:
                damage();
                break;
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
        Door door = player.getCurrentRoom().getDoor(direction);
        
        if (door == null) 
        {
            System.out.println("There is no door.");
        }
        else 
        {
            roomHistory.push(player.getCurrentRoom());
            if (player.goThrough(direction))
            {
                System.out.println(player.getLongDescription());
            }
            else
            {
                System.out.println("The door is locked and you don't have the key for it.");
            }
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
     * Gives info about the game.
     */
    public void about()
    {
        System.out.println("This game was made by M. Schuringa and K. van der Laan");
        System.out.println("The game is an assignment from the Hanzehogeschool in Groningen");
        System.out.println("");
        System.out.println("Bitterblack Isle is a black, bitter, mysterious place.");
        System.out.println("Countless people have visited this place in hopes of finding something.");
        System.out.println("Whether on a quest from a king of old or just seeking fame and glory, ");
        System.out.println("many who venture into the maze have never again seen the light of day.");
        System.out.println("");
        System.out.println("Here live unspeakable creatures no one has seen outside its walls.");
        System.out.println("Bitterblack Isle is also the home for strange plants that can be used to create new curatives; ");
        System.out.println("ores never seen before lie within rocks littering the entire isle.");
        System.out.println("");
        System.out.println("The entrance is marked with an epitaph, a slab of stone etched with stories ");
        System.out.println("and accounts of other adventurers that have traveled into this forsaken place.");
        System.out.println("Some of their tales might be of use for those who dare journey these halls.");
        System.out.println("");
    }
    
    public void quitDead()
    {
        if (player.isDead() == true)
        {
            wantToQuit = true;
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
            printPlayer();
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
            printPlayer();
        }
    }
    
    /**
     * Prints out the items that the player is currently carrying.
     */
    private void printPlayer()
    {
        System.out.println(player.getPlayer());
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
            System.out.println("You feel invigorated!");
            printPlayer();
        }
    }
    
    private void heal()
    {
        player.addHealth(10);
        System.out.println("Your health is: " + player.printHealth());
    }
    
    private void damage()
    {
        player.removeHealth(10);
        System.out.println("Your health is: " + player.printHealth());
        int health = player.getHealth();
        if (player.isDead() == true)
        {
            System.out.println("You have died.");
            quitDead();
        }
    }
}
