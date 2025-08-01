package sound;

import utils.LogUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundManager {

    public static void playSound(String filePath) {
        try {
            // load the file from resources
            URL soundURL = SoundManager.class.getClassLoader().getResource("sounds/"+filePath);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + filePath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start(); // play the file
        } catch (Exception e) {
            LogUtils.logDebug(e.getMessage());
        }
    }
}
