import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DuongDanBay extends JPanel {
    private double bulletX; // Vị trí x ban đầu của viên đạn
    private double bulletY; // Vị trí y ban đầu của viên đạn
    private int thanhLuc = 50; // Thanh lực (giá trị từ 1 đến 100)
    private double bulletSpeed = 300 + thanhLuc * 50; // Vận tốc ban đầu của viên đạn (px/s)
    private double bulletAngle; // Góc bắn (được chuyển đổi từ độ sang radian)
    private double bulletSpeedX; // Vận tốc theo trục x của viên đạn (px/s)
    private double bulletSpeedY; // Vận tốc theo trục y của viên đạn (px/s)
    private int frameWidth = 1422;
    private int frameHeight = 810;
    private double gravity = 700; // Gia tốc trọng trường (px/s^2)

    public DuongDanBay(int thanhLuc, double gocBan, double startX, double startY) {
        this.thanhLuc = Math.max(1, Math.min(100, thanhLuc)); // Đảm bảo thanhLuc nằm trong khoảng từ 1 đến 100
        this.bulletSpeed = 200 + this.thanhLuc * 10; // Cập nhật vận tốc ban đầu dựa trên thanh lực
        this.bulletAngle = Math.toRadians(gocBan); // Chuyển đổi góc bắn từ độ sang radian
        this.bulletX = startX; // Cập nhật vị trí x ban đầu
        this.bulletY = startY; // Cập nhật vị trí y ban đầu
        this.bulletSpeedX = bulletSpeed * Math.cos(bulletAngle); // Cập nhật vận tốc theo trục x
        this.bulletSpeedY = -bulletSpeed * Math.sin(bulletAngle); // Cập nhật vận tốc theo trục y
        Timer timer = new Timer(10, e -> {
            moveBullet(); // Di chuyển viên đạn
            repaint(); // Yêu cầu vẽ lại JPanel
        });
        timer.start();
    }
    private boolean checkCollision1() {
        // Kiểm tra xem viên đạn có chạm vào các vùng cản trở không thể rơi
        return ( (bulletY >= 666 && bulletY <= 670) ||
                (bulletX >= 335 && bulletX <= 521 && bulletY >= 420 && bulletY <= 424 ) ||
                (bulletX >= 910 && bulletX <= 1114 && bulletY >= 420 && bulletY <= 424) ||
                (bulletX >= 607 && bulletX <= 815 && bulletY >= 579 && bulletY <= 583)) ;
    }
    private boolean checkCollision2() {
        // Kiểm tra xem viên đạn có chạm vào các vùng cản trở có thể rơi
        return (bulletX == 0 || bulletX == frameWidth ||
                (bulletX >= 035 && bulletX <= 1121 && bulletY >= 622 && bulletY <= 667) ||
                (bulletX >= 910 && bulletX <= 1114 && bulletY >= 442 && bulletY <= 467) ||
                (bulletX >= 607 && bulletX <= 815 && bulletY >= 581 && bulletY <= 623));
    }

    private void moveBullet() {
        // Tính toán sự di chuyển theo trục x và y dựa trên vận tốc và gia tốc trọng trường
        double timeInSeconds = 0.005; // Thời gian mỗi bước đếm (10ms = 0.01s)
        bulletX += bulletSpeedX * timeInSeconds; // Di chuyển theo trục x

        // Tính toán vận tốc theo trục y dựa trên gia tốc trọng trường
        bulletSpeedY += gravity * timeInSeconds;
        bulletY += bulletSpeedY * timeInSeconds; // Di chuyển theo trục y

        // Kiểm tra va chạm
       
        if(checkCollision1()){
            bulletSpeedX = 0;
            bulletSpeedY = 0;
            gravity = 0;
        }   
        if(checkCollision1()){
            bulletSpeedX = 0;
            bulletSpeedY = 0;
        }   
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBullet(g);
    }

    private void drawBullet(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) bulletX, (int) bulletY, 10, 10); // Vẽ một hình tròn đại diện cho viên đạn
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Đường Dẫn Bay");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1422, 810);
        int thanhLuc = 90; // Thanh lực
        double gocBan = 50; // Góc bắn
        double startX = 100; // Vị trí x ban đầu
        double startY = 654; // Vị trí y ban đầu
        DuongDanBay duongDanBay = new DuongDanBay(thanhLuc, gocBan, startX, startY);
        frame.add(duongDanBay);
        frame.setVisible(true);
    }
}