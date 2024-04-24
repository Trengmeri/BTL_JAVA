package GiaoDien;
import javax.swing.*;
import java.awt.*;

public class GameInterface extends JPanel {
    private Image backgroundImage;
    private int padding = 20; // Đặt giá trị của padding
    private int padding_top = 70;

    public GameInterface() {
        loadImage("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\image\\bgr.png"); // Load hình ảnh nền cho bảng
        
     // Đặt padding cho JPanel Shop
        setBorder(BorderFactory.createEmptyBorder(padding_top, padding, padding, padding));
        
     // Đặt phần nền của JPanel trong suốt
        setOpaque(false);

        // Đặt kích thước cho bảng shop
        setPreferredSize(new Dimension(960, 640));
        
        // Sử dụng GridBagLayout để căn giữa các nút
        setLayout(new GridBagLayout());
        
        // Thêm nhãn văn bản trên cùng giao diện
        addTitleLabel();
        
        // Thêm một hộp chứa để căn giữa các nút
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(Box.createGlue(), gbc);
        
        // Thêm ba nút vào giao diện và thiết lập hình ảnh cho chúng
        addButtons();
        
        // Thêm một hộp chứa để căn giữa các nút
        gbc.gridy = 0;
        add(Box.createGlue(), gbc);
    }

    private void loadImage(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }
    
    private void addTitleLabel() {
        // Tạo nhãn văn bản
        JLabel titleLabel = new JLabel("CHỌN MÀN CHƠI");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Thiết lập font và kích thước cho văn bản
        titleLabel.setForeground(Color.YELLOW); // Thiết lập màu văn bản
        
        // Thiết lập hình nền cho nhãn văn bản
        ImageIcon labelBackground = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\image\\nhãn.png");
        titleLabel.setIcon(labelBackground);
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER); // Căn văn bản vào giữa theo chiều ngang
        titleLabel.setVerticalTextPosition(SwingConstants.CENTER); // Căn văn bản vào giữa theo chiều dọc
        
        // Thêm nhãn văn bản vào giao diện
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(-100, 0, 10, 0); // Đặt khoảng cách trên và dưới cho nhãn
        add(titleLabel, gbc);
    }

    private void addButtons() {
        // Tạo ba nút
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        
        // Thiết lập hình ảnh cho từng nút
        ImageIcon icon1 = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\image\\chapter\\1.png");
        ImageIcon icon2 = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\image\\chapter\\2.png");
        ImageIcon icon3 = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\image\\chapter\\3.png");
        
        button1.setIcon(icon1);
        button2.setIcon(icon2);
        button3.setIcon(icon3);
        
        // Đặt phần nền của các nút là trong suốt
        button1.setOpaque(false);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        
        button2.setOpaque(false);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        
        button3.setOpaque(false);
        button3.setContentAreaFilled(false);
        button3.setBorderPainted(false);
        
        button1.addActionListener(e -> {
            Chapter1 chapter1 = new Chapter1();
            chapter1.displayChapterInterface();
        });
        
        button2.addActionListener(e -> {
            Chapter2 chapter2 = new Chapter2();
            chapter2.displayChapterInterface();
        });
        
        button3.addActionListener(e -> {
            Chapter3 chapter3 = new Chapter3();
            chapter3.displayChapterInterface();
        });
        
        // Thêm các nút vào giao diện
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0); // Đặt khoảng cách trên và dưới cho nút
        add(button1, gbc);
        
        gbc.gridy = 2;
        add(button2, gbc);
        
        gbc.gridy = 3;
        add(button3, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ hình ảnh nền của bảng Shop
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game Interface");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new GameInterface());
            frame.pack();
            frame.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
            frame.setVisible(true);
        });
    }
}
