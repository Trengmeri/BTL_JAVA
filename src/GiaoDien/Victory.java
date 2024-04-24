package GiaoDien;
import javax.swing.*;
import java.awt.*;

public class Victory extends JPanel {
    private Image backgroundImage;
    private int padding = 20; // Đặt giá trị của padding
    private int padding_top = 70;

    public Victory() {
        loadImage("image/bgr.png"); // Load hình ảnh nền cho bảng
        
     // Đặt padding cho JPanel Shop
        setBorder(BorderFactory.createEmptyBorder(padding_top, padding, padding, padding));
        
     // Đặt phần nền của JPanel trong suốt
        setOpaque(false);

        // Đặt kích thước cho bảng shop
        setPreferredSize(new Dimension(500, 200));
        
        // Sử dụng GridBagLayout để căn giữa các nút
        setLayout(new GridBagLayout());
        
        // Thêm nhãn văn bản trên cùng giao diện
        addTitleLabel();
    }

    private void loadImage(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }
    
    private void addTitleLabel() {
        // Tạo nhãn văn bản
        JLabel titleLabel = new JLabel("VICTORY");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Thiết lập font và kích thước cho văn bản
        titleLabel.setForeground(Color.YELLOW); // Thiết lập màu văn bản
        
        // Thiết lập hình nền cho nhãn văn bản
        ImageIcon labelBackground = new ImageIcon("image/nhãn.png");
        titleLabel.setIcon(labelBackground);
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER); // Căn văn bản vào giữa theo chiều ngang
        titleLabel.setVerticalTextPosition(SwingConstants.CENTER); // Căn văn bản vào giữa theo chiều dọc
        
        // Thêm nhãn văn bản vào giao diện
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(-30, 0, 10, 0); // Đặt khoảng cách trên và dưới cho nhãn
        add(titleLabel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Victory");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Victory());
            frame.pack();
            frame.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
            frame.setVisible(true);
        });
    }
}
