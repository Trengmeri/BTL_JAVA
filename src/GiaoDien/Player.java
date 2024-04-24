package GiaoDien;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends Object {
    private int speed = 6; // Tốc độ di chuyển của player
    private double playerX; // Vị trí x của người chơi
    private double playerY; // Vị trí y của người chơi
    private int health = 100;
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
    
    private int angle;
    private double power;
    private BulletManager bullManager;

    // Constructor
    public Player(double px, double py,DoHoaOKM o1,DoHoaGC o2,DoHoaTTK o3) {
        super(px,py,o1,o2,o3);
        this.playerX = px;
        this.playerY = py;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.angle = 45;
        this.power = 0;
        this.playerHealthBarX = (int)playerX + 30;
        this.playerHealthBarY =(int) playerY - playerHealthBarHeight + 10 ;
    }
    

    // Phương thức nhận sát thương
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    // Di chuyển người chơi sang một vị trí mới
   public void move(KeyEvent e){
        int keycode = e.getKeyCode();
        switch(keycode){
            case KeyEvent.VK_LEFT -> {
                setPlayerX(getPlayerX() - getSpeed()); // Di chuyển sang trái
                setIsFacingLeft(true); 
                updateHealthBarPosition();
                //isFacingLeft = true; // Đặt hướng di chuyển sang trái
            }
            case KeyEvent.VK_RIGHT -> {
                setPlayerX(getPlayerX() + getSpeed()); // Di chuyển sang trái
                setIsFacingLeft(false); 
                updateHealthBarPosition();
            }
            
        }
    }
    public void attack(Boss boss) {
         
    int gravity = 10;
   
    
    double timeofFlight =  power / 10;
    Bullet bullet = new Bullet(100, 20, 10, getPlayerX() ,  getPlayerY(),  getO1(),getO2(),getO3());   
    
    bullet.setPosX(bullet.getPosX()+100+ bullet.getSpeedx()*timeofFlight);
    bullet.setPosY(bullet.getPosY()- bullet.getSpeedx()*timeofFlight/(Math.tan(Math.toRadians(angle-80))));
 
        
        bullManager.addObject(bullet);
        bullet.checkCollision(boss);                          
    }
   
    // Cập nhật vị trí của thanh máu
    public void updateHealthBarPosition() {
        this.playerHealthBarX =(int) playerX + 30;
        this.playerHealthBarY =(int) playerY - playerHealthBarHeight + 10;
    }
    public double getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power =  power;
    }
    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public BulletManager getBullManager() {
        return bullManager;
    }
    
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    public boolean getIsMoving() {
        return isMoving;
    }
    
    public void setUping(boolean Uping) {
        this.Uping = Uping;
    }
    
    public boolean getUping() {
        return Uping;
    }

    // Getter và setter cho các thuộc tính
    // (Đã bỏ các setter không cần thiết và sửa lại phương thức setSpeed)
    public void setBullManager(BulletManager bullManager) {    
        this.bullManager = bullManager;
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    public int getHealth() {
        return health;
    }

    public void setPlayerX(double playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(double playerY) {
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

    public int getSpeed() {
        return speed;
    }

    public void setPlayerSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public void Update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void draw(Graphics2D g2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}