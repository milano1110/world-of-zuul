
/**
 * A TeleporterRoom is a room that will automatically transport you 
 * to another room when you try to leave it.
 * 
 * @author M. Schuringa
 * @version 0.1
 */
public class TeleporterRoom extends Room
{
    private Scenario scene;
    
    /**
     * Create a room that teleports you back to the beginning
     */
    public TeleporterRoom(String description, Scenario scene)
    {
        super(description);
        this.scene = scene;
    }

    /**
     * Describe the exits. Since this is a Teleport room, you see no
     * clear exits.
     */
    public String getExitString()
    {
        return "You feel quite dizzy. Something is strange.\n" +
               "You cannot really see the exits...";
    }

    /**
     * Return the room that the player started in.
     */
    public Room getExit(String direction) 
    {
        return scene.getStartRoom();
    }

}
