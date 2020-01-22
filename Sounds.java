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

    public void doorSound()
    {
        File doorOpen = new File("sounds/door_open.wav");
        playSound(doorOpen);

        File doorClose = new File("sounds/door_close.wav");
        playSound(doorClose);
    }
    
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
    
    public void takedamageSound()
    {
        File damage = new File("sounds/classic_hurt.wav");
        playSound(damage);
    }
    
    public void pickupSound()
    {
        File pickup = new File("sounds/pop.wav");
        playSound(pickup);
    }
    
    public void teleportSound()
    {
        File teleport = new File("sounds/travel.wav");
        playSound(teleport);
    }
    
    public void upSound()
    {
        File up1 = new File("sounds/ladder5.wav");
        playSound(up1);
        
        File up2 = new File("sounds/ladder4.wav");
        playSound(up2);
        
        File up3 = new File("sounds/ladder3.wav");
        playSound(up3);
        
        File up4 = new File("sounds/ladder2.wav");
        playSound(up4);
        
        File up5 = new File("sounds/ladder1.wav");
        playSound(up5);
    }
    
    

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
