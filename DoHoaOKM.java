package GiaoDien;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DoHoaOKM extends JFrame implements KeyListener {
    private Image backGroundOKM;
    private Image DiaHinhOKM;
    private Image TenMapOKM;
    private Image DaoTrenKhongOKM; // Ảnh đảo trên không
    private Image playerImageOKM; // Ảnh của player
    private Image playerDeadImageOKM; // Ảnh chết của player
    private Image bossImageOKM;
    private Image vongngam;
    private move_left moveLeft;
    private MoveCharacter_Boy moveRight;
    private stand upright;
    private stand_left upleft;
    private ant Ant;
    private int currentFrame = 0;
    private int numFrames = 5; // Số lượng khung hình trong sprite sheet
    private int frameDelay = 100; // Độ trễ giữa các khung hình (milliseconds)
    private long lastFrameTime;
    
    Player player = new Player(100,600,this,null,null);
    BulletManager bulletmanager = new BulletManager(player);
    Boss boss = new Boss(500, 250); // Khởi tạo đối tượng boss
    AngleBar bar = new AngleBar(); // Khởi tạo vòng chọn góc
    PowerBar power = new PowerBar(); // Khởi tạo thanh căn lực

    public DoHoaOKM() {
        setTitle("Gunny Game");
        setSize(1422, 810);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            // Load ảnh
            backGroundOKM = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\imagesOKM\\backGroundOKM.jpg").getImage();
            DiaHinhOKM = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\imagesOKM\\DiaHinhOKM.png").getImage();
            DaoTrenKhongOKM = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\imagesOKM\\DaoTrenKhongOKM.png").getImage();
            TenMapOKM = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\imagesOKM\\TenMapOKM.png").getImage();
            playerImageOKM = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\imagesOKM\\BoyOKM.png").getImage(); 
            playerDeadImageOKM = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\imagesOKM\\P_DIE.png").getImage();
            bossImageOKM = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\imagesOKM\\BossOKM.png").getImage(); 
            vongngam = new ImageIcon("D:\\Kunny_1\\Kunny_1\\src\\main\\java\\imagesGaCon\\VongTronNgam.png").getImage(); 
            moveLeft = new move_left();
            moveRight = new MoveCharacter_Boy();
            upright = new stand();
            upleft = new stand_left();
            Ant = new ant();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading images: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        add(Ant);
        
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
    public void Update(){
        player.Update();
        bulletmanager.UpdateObjects();
        bulletmanager.updateBullets();
    }
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Vẽ ảnh nền
        g2d.drawImage(backGroundOKM, 0, 0, getWidth(), getHeight(), this);

        // Thiết lập độ trong suốt của ảnh Địa Hình
        float alpha = 1f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(DaoTrenKhongOKM, 400, 100, 600, 400, this);
        // Vẽ ảnh Địa Hình
        g2d.drawImage(DiaHinhOKM, 0, 205, 1450, 680, this);
       
        // Thiết lập độ trong suốt của ảnh Tên Map
        g2d.drawImage(TenMapOKM, 500, -50, 450, 200, null);

        // vẽ vòng ngắm
        float alpha2 = 1f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha2));
        g2d.drawImage(vongngam,(int) player.getPlayerX() - 80,(int) player.getPlayerY() - 80, 300, 300, this);
        // Vẽ thước ngắm
        bar.paintComponent(g,(int) player.getPlayerX() + 70,(int) player.getPlayerY() + 70);
        // Vẽ thanh căn lực
        power.paintComponent(g);
        
        // Vẽ player	
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
                    g2d.drawImage(playerImageOKM, (int) player.getPlayerX() + 150, (int) player.getPlayerY(), -150, 150, this);
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
                g2d.drawImage(playerImageOKM,(int) player.getPlayerX(),(int) player.getPlayerY(), 150, 150, this);
                }
            }
        } else {
            g2d.drawImage(playerDeadImageOKM,(int) player.getPlayerX() - 80,(int) player.getPlayerY() - 100, 300, 300, this);
            //Hiển thị giao diện Defeated
            Defeated defeatedPanel = new Defeated();
            JFrame frame = new JFrame("Defeated");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(defeatedPanel);
            frame.pack();
            frame.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
            frame.setVisible(true);
            dispose();
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
        if(boss.getIsBossAlive()) {
            if (boss.getIsBossFacingLeft()) {
                    if(boss.getIsAttacking()){
                    Ant.paintComponent(g2d);
                }
                else {
                    // Nếu boss đang hướng trái, phản chiếu hình ảnh
                g2d.drawImage(bossImageOKM, boss.getBossX() + 150, boss.getBossY(), -150, 150, this);
                }
            } else {
                    if(boss.getIsAttacking()){
                    Ant.paintComponent(g2d);
                }
                else {
                    // Nếu nhân vật đang hướng phải, vẽ bình thường
                g2d.drawImage(bossImageOKM, boss.getBossX(), boss.getBossY(), 150, 150, this);
                }
            }
        } else {
            //Hiển thị giao diện Victory
            Victory victoryPanel = new Victory();
            JFrame frame = new JFrame("Victory");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(victoryPanel);
            frame.pack();
            frame.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
            frame.setVisible(true);
            dispose();
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

        // Khi player chết
        if (player.getHealth() <= 0) player.setIsPlayerAlive(false);
        bulletmanager.draw(g2d);
        
        // Khi boss chết
        if (boss.getBossHealth() <= 0) boss.setIsBossAlive(false);
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
            bulletmanager.clearBullets();
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
                boss.moveBossToRandomPosition(0);
        }
       
        // Cập nhật vị trí của thanh máu
            player.setPlayerHealthBarX((int)player.getPlayerX() + 30);
            player.setPlayerHealthBarY((int)player.getPlayerY() - player.getPlayerHealthBarHeight() + 10);

        // Vẽ lại màn hình
                repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_SPACE -> {
            	boss.setIsAttacking(true);
                boss.moveToPlayer((int)player.getPlayerX(),(int) player.getPlayerY());
                if (boss.getIsAttacking()) {
                    // Nếu Boss đang tấn công, vẽ animation tấn công của Boss và giảm máu của người chơi
                    Ant.setX(boss.getBossX() -10);
                    Ant.setY(boss.getBossY() -40 );
                    System.out.println("ack!");
                    System.out.println(player.getHealth());// Đánh dấu Boss đã kết thúc việc tấn công
                }
                boss.attackPlayer(player);
                System.out.println(player.getHealth());
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
            new DoHoaOKM(); 
        });
    }
}
