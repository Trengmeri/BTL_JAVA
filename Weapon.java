package GiaoDien;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Weapon extends JPanel {
    private BufferedImage spriteSheet;
    private int currentFrame = 0;
    private int frameWidth, frameHeight;
    private int numFrames = 5; // Số lượng khung hình trong sprite sheet
    private int frameDelay = 100; // Độ trễ giữa các khung hình (milliseconds)
    private long lastFrameTime;

    public Weapon() {
        // Load sprite sheet
        ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\Desktop\\JAVA\\BTL\\TÀI NGUYÊN\\weapon\\WEAPON.png");
        spriteSheet = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        spriteSheet.getGraphics().drawImage(icon.getImage(), 0, 0, null);

        // Set frame width and height (assuming all frames are of same size)
        frameWidth = spriteSheet.getWidth() / numFrames;
        frameHeight = spriteSheet.getHeight();
        
        // Set preferred size of the panel based on frame size
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        
        // Start animation timer
        lastFrameTime = System.currentTimeMillis();
        Timer timer = new Timer(16, e -> {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFrameTime >= frameDelay) {
                currentFrame = (currentFrame + 1) % numFrames;
                repaint();
                lastFrameTime = currentTime;
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw current frame
        g.drawImage(spriteSheet.getSubimage(currentFrame * frameWidth, 0, frameWidth, frameHeight), 0, 0, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sprite Animation Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Weapon());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

