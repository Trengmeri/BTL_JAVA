package GiaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AngleBar extends JPanel implements KeyListener, ActionListener {
    private int angle = 0; // Góc ban đầu là 60 độ
    private final int handLength = 100;
    private final int delay = 1000; // milliseconds
    private Timer timer;

    public AngleBar() {
        timer = new Timer(delay, this);
        timer.start();

        this.setFocusable(true); // Cho phép JPanel nhận focus để lắng nghe sự kiện từ bàn phím
        this.addKeyListener(this); // Thêm bộ lắng nghe sự kiện từ bàn phím cho JPanel
    }

    protected void paintComponent(Graphics g, int px, int py) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        int centerX = px;
        int centerY = py;

        // Vẽ thanh
        int handX = (int) (centerX + handLength * Math.cos(Math.toRadians(angle)));
        int handY = (int) (centerY + handLength * Math.sin(Math.toRadians(angle)));
        g.drawLine(centerX, centerY, handX, handY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Không cần làm gì khi timer chạy
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
    if (keyCode == KeyEvent.VK_DOWN) {
        angle = (angle + 10) % 90; // Tăng góc lên 10 độ và giới hạn trong khoảng từ 0 đến 90
        repaint(); // Vẽ lại thanh
    } else if (keyCode == KeyEvent.VK_UP) {
        angle = (angle - 10) % 90; // Giảm góc đi 10 độ và giới hạn trong khoảng từ 0 đến 90
        repaint(); // Vẽ lại thanh
    }
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        // Không cần làm gì khi phím được nhả ra
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không cần làm gì khi có ký tự được gõ từ bàn phím
    }
}
