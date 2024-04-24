package GiaoDien;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Boss {
    private final int BOSS_SPEED = 10; // Tốc độ di chuyển của boss
    private int bossX = 1240; // Vị trí x của nhân vật số 2
    private int bossY = 576; // Vị trí y của nhân vật số 2
    private Image bossImageGC;
    
    private boolean isBossFacingLeft = false; // Theo dõi hướng di chuyển của player
    private boolean isBossAlive = true; // Theo dõi còn sống
    private boolean isAttack = false;
    
    //Thanh máu của nhân vật số 2
    private int bossHealth = 100; // Sức khỏe ban đầu của nhân vật số 2
    private int bossMaxHealth = 100; // Sức khỏe tối đa của nhân vật số 2
    private int bossHealthBarWidth = 90; // Độ rộng của thanh máu của nhân vật số 2
    private int bossHealthBarHeight = 10; // Độ cao của thanh máu của nhân vật số 2
    private int bossHealthBarX = bossX ; // Vị trí x của thanh máu của nhân vật số 2
    private int bossHealthBarY = bossY - bossHealthBarHeight - 4 ; // Vị trí y của thanh máu của nhân vật số 2

    // Constructor
    public Boss(int initialX, int initialY) {
        this.bossX = initialX;
        this.bossY = initialY;
    }

    // Phương thức di chuyển boss đến vị trí player
    public void moveToPlayer(int playerX, int playerY) {
        while (true) {
            // Xác định hướng di chuyển
            int dx = playerX - bossX;
            int dy = playerY - bossY;

            // Tính toán hướng di chuyển và khoảng cách
            double distance = Math.sqrt(dx * dx + dy * dy);
            double ratio = 10 / distance;
            int moveX = (int) (dx * ratio);
            int moveY = (int) (dy * ratio);

            // Di chuyển boss
            bossX += moveX;
            bossY += moveY;
            
            

            // Kiểm tra xem boss có nằm gần player không
            if (Math.abs(bossX - playerX) < 10 && Math.abs(bossY - playerY) < 10) {
                System.out.println("Boss da di chuyen den canh player.");
                break; // Nếu có, dừng vòng lặp
            }

            // Cập nhật vị trí của thanh máu
            bossHealthBarX = bossX + 20;
            bossHealthBarY = bossY - bossHealthBarHeight + 10;

            
        }

    }

    // Phương thức gây sát thương cho player
    public void attackPlayer(Player player) {
        // Kiểm tra xem player có ở gần boss không
        int distanceX = Math.abs(player.getPlayerX() - bossX);
        int distanceY = Math.abs(player.getPlayerY() - bossY);
        int attackRange = 12; // Phạm vi tấn công

        if (distanceX < attackRange && distanceY < attackRange) {
            // Gây sát thương cho player
            player.takeDamage(10); // Giả sử boss gây 10 sát thương
            System.out.println("Boss da tan cong player.");
        }
        // Dừng một chút để tạo hiệu ứng di chuyển liên tục mịn màng
            try {
                Thread.sleep(1); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    // Khai báo danh sách các vị trí cho boss
    private final int[][] bossPositions = {
        {250, 250}, // Vị trí 1
        {650, 350}, // Vị trí 2
        {1050, 270}, // Vị trí 3
        {1240, 576} // Vị trí 4
    };
    private int currentPositionIndex = 0; // Chỉ số của vị trí hiện tại trong danh sách

    // Phương thức di chuyển boss đến vị trí mới
    public void moveBossToPosition(int x, int y) {
        // Di chuyển boss
        bossX = x;
        bossY = y;

        // Cập nhật vị trí của thanh máu
        bossHealthBarX = bossX + 20;
        bossHealthBarY = bossY - bossHealthBarHeight + 10;

        try {
            Thread.sleep(1000); // Dừng 10 milliseconds
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
    // Phương thức di chuyển boss đến vị trí ngẫu nhiên
    public void moveBossToRandomPosition() {
        // Lấy một vị trí ngẫu nhiên từ danh sách bossPositions
        Random random = new Random();
        int randomIndex = random.nextInt(bossPositions.length);
        int[] randomPosition = bossPositions[randomIndex];
        int newBossX = randomPosition[0];
        int newBossY = randomPosition[1];

        // Di chuyển boss đến vị trí mới
        moveBossToPosition(newBossX, newBossY);
        System.out.println("Boss da di chuyen den vi tri khac.");

        try {
            Thread.sleep(1); // Dừng 10 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Phương thức quay trở lại vị trí ban đầu
    public void returnToStartPosition(int startX, int startY) {
        // Di chuyển boss về vị trí ban đầu
        bossX = startX;
        bossY = startY;
        // Cập nhật vị trí của thanh máu
            bossHealthBarX = bossX + 20;
            bossHealthBarY = bossY - bossHealthBarHeight + 10;

            try {
                Thread.sleep(10); // Dừng 10 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public boolean getIsBossFacingLeft() {
        return isBossFacingLeft;
    }

    public void setIsBossFacingLeft(boolean isBossFacingLeft) {
        this.isBossFacingLeft = isBossFacingLeft;
    }

    public boolean getIsBossAlive() {
        return isBossAlive;
    }

    public void setIsBossAlive(boolean isBossAlive) {
        this.isBossAlive = isBossAlive;
    }

    public boolean getIsAttack(){
        return isAttack;
    }
     
    public void setIsAttack(boolean isAttack){
        this.isAttack = isAttack;
    }
    public int getBOSS_SPEED() {
        return BOSS_SPEED;
    }

    public int[][] getBossPositions() {
        return bossPositions;
    }

    // Getter cho vị trí x của boss
    public int getBossX() {
        return bossX;
    }

    // Getter cho vị trí y của boss
    public int getBossY() {
        return bossY;
    }

    public int getBossHealth() {
        return bossHealth;
    }

    public void setBossHealth(int bossHealth) {
        this.bossHealth = bossHealth;
    }

    public int getBossMaxHealth() {
        return bossMaxHealth;
    }

    public void setBossMaxHealth(int bossMaxHealth) {
        this.bossMaxHealth = bossMaxHealth;
    }

    public int getBossHealthBarWidth() {
        return bossHealthBarWidth;
    }

    public void setBossHealthBarWidth(int bossHealthBarWidth) {
        this.bossHealthBarWidth = bossHealthBarWidth;
    }

    public int getBossHealthBarHeight() {
        return bossHealthBarHeight;
    }

    public void setBossHealthBarHeight(int bossHealthBarHeight) {
        this.bossHealthBarHeight = bossHealthBarHeight;
    }

    public int getBossHealthBarX() {
        return bossHealthBarX;
    }

    public void setBossHealthBarX(int bossHealthBarX) {
        this.bossHealthBarX = bossHealthBarX;
    }

    public int getBossHealthBarY() {
        return bossHealthBarY;
    }

    public void setBossHealthBarY(int bossHealthBarY) {
        this.bossHealthBarY = bossHealthBarY;
    }

    public int getCurrentPositionIndex() {
        return currentPositionIndex;
    }

    public void setCurrentPositionIndex(int currentPositionIndex) {
        this.currentPositionIndex = currentPositionIndex;
    }

    
}
