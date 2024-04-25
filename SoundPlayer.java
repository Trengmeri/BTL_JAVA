package GiaoDien;
import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer {
    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            
            // Lấy FloatControl để điều chỉnh âm lượng
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            // Đặt âm lượng (tính theo dB)
            volumeControl.setValue(-10.0f); // Điều chỉnh âm lượng tại đây
            
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }
}
