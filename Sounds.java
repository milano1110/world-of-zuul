import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
/**
 * Location for the sounds.
 *
 * @author M. Schuringa
 * @version 0.1
 */
public class Sounds
{
    public Sounds()
    {
    }
    
    /**
     * Plays a door sound.
     */
    public void doorSound()
    {
        File doorOpen = new File("sounds/door_open.wav");
        playSound(doorOpen);

        File doorClose = new File("sounds/door_close.wav");
        playSound(doorClose);
    }
    
    /**
     * Plays an eating sound.
     */
    public void eatSound()
    {
        File eat1 = new File("sounds/eat1.wav");
        playSound(eat1);
        
        File eat2 = new File("sounds/eat2.wav");
        playSound(eat2);
        
        File eat3 = new File("sounds/eat3.wav");
        playSound(eat3);
        
        File burp = new File("sounds/burp.wav");
        playSound(burp);
    }
    
    /**
     * Plays a damage sound.
     */
    public void takedamageSound()
    {
        File damage = new File("sounds/classic_hurt.wav");
        playSound(damage);
    }
    
    /**
     * Plays a pick up sound.
     */
    public void pickupSound()
    {
        File pickup = new File("sounds/pop.wav");
        playSound(pickup);
    }
    
    /**
     * Plays a teleport sound.
     */
    public void teleportSound()
    {
        File teleport = new File("sounds/travel.wav");
        playSound(teleport);
    }
    
    /**
     * Plays a ladder sound.
     */
    public void upSound()
    {
        File up1 = new File("sounds/ladder1.wav");
        playSound(up1);
        
        File up2 = new File("sounds/ladder2.wav");
        playSound(up2);
        
        File up3 = new File("sounds/ladder3.wav");
        playSound(up3);
        
        File up4 = new File("sounds/ladder4.wav");
        playSound(up4);
        
        File up5 = new File("sounds/ladder5.wav");
        playSound(up5);
    }
    
    /**
     * Plays a drinking sound.
     */
    public void drinkSound()
    {
        File drink = new File("sounds/drink.wav");
        playSound(drink);
        playSound(drink);
        playSound(drink);
    }
    
    /**
     * Plays a demon hit sound.
     */
    public void demonhitSound()
    {
        File demonHit = new File("sounds/demonhit4.wav");
        playSound(demonHit);
    }
    
    /**
     * Plays a demon death sound.
     */
    public void demondeathSound()
    {
        File demondeath = new File("sounds/demondeath.wav");
        playSound(demondeath);
    }
    
    /**
     * Plays the sound from the file.
     * @param sound The file path of the sound.
     */
    public void playSound(File sound)
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);
        }
        catch(Exception e)
        {
            
        }
    }
}
