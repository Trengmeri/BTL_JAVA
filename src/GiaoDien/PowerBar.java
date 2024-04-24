package GiaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PowerBar extends JPanel implements KeyListener {
    private int powerLevel = 0; // Mức lực ban đầu là 0
    private final int barLength = 200;
    private final int barHeight = 20;
    private int powerX = 550; // Vị trí X của thanh lực
    private int powerY = 100; // Vị trí Y của thanh lực

    public PowerBar() {
        setPreferredSize(new Dimension(220, 50));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this); // Thêm bộ lắng nghe sự kiện từ bàn phím cho JPanel
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vẽ khung thanh lực
        g.setColor(Color.BLACK);
        g.drawRect(powerX, powerY, barLength, barHeight);

        // Tính toán chiều dài của thanh lực dựa trên mức lực hiện tại
        int filledLength = (int) (barLength * (powerLevel / 100.0));

        // Màu của thanh lực
        Color barColor = (powerLevel == 0) ? Color.BLUE : Color.GREEN;

        // Vẽ thanh lực
        g.setColor(barColor);
        g.fillRect(powerX, powerY, filledLength, barHeight);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            increasePower(); // Tăng mức lực khi nhấn space
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            resetPower(); // Reset mức lực khi nhả space
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không cần làm gì khi có ký tự được gõ từ bàn phím
    }

    void increasePower() {
        powerLevel += 2; // Tăng mức lực lên 2 đơn vị
        if (powerLevel > 100) {
            powerLevel = 100;
        }
        repaint(); // Vẽ lại thanh lực sau khi thay đổi mức lực
    }

    void resetPower() {
        powerLevel = 0; // Reset mức lực về 0
        repaint(); // Vẽ lại thanh lực sau khi reset
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
        repaint(); // Vẽ lại thanh lực khi cập nhật mức lực
    }
}
