package GiaoDien;

public class Player {
    private int playerSpeed = 6; // Tốc độ di chuyển của player
    private int playerX; // Vị trí x của người chơi
    private int playerY; // Vị trí y của người chơi
    private int health = 100; // Máu
    private int MAX_HEALTH = 100; // Máu tối đa của người chơi

    private boolean isFacingLeft = false; // Theo dõi hướng di chuyển của player
    private boolean isPlayerAlive = true; // Theo dõi còn sống
    private boolean isMoving = false; //Theo dõi chuyển động nhân vật
    private boolean Uping = false; //Theo dõi tư thế bắn
    
    // Thanh máu của nhân vật số 1
    private int playerHealthBarWidth = 90; // Độ rộng của thanh máu
    private int playerHealthBarHeight = 10; // Độ cao của thanh máu
    private int playerHealthBarX; // Vị trí x của thanh máu
    private int playerHealthBarY; // Vị trí y của thanh máu

    // Constructor
    public Player(int initialX, int initialY) {
        this.playerX = initialX;
        this.playerY = initialY;
        this.playerHealthBarX = initialX + 30;
        this.playerHealthBarY = initialY - playerHealthBarHeight + 10;
    }

    // Phương thức nhận sát thương
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    // Di chuyển người chơi sang một vị trí mới
    public void movePlayer(int newX, int newY) {
        this.playerX = newX;
        this.playerY = newY;
        updateHealthBarPosition();
    }

    // Cập nhật vị trí của thanh máu
    private void updateHealthBarPosition() {
        this.playerHealthBarX = playerX + 30;
        this.playerHealthBarY = playerY - playerHealthBarHeight + 10;
    }

    // Getter và setter cho các thuộc tính
    // (Đã bỏ các setter không cần thiết và sửa lại phương thức setSpeed)
    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getHealth() {
        return health;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMAX_HEALTH(int MAX_HEALTH) {
        this.MAX_HEALTH = MAX_HEALTH;
    }

    public void setIsFacingLeft(boolean isFacingLeft) {
        this.isFacingLeft = isFacingLeft;
    }

    public void setIsPlayerAlive(boolean isPlayerAlive) {
        this.isPlayerAlive = isPlayerAlive;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    public void setUping(boolean Uping) {
        this.Uping = Uping;
    }
    
    public void setPlayerHealthBarWidth(int playerHealthBarWidth) {
        this.playerHealthBarWidth = playerHealthBarWidth;
    }

    public void setPlayerHealthBarHeight(int playerHealthBarHeight) {
        this.playerHealthBarHeight = playerHealthBarHeight;
    }

    public void setPlayerHealthBarX(int playerHealthBarX) {
        this.playerHealthBarX = playerHealthBarX;
    }

    public void setPlayerHealthBarY(int playerHealthBarY) {
        this.playerHealthBarY = playerHealthBarY;
    }

    
    public boolean getIsFacingLeft() {
        return isFacingLeft;
    }
    
    public boolean getIsPlayerAlive() {
        return isPlayerAlive;
    }
    
    public boolean getIsMoving() {
        return isMoving;
    }
    
    public boolean getUping() {
        return Uping;
    }

    public void setPlayerAlive(boolean playerAlive) {
        isPlayerAlive = playerAlive;
    }

    public int getPlayerHealthBarWidth() {
        return playerHealthBarWidth;
    }

    public int getPlayerHealthBarHeight() {
        return playerHealthBarHeight;
    }

    public int getPlayerHealthBarX() {
        return playerHealthBarX;
    }

    public int getPlayerHealthBarY() {
        return playerHealthBarY;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    
}
