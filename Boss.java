package GiaoDien;

import java.awt.Image;
import java.util.Random;

public class Boss {
    private final int BOSS_SPEED = 10; // Tốc độ di chuyển của boss
    private int bossX; // Vị trí x của nhân vật số 2
    private int bossY; // Vị trí y của nhân vật số 2
    private Image bossImageGC;
    
    private boolean isBossFacingLeft = false; // Theo dõi hướng di chuyển của player
    private boolean isBossAlive = true; // Theo dõi còn sống
    
    private boolean isAttacking = false;
    //Thanh máu của nhân vật số 2
    private int bossHealth = 100; // Sức khỏe ban đầu của nhân vật số 2
    private int bossMaxHealth = 100; // Sức khỏe tối đa của nhân vật số 2
    private int bossHealthBarWidth = 90; // Độ rộng của thanh máu của nhân vật số 2
    private int bossHealthBarHeight = 10; // Độ cao của thanh máu của nhân vật số 2
    private int bossHealthBarX; // Vị trí x của thanh máu của nhân vật số 2
    private int bossHealthBarY; // Vị trí y của thanh máu của nhân vật số 2

    // Constructor
    public Boss(int initialX, int initialY) {
        this.bossX = initialX;
        this.bossY = initialY;
        this.bossHealthBarX = initialX;
        this.bossHealthBarY = initialY - bossHealthBarHeight;
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

            // Dừng một chút để tạo hiệu ứng di chuyển liên tục mịn màng
            try {
                Thread.sleep(5); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void takeDamage(int damage) {
    bossHealth -= damage;
    if (bossHealth <= 0) {
        isBossAlive = false;
        // Xử lý sau khi boss bị hạ gục
    }
    }
    // Phương thức gây sát thương cho player
    public void attackPlayer(Player player) {
        // Kiểm tra xem player có ở gần boss không
        int distanceX = Math.abs((int)player.getPlayerX() - bossX);
        int distanceY = Math.abs((int)player.getPlayerY() - bossY);
        int attackRange = 12; // Phạm vi tấn công

        if (distanceX < attackRange && distanceY < attackRange) {
            // Gây sát thương cho player
            player.takeDamage(5); // Giả sử boss gây 10 sát thương
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
    private final int[][][] bossPositions = {
        // Màn chơi OKM
        {
            {500, 250}, // Vị trí 1
            {850, 250}, // Vị trí 2
        },
        // Màn chơi GC
        {
            {400, 200}, // Vị trí 1
            {650, 300}, // Vị trí 2
            {950, 200}, // Vị trí 3
        },
        // Màn chơi TTK
        {
            {250, 250}, // Vị trí 1
            {650, 350}, // Vị trí 2
            {1050, 270}, // Vị trí 3
        }
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
            Thread.sleep(1); // Dừng 10 milliseconds
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
    // Phương thức di chuyển boss đến vị trí ngẫu nhiên trong màn chơi được chỉ định
    public void moveBossToRandomPosition(int levelIndex) {
        // Kiểm tra xem levelIndex có hợp lệ không
        if (levelIndex < 0 || levelIndex >= bossPositions.length) {
            System.out.println("Màn chơi không hợp lệ.");
            return;
        }

        // Lấy một vị trí ngẫu nhiên từ danh sách bossPositions của màn chơi hiện tại
        Random random = new Random();
        int[][] currentLevelPositions = bossPositions[levelIndex];
        
        int randomIndex = random.nextInt(currentLevelPositions.length);
        int[] randomPosition = currentLevelPositions[randomIndex];
        int newBossX = randomPosition[0];
        int newBossY = randomPosition[1];

        // Di chuyển boss đến vị trí mới
        moveBossToPosition(newBossX, newBossY);
        System.out.println("Boss đã di chuyển đến vị trí khác.");

        try {
            Thread.sleep(10); // Dừng 10 milliseconds
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

    public boolean getIsAttacking() {
        return isAttacking;
    }

    public void setIsAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

    public int getBOSS_SPEED() {
        return BOSS_SPEED;
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
