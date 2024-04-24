package GiaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public final class DoHoaGC extends JFrame implements KeyListener {
    private Image backGroundGC;
    private Image DiaHinhGC;
    private Image TenMapGC;
    private Image DaoTrenKhongGC; // Ảnh đảo trên không
    private Image playerImageGC; // Ảnh của player
    private Image playerDeadImageGC; // Ảnh chết của player
    private Image bossImageGC;
    private Image vongngam;
    private move_left moveLeft;
    private MoveCharacter_Boy moveRight;
    private stand upright;
    private stand_left upleft;
    private Chic_At chic_at;
    
    Player player = new Player(100,600,null,this,null);
    BulletManager bulletmanager = new BulletManager(player);
    Boss boss = new Boss(1240, 580); // Khởi tạo đối tượng boss
    AngleBar bar = new AngleBar(); // Khởi tạo vòng chọn góc
    PowerBar power = new PowerBar();
    
    public DoHoaGC() {
        setTitle("Gunny Game");
        setSize(1422, 810);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            // Load ảnh
            backGroundGC = new ImageIcon("image\\imagesGaCon\\BackGroundGaCon.jpg").getImage();
            DiaHinhGC = new ImageIcon("image\\imagesGaCon\\DiaHinhGaCon.png").getImage();
            DaoTrenKhongGC = new ImageIcon("image\\imagesGaCon\\DaoTrenKhong.png").getImage();
            TenMapGC = new ImageIcon("image\\imagesGaCon\\TenMap.png").getImage();
            playerImageGC = new ImageIcon("image\\imagesGaCon\\Boy.png").getImage(); 
            playerDeadImageGC = new ImageIcon("image\\imagesGaCon\\P_DIE.png").getImage();
            bossImageGC = new ImageIcon("image\\imagesGaCon\\BossGa.png").getImage(); 
            vongngam = new ImageIcon("image\\imagesGaCon\\VongTronNgam.png").getImage(); 
            moveLeft = new move_left();
            moveRight = new MoveCharacter_Boy();
            upright = new stand();
            upleft = new stand_left();
            chic_at = new Chic_At();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading images: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Khởi tạo các nút
        JButton backBT = new JButton("<< Back");
        
        // Thiết lập vị trí và kích thước của các nút
        backBT.setBounds(20, 20, 100, 40);
        
        // Thiết lập màu sắc cho các nút
        backBT.setBackground(Color.yellow);
        
        // Thiết lập màu chữ cho các nút
        backBT.setForeground(Color.red);
        
        // Thêm các nút vào JFrame
        add(backBT);
        player.setBullManager(bulletmanager);
        // Thêm KeyListener vào JFrame
        addKeyListener(this);
        setFocusable(true);
        
        backBT.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                GameInterface interfaceFrame = new GameInterface(); // Sử dụng kiểu dữ liệu GameInterface
                JFrame frame = new JFrame("Chọn màn chơi"); // Tạo một JFrame mới để chứa GameInterface
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(interfaceFrame);
                frame.pack();
                frame.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
                frame.setVisible(true); // Hiển thị cửa sổ giao diện chọn màn chơi
                dispose(); // Đóng cửa sổ hiện tại của trò chơi
            });
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
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Vẽ ảnh nền
        g2d.drawImage(backGroundGC, 0, 0, getWidth(), getHeight(), this);

        // Thiết lập độ trong suốt của ảnh Địa Hình
        float alpha = 1f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(DaoTrenKhongGC, 222, 100, 1000, 500, this);
        // Vẽ ảnh Địa Hình
        g2d.drawImage(DiaHinhGC, 5, -75, 1400, 850, this);
       
        // Thiết lập độ trong suốt của ảnh Tên Map
        g2d.drawImage(TenMapGC, 670, 20, 150, 50, null);
        
        float alpha2 = 1f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha2));
        g2d.drawImage(vongngam,(int) player.getPlayerX() - 80,(int) player.getPlayerY() - 80, 300, 300, this);
        // Vẽ thước ngắm
        bar.paintComponent(g,(int) player.getPlayerX() + 70,(int) player.getPlayerY() + 70);
        // Vẽ thanh căn lực
        power.paintComponent(g);
        
        if (player.getIsPlayerAlive()) {
            if (player.getIsFacingLeft()) {
            	if(player.getIsMoving()){
                    moveLeft.paintComponent(g2d);
                }
                else if (player.getUping()){
                    upleft.paintComponent(g2d);
                }
                else {
                    // Nếu nhân vật đang hướng trái, phản chiếu hình ảnh
                    g2d.drawImage(playerImageGC, (int) player.getPlayerX() + 150, (int) player.getPlayerY(), -150, 150, this);
                }
            } else {
            	if(player.getIsMoving()){
                    moveRight.paintComponent(g2d);
                }
                else if (player.getUping()){
                    upright.paintComponent(g2d);
                }
                else {
                    // Nếu nhân vật đang hướng phải, vẽ bình thường
                g2d.drawImage(playerImageGC,(int) player.getPlayerX(),(int) player.getPlayerY(), 150, 150, this);
                }
            }
        } else {
            g2d.drawImage(playerDeadImageGC,(int) player.getPlayerX() - 80,(int) player.getPlayerY() - 100, 300, 300, this);
            SwingUtilities.invokeLater(() -> {
                Defeated defeated = new Defeated(); // Tạo một thể hiện mới của GameInterface
                defeated.setVisible(true); // Hiển thị cửa sổ giao diện chọn màn chơi
                dispose(); // Đóng cửa sổ hiện tại của trò chơi
            });
        }

        
        // Vẽ thanh máu của player
        g2d.setColor(Color.GRAY);
        g2d.fillRect(player.getPlayerHealthBarX() - 1, player.getPlayerHealthBarY() - 1, player.getPlayerHealthBarWidth() + 2, player.getPlayerHealthBarHeight() + 2);

        // Tạo GradientPaint từ màu đỏ sang màu vàng dựa trên lượng máu còn lại của player
        GradientPaint playerGradient = new GradientPaint(player.getPlayerHealthBarX(), player.getPlayerHealthBarY(), Color.RED, player.getPlayerHealthBarX() + player.getPlayerHealthBarWidth(), player.getPlayerHealthBarY(), Color.YELLOW);
        g2d.setPaint(playerGradient);

        // Vẽ thanh máu của player với GradientPaint
        int playerHealthBarWidth = (int) ((double) player.getHealth() / player.getMaxHealth() * player.getPlayerHealthBarWidth());
        g2d.fillRect(player.getPlayerHealthBarX(), player.getPlayerHealthBarY(), playerHealthBarWidth, player.getPlayerHealthBarHeight());

        // Vẽ viền màu vàng xung quanh thanh máu của player
        g2d.setColor(Color.YELLOW);
        g2d.drawRect(player.getPlayerHealthBarX() - 2, player.getPlayerHealthBarY() - 2, player.getPlayerHealthBarWidth() + 2, player.getPlayerHealthBarHeight() + 2);

     // vẽ boss
        if (boss.getIsBossFacingLeft()) {
        	if(boss.getIsAttacking()){
                chic_at.paintComponent(g2d);
            }
            else {
                // Nếu boss đang hướng trái, phản chiếu hình ảnh
            g2d.drawImage(bossImageGC, boss.getBossX() + 150, boss.getBossY(), -150, 150, this);
            }
        } else {
        	if(boss.getIsAttacking()){
                chic_at.paintComponent(g2d);
            }
            else {
                // Nếu nhân vật đang hướng phải, vẽ bình thường
            g2d.drawImage(bossImageGC, boss.getBossX(), boss.getBossY(), 150, 150, this);
            }
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

        if (player.getHealth() <= 0) player.setIsPlayerAlive(false);
        bulletmanager.draw(g2d);
        // Giải phóng Graphics2D
        g2d.dispose();
    }

 // Xử lý sự kiện từ bàn phím
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN) {
        	
                {
                    bar.setAngle((bar.getAngle() + 10 ) %90); // Giảm góc đi 10 độ
                    player.setAngle(bar.getAngle());
                    System.out.println(player.getAngle());
                    repaint(); // Vẽ lại thanh
                }
        } else  if (keyCode == KeyEvent.VK_UP){
                
                bar.setAngle((bar.getAngle() - 10) % 90 );
                
                player.setAngle(bar.getAngle());// Tăng góc lên 10 độ
                System.out.println(player.getAngle());
                repaint(); // Vẽ lại thanh
            }
        
        if (keyCode == KeyEvent.VK_Y) {
            power.increasePower(); 
// Tăng mức lực khi nhấn space
            player.setPower(power.getPowerLevel());
            System.out.println(player.getPower());
            repaint();
        }
        // Xử lý phím di chuyển cho player
        if (keyCode == KeyEvent.VK_SPACE) {
                player.setIsMoving(false);
                player.setUping(true);
                if(player.getIsFacingLeft()){
                    upleft.setX((int) player.getPlayerX());
                    upleft.setY((int) player.getPlayerY());
                }
                else{
                    upright.setX((int) player.getPlayerX());
                    upright.setY((int) player.getPlayerY());
                }
                player.attack(boss);
                System.out.println("tru mau boss"+boss.getBossHealth());// Truyền giá trị powerLevel vào phương thức attack của Player
                    repaint();
            } 
        if(keyCode == KeyEvent.VK_LEFT) {
        	player.setPlayerX(player.getPlayerX() - player.getSpeed()); // Di chuyển sang trái
            player.setIsFacingLeft(true); 
            player.updateHealthBarPosition();
            player.setIsMoving(true);
            moveLeft.setX((int) player.getPlayerX());
            moveLeft.setY((int) player.getPlayerY());
        }
        if(keyCode == KeyEvent.VK_RIGHT) {
        	player.setPlayerX(player.getPlayerX() + player.getSpeed()); // Di chuyển sang trái
            player.setIsFacingLeft(false); 
            player.updateHealthBarPosition();
            player.setIsMoving(true);
            moveRight.setX((int) player.getPlayerX());
            moveRight.setY((int) player.getPlayerY());
        }
        if(keyCode == KeyEvent.VK_B) {
        	boss.setIsAttacking(false);
            boss.moveBossToRandomPosition();
        }
       
        // Cập nhật vị trí của thanh máu
            player.setPlayerHealthBarX((int)player.getPlayerX() + 30);
            player.setPlayerHealthBarY((int)player.getPlayerY() - player.getPlayerHealthBarHeight() + 10);

        // Vẽ lại màn hình
                repaint();
        
        
        
    }

    // Các phương thức còn lại của KeyListener không cần xử lý
    

     public void keyTyped(KeyEvent e) {
        

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_SPACE -> {
            	boss.setIsAttacking(true);
                boss.moveToPlayer((int)player.getPlayerX(),(int) player.getPlayerY());
                if (boss.getIsAttacking()) {
                    // Nếu Boss đang tấn công, vẽ animation tấn công của Boss và giảm máu của người chơi
                    chic_at.setX(boss.getBossX() -10);
                    chic_at.setY(boss.getBossY() -40 );
                    System.out.println("ack!");
                    System.out.println(player.getHealth());// Đánh dấu Boss đã kết thúc việc tấn công
                    
                }
                boss.attackPlayer(player);
                System.out.println(player.getHealth());
                boss.moveBossToRandomPosition();
                power.resetPower();
            }
        }
    }
    
    public void StopForAMoment()
    {
        // Dừng một chút để tạo hiệu ứng di chuyển liên tục mịn màng
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DoHoaGC(); 
        });
    }
}
