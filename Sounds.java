import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
/**
 * Write a description of class Sounds here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sounds
{

    public void doorSound()
    {
        File doorOpen = new File("sounds/door_open.wav");
        playSound(doorOpen);

        File doorClose = new File("sounds/door_close.wav");
        playSound(doorClose);
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
