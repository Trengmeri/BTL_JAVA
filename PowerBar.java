package GiaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PowerBar extends JPanel implements KeyListener {
    private int powerLevel = 0; // Mức lực ban đầu là 0
    private final int barLength = 200;
    private final int barHeight = 20;
    private int powerX = 50; // Vị trí X của thanh lực
    private int powerY = 150; // Vị trí Y của thanh lực
    private Image powerline;

    public PowerBar() {
        setPreferredSize(new Dimension(220, 50));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this); // Thêm bộ lắng nghe sự kiện từ bàn phím cho JPanel
        try {
            // Load ảnh
            powerline = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\image\\power-line.png").getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading images: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Vẽ powerline
        g2d.drawImage(powerline, 38, 125, 70, 250, this);

        // Tính toán chiều dài của thanh lực dựa trên mức lực hiện tại
        int filledLength = (int) (barLength * (powerLevel / 100.0));

        // Tạo GradientPaint để tạo màu chuyển đổi từ xanh đến xanh lá cây
        GradientPaint gradient = new GradientPaint(powerX, powerY, new Color(0, 0, 255), powerX + barHeight, powerY + barLength, new Color(0, 255, 0));

        // Sử dụng GradientPaint để vẽ thanh lực
        g2d.setPaint(gradient);
        g2d.fillRect(powerX + 3, powerY + barLength - filledLength, barHeight - 3, filledLength);

        g2d.dispose(); // Giải phóng tài nguyên đồ hoạ
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
