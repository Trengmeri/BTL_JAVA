package GiaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThanhLucBan extends JFrame {
    private JProgressBar powerBar;
    private int powerLevel;
    private Timer timer;
    private boolean increasing;

    public ThanhLucBan() {
        setTitle("Gunny Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 80));

        // Khởi tạo thanh đo lực
        powerBar = new JProgressBar(0, 100);
        powerBar.setStringPainted(true);
        powerBar.setForeground(Color.BLUE);
        powerBar.setValue(0);

        // Thêm thanh đo lực vào khung chính
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(powerBar, BorderLayout.CENTER);

        // Khởi tạo Timer với khoảng thời gian 50 milliseconds (0.05 giây)
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increasePower();
            }
        });

        // Xử lý sự kiện khi nhấn và nhả nút space
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    increasing = true;
                    timer.start(); // Bắt đầu Timer khi nút space được nhấn
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    increasing = false;
                    timer.stop(); // Dừng Timer khi nút space được nhả
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);
    }

    // Phương thức tăng lực
    private void increasePower() {
        powerLevel += 1;
        if (powerLevel >= 100) {
            powerLevel = 100;
            increasing = false; 
        }
        powerBar.setValue(powerLevel);
    }

    // Phương thức bắt đầu chương trình
    public void start() {
        // Bắt đầu chương trình ở đây
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThanhLucBan());
    }
}
