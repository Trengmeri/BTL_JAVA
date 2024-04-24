package GiaoDien;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class move_left extends JPanel {
    private BufferedImage spriteSheet;
    private int currentFrame = 0;
    private int frameWidth, frameHeight;
    private int numFrames = 2; // Số lượng khung hình trong sprite sheet
    private int frameDelay = 400; // Độ trễ giữa các khung hình (milliseconds)
    private long lastFrameTime;
    private int x;
    private int y;

    public void setX(int x) {
    this.x = x;
    }
    public void setY(int y) {
    this.y = y;
    }
   
    private BufferedImage scaledSpriteSheet;
    private int scaledFrameWidth, scaledFrameHeight;

    public move_left() {
        // Load sprite sheet
        ImageIcon icon = new ImageIcon("image\\character\\boò.png");
        spriteSheet = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        spriteSheet.getGraphics().drawImage(icon.getImage(), 0, 0, null);

        // Set frame width and height (assuming all frames are of same size)
        frameWidth = spriteSheet.getWidth() / numFrames;
        frameHeight = spriteSheet.getHeight();

        // Reverse the image
        reverseImage();

        // Set preferred size of the panel based on frame size
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        scaleSpriteSheet();

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

    private void reverseImage() {
        BufferedImage reversedImage = new BufferedImage(spriteSheet.getWidth(), spriteSheet.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = reversedImage.createGraphics();
        g2d.drawImage(spriteSheet, spriteSheet.getWidth(), 0, 0, spriteSheet.getHeight(), 0, 0, spriteSheet.getWidth(), spriteSheet.getHeight(), null);
        g2d.dispose();
        spriteSheet = reversedImage;
    }
    private void scaleSpriteSheet() {
        // Tạo một bản sao thu nhỏ của sprite sheet
        scaledFrameWidth = frameWidth / 5; // Giảm chiều rộng của khung hình
        scaledFrameHeight = frameHeight / 5; // Giảm chiều cao của khung hình
        scaledSpriteSheet = new BufferedImage(scaledFrameWidth * numFrames, scaledFrameHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledSpriteSheet.createGraphics();
        for (int i = 0; i < numFrames; i++) {
            g2d.drawImage(spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight).getScaledInstance(scaledFrameWidth, scaledFrameHeight, Image.SCALE_SMOOTH), i * scaledFrameWidth, 0, null);
        }
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ animation với kích thước mới
        g.drawImage(scaledSpriteSheet.getSubimage(currentFrame * scaledFrameWidth, 0, scaledFrameWidth, scaledFrameHeight), x, y, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sprite Animation Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new move_left());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
