/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameGunny;

/**
 *
 * @author admin
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class DoHoaTTK extends JFrame implements KeyListener {
    private Image backGroundTTK;
    private Image DiaHinhTTK;
    private Image TenMapTTK;
    private Image DaoTrenKhong; // Ảnh đảo trên không
    private Image playerImageTTK; // Ảnh của player
    private Image playerDeadImageTTK; // Ảnh chết của player
    private Image bossImageTTK;
    
    PlayerTTK player = new PlayerTTK(100,6,150,90,100,600,this);
    BulletManager bulletmanager = new BulletManager(player);
    BossTTK boss = new BossTTK(1240, 576); // Khởi tạo đối tượng boss
    private Timer timer;
    private boolean increasing;
    
     private JSlider angleSlider;
    
    public DoHoaTTK() {
        setTitle("Gunny Game");
        setSize(1422, 810);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
            player.setBulletManager(bulletmanager);

        try {
            // Load ảnh
            backGroundTTK = new ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\BTL2\\src\\main\\java\\img\\BackGroundTTK.jpg").getImage();
            DiaHinhTTK = new ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\BTL2\\src\\main\\java\\img\\DiaHinhTTK.png").getImage();
            DaoTrenKhong = new ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\BTL2\\src\\main\\java\\img\\DaoTrenKhong.png").getImage();
            TenMapTTK = new ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\BTL2\\src\\main\\java\\img\\TenMapTTK.png").getImage();
            playerImageTTK = new ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\BTL2\\src\\main\\java\\img\\BoyTTK.png").getImage(); 
            playerDeadImageTTK = new ImageIcon("").getImage();
            bossImageTTK = new ImageIcon("C:\\Users\\admin\\Documents\\NetBeansProjects\\BTL2\\src\\main\\java\\img\\BossTTK.png").getImage(); 
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading images: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Khởi tạo các nút
        JButton backBT = new JButton("<< Back");
        JButton powerBT = new JButton("Bộc phá");
        JButton flyBT = new JButton("Bay");
        JButton x2BT = new JButton("x2 Đạn");
        
        // Thiết lập vị trí và kích thước của các nút
        backBT.setBounds(20, 20, 100, 40);
        powerBT.setBounds(40, 180, 100, 40);
        flyBT.setBounds(40, 240, 100, 40);
        x2BT.setBounds(40, 300, 100, 40);
        
        // Thiết lập màu sắc cho các nút
        backBT.setBackground(Color.yellow);
        powerBT.setBackground(Color.red);
        flyBT.setBackground(Color.green);
        x2BT.setBackground(Color.yellow);
        
        // Thiết lập màu chữ cho các nút
        backBT.setForeground(Color.red);
        powerBT.setForeground(Color.yellow);
        flyBT.setForeground(Color.red);
        x2BT.setForeground(Color.red);
        
        // Thêm các nút vào JFrame
        add(backBT);
        add(powerBT);
        add(flyBT);
        add(x2BT);
        
        // Thêm KeyListener vào JFrame
        addKeyListener(this);
        setFocusable(true);
        angleSlider = new JSlider(JSlider.VERTICAL, 0, 90, 45);
        angleSlider.setMajorTickSpacing(10);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);
        // Đặt vị trí của angleSlider gần người chơi
        angleSlider.setBounds((int)player.getPosX() + 60,(int) player.getPosY() - 100, 50, 150);
        add(angleSlider);
        angleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int angle = source.getValue();
                    player.setAngle(angle);
                    repaint();
                }
            }
        });
        // Tạo một lớp JPanel mới để vẽ hình ảnh và thanh máu
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        // Thêm panel vào JFrame
        add(panel);
        // Hiển thị cửa sổ
        setVisible(true);   
    }
    public void Update(){
        player.Update();
        bulletmanager.UpdateObjects();
        bulletmanager.updateBullets();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Vẽ vòng cung chỉ góc
        int centerX =(int) player.getPosX() + (int)player.getWidth() / 2;
        int centerY = (int) player.getPosY() +(int) player.getHeight() / 2;
        int radius = 50; // Bán kính của vòng cung
        int startAngle = 90 - 45;
        int arcAngle = 2 * 45;
        g2d.setColor(Color.BLACK);
        g2d.drawArc(centerX - radius, centerY - radius , 2 * radius, 2 * radius, startAngle, arcAngle);
        int flatLength = 10; // Chiều dài phần bị dẹp vào
    
         // các thanh nhỏ trên vòng cung
    }
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Vẽ ảnh nền
        g2d.drawImage(backGroundTTK, 0, 0, getWidth(), getHeight(), this);

        // Thiết lập độ trong suốt của ảnh Địa Hình
        float alpha = 1f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(DaoTrenKhong, 222, 100, 1000, 500, this);
        // Vẽ ảnh Địa Hình
        g2d.drawImage(DiaHinhTTK, 0, 205, 1450, 900, this);
       
        // Thiết lập độ trong suốt của ảnh Tên Map
        g2d.drawImage(TenMapTTK, 500, -50, 450, 200, null);

        // Vẽ player
        if (player.getIsFacingLeft() ) {
            // Nếu nhân vật đang hướng trái, phản chiếu hình ảnh
            g2d.drawImage(playerImageTTK,(int) player.getPosX() + 150, (int)player.getPosY(), -150, 150, this);
        } else
                {
            // Nếu nhân vật đang hướng phải, vẽ bình thường
            g2d.drawImage(playerImageTTK,(int) player.getPosX(),(int) player.getPosY(), 150, 150, this);
        }

        // Vẽ thanh máu của player
        g2d.setColor(Color.GRAY);
        g2d.fillRect((int)player.getPlayerHealthBarX() - 1,(int) player.getPlayerHealthBarY() - 1,(int)player.getPlayerHealthBarWidth() + 2,(int) player.getPlayerHealthBarHeight() + 2);

        // Tạo GradientPaint từ màu đỏ sang màu vàng dựa trên lượng máu còn lại của player
        GradientPaint playerGradient = new GradientPaint((int)player.getPlayerHealthBarX(),(int) player.getPlayerHealthBarY(), Color.RED,(int) player.getPlayerHealthBarX() +(int) player.getPlayerHealthBarWidth(),(int) player.getPlayerHealthBarY(), Color.YELLOW);
        g2d.setPaint(playerGradient);

        // Vẽ thanh máu của player với GradientPaint
        int playerHealthBarWidth = (int) ( player.getHealth() / player.getHealth() * player.getPlayerHealthBarWidth());
        g2d.fillRect((int)player.getPlayerHealthBarX(),(int) player.getPlayerHealthBarY(), playerHealthBarWidth,(int) player.getPlayerHealthBarHeight());

        // Vẽ viền màu vàng xung quanh thanh máu của player
        g2d.setColor(Color.YELLOW);
        g2d.drawRect((int)player.getPlayerHealthBarX() - 2,(int) player.getPlayerHealthBarY() - 2,(int) player.getPlayerHealthBarWidth() + 2,(int) player.getPlayerHealthBarHeight() + 2);

        // vẽ boss
        if (boss.getIsBossFacingLeft()) {
            // Nếu boss đang hướng trái, phản chiếu hình ảnh
            g2d.drawImage(bossImageTTK, boss.getBossX() + 150, boss.getBossY(), -150, 150, this);
        } else {
            // Nếu nhân vật đang hướng phải, vẽ bình thường
            g2d.drawImage(bossImageTTK, boss.getBossX(), boss.getBossY(), 150, 150, this);
        }
        // Tương tự cho thanh máu của boss
        g2d.setColor(Color.GRAY);
        g2d.fillRect(boss.getBossHealthBarX() - 1, boss.getBossHealthBarY() - 1, boss.getBossHealthBarWidth() + 2, boss.getBossHealthBarHeight() + 2);

        // Tạo GradientPaint từ màu đỏ sang màu vàng dựa trên lượng máu còn lại của boss
        GradientPaint bossGradient = new GradientPaint(boss.getBossHealthBarX(), boss.getBossHealthBarY(), Color.RED, boss.getBossHealthBarX() + boss.getBossHealthBarWidth(), boss.getBossHealthBarY(), Color.YELLOW);
        g2d.setPaint(bossGradient);

        // Vẽ thanh máu của boss với GradientPaint
        int bossHealthBarWidth = (int) ((double) boss.getBossHealth() / boss.getBossMaxHealth() * boss.getBossHealthBarWidth());
        g2d.fillRect(boss.getBossHealthBarX(), boss.getBossHealthBarY(), bossHealthBarWidth, boss.getBossHealthBarHeight());

        // Vẽ viền màu vàng xung quanh thanh máu của boss
        g2d.setColor(Color.YELLOW);
        g2d.drawRect(boss.getBossHealthBarX() - 2, boss.getBossHealthBarY() - 2, boss.getBossHealthBarWidth() + 2, boss.getBossHealthBarHeight() + 2);

        // Vẽ player khi chết
        if (!player.getIsPlayerAlive())
        g2d.drawImage(playerDeadImageTTK,(int) player.getPosX() ,(int) player.getPosY(), 140, 140, this);
//        ArrayList<Point> bulletPath = player.getBulletPath();
//        if (bulletPath.size() > 1) {
//        for (int i = 0; i < bulletPath.size() - 1; i++) {
//            bulletmanager.draw(g2d);
//        }
//    }
        bulletmanager.draw(g2d);
        g2d.dispose();
    }

    // Xử lý sự kiện từ bàn phím
    @Override
    public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyCode == KeyEvent.VK_UP) {
            // Nếu người chơi bấm phím lên, tăng giá trị góc của thanh chỉ hướng
            int currentAngle = angleSlider.getValue();
            if (currentAngle < 90) {
                angleSlider.setValue(currentAngle + 1);
                player.setAngle(currentAngle + 1); // Cập nhật giá trị angle trong PlayerTTK
                repaint();
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            // Nếu người chơi bấm phím xuống, giảm giá trị góc của thanh chỉ hướng
            int currentAngle = angleSlider.getValue();
            if (currentAngle > 0) {
                angleSlider.setValue(currentAngle - 1);
                player.setAngle(currentAngle - 1); // Cập nhật giá trị angle trong PlayerTTK
                repaint();
            }
    } else if (keyCode == KeyEvent.VK_SPACE) {
        player.attack(); // Truyền giá trị powerLevel vào phương thức attack của Player
        repaint();
    } else {
        player.move(e); // Di chuyển Player
        angleSlider.setLocation((int)player.getPosX() + 60,(int) player.getPosY() - 100);
        repaint();
    } 
         
    player.setPlayerHealthBarX(player.getPosX() + 30);
    player.setPlayerHealthBarY(player.getPosY() - player.getPlayerHealthBarHeight() + 10);
    repaint();
}

    // Các phương thức còn lại của KeyListener không cần xử lý
    @Override
    public void keyTyped(KeyEvent e) {
    }

//    @Override
//    public void keyReleased(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//        switch (keyCode) {
//            case KeyEvent.VK_SPACE -> {
//                boss.moveToPlayer((int)player.getPosX(),(int) player.getPosY());
//                boss.attackPlayer(player);
//                System.out.println(player.getHealth());
//                boss.moveBossToRandomPosition();
//            }   
//        }
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DoHoaTTK(); 
        });
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
