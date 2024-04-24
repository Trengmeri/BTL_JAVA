package GiaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ThanhGocBan extends JPanel implements KeyListener, ActionListener {
    private int angle = 60; // Góc ban đầu là 60 độ
    private final int width = 300;
    private final int height = 300;
    private final int handLength = 100;
    private final int delay = 1000; // milliseconds
    private final Timer timer;

    public ThanhGocBan() {
        timer = new Timer(delay, this);
        timer.start();

        this.setFocusable(true); // Cho phép JPanel nhận focus để lắng nghe sự kiện từ bàn phím
        this.addKeyListener(this); // Thêm bộ lắng nghe sự kiện từ bàn phím cho JPanel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = width / 2;
        int centerY = height / 2;

        // Xóa màn hình
        g.clearRect(0, 0, width, height);

        // Vẽ thanh
        int handX = (int) (centerX + handLength * Math.cos(Math.toRadians(angle)));
        int handY = (int) (centerY + handLength * Math.sin(Math.toRadians(angle)));
        g.drawLine(centerX, centerY, handX, handY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Không cần làm gì khi timer chạy
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN) {
            angle = (angle + 10) % 360; // Tăng góc lên 10 độ
            repaint(); // Vẽ lại thanh
        } else if (keyCode == KeyEvent.VK_UP) {
            angle = (angle - 10 + 360) % 360; // Giảm góc đi 10 độ
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thanh Góc Bắn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            frame.add(new ThanhGocBan());
            frame.setVisible(true);
        });
    }
}
