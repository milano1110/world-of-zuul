
/**
 * Representation for all valid command words in the game.
 *
 * @author M. Schuringa
 * @version 0.2
 */
public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), BACK("back"), TAKE("take"), 
    DROP("drop"), EAT("eat"), PLAYER("player"), UNKNOWN("?");
    
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
