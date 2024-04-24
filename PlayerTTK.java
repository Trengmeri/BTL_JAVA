/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameGunny;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayerTTK extends Object{
    private int speed;
    private int width;
    private int height;
    private int health;
    private int direction;
    public static int DIR_LEFT;
    public static int DIR_RIGHT;
    private boolean isPlayerAlive = true; // Theo dõi còn sống
    private boolean isFacingLeft = false; 
    
    private double playerHealthBarWidth = 90; // Độ rộng của thanh máu
    private double playerHealthBarHeight = 10; // Độ cao của thanh máu
    private double playerHealthBarX; // Vị trí x của thanh máu
    private double playerHealthBarY; // Vị trí y của thanh máu
    private int angle;
    
    private BulletManager bullManager;
    private ArrayList<Point> bulletPath;
    public PlayerTTK(int speed, int width, int height, int health, double posX, double posY, DoHoaTTK o) {
        super(posX, posY, o);
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.health = health;
        this.angle = 45;
        this.playerHealthBarX = getPosX() + 30;
        this.playerHealthBarY = getPosY() - playerHealthBarHeight + 10 ;
    }

    public void setBulletManager(BulletManager bulletManager) {
        this.bullManager = bulletManager;
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
            case KeyEvent.VK_LEFT:
                setPosX(getPosX() - getSpeed()); // Di chuyển sang trái
                setIsFacingLeft(true); 
                updateHealthBarPosition();
                //isFacingLeft = true; // Đặt hướng di chuyển sang trái
                break;
            case KeyEvent.VK_RIGHT:
                setPosX(getPosX() + getSpeed()); // Di chuyển sang trái
                setIsFacingLeft(false); 
                updateHealthBarPosition();
                break;
            case KeyEvent.VK_SPACE:
                attack();
                break;
                case KeyEvent.VK_UP:
                // Điều chỉnh góc bắn lên
                if (angle < 90) {
                    angle+=1;
                }
                break;
            case KeyEvent.VK_DOWN:
                // Điều chỉnh góc bắn xuống
                if (angle > 0) {
                    angle-=1;
                }
                break;
        }
    }

    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        Rectangle rect = getBoundForCollisionWithMap();
       
          rect.x = (int) getPosX() - 22;
          rect.y = (int) getPosY() - 20;
          rect.width = 44;
          rect.height = 65;
        
        
        return rect;
    }

    public void drawBoundForCollisionWithMap(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithMap();
        g2.setColor(Color.BLUE);
        g2.drawRect(rect.x, rect.y, rect.width, rect.height);
        
    }
    public void attack() {
         
    double powerLevel= 70;
    double gravity = 10;
    // Thời gian bay
    
    // Tính toán vận tốc ban đầu theo phương x và y
    double timeofFlight = (1.5 * powerLevel * Math.cos(Math.toRadians(angle))) / 10;
    Bullet bullet = new Bullet(100, 100, 10, 10, 10, (int)getPosX() , (int) getPosY(), getO());
    
    for (double t = 0; t <= timeofFlight; t += 0.1) {
        bullet.setPosX(bullet.getPosX() + (bullet.getSpeedx()+powerLevel/10)*Math.cos(Math.toRadians(angle))*timeofFlight);
        bullet.setPosY(bullet.getPosY() - (bullet.getSpeedy()+powerLevel/10)*Math.sin(Math.toRadians(angle))*timeofFlight+10*timeofFlight);
        bulletPath.add(new Point((int) bullet.getPosX(), (int) bullet.getPosY()));
        };
        bullManager.addObject(bullet);
    }
    public ArrayList<Point> getBulletPath() {
        return bulletPath;
    }
    // Cập nhật vị trí của thanh máu
    private void updateHealthBarPosition() {
        this.playerHealthBarX = getPosX() + 30;
        this.playerHealthBarY = getPosY() - playerHealthBarHeight + 10;
    }
    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = (int) angle;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDirection() {
        return direction;
    }
     public void setIsFacingLeft(boolean isFacingLeft) {
        this.isFacingLeft = isFacingLeft;
    }
      public boolean getIsFacingLeft() {
        return isFacingLeft;
    }
    // Getter và setter cho các thuộc tính
    // (Đã bỏ các setter không cần thiết và sửa lại phương thức setSpeed)
    public void setDirection(int direction) {    
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
   public void setIsPlayerAlive(boolean isPlayerAlive) {
        this.isPlayerAlive = isPlayerAlive;
    }

    public void setPlayerHealthBarWidth(double playerHealthBarWidth) {
        this.playerHealthBarWidth = playerHealthBarWidth;
    }

    public void setPlayerHealthBarHeight(double playerHealthBarHeight) {
        this.playerHealthBarHeight = playerHealthBarHeight;
    }

    public void setPlayerHealthBarX(double playerHealthBarX) {
        this.playerHealthBarX = playerHealthBarX;
    }

    public void setPlayerHealthBarY(double playerHealthBarY) {
        this.playerHealthBarY = playerHealthBarY;
    }

    
    public boolean getIsPlayerAlive() {
        return isPlayerAlive;
    }

    public void setPlayerAlive(boolean playerAlive) {
        isPlayerAlive = playerAlive;
    }

    public double getPlayerHealthBarWidth() {
        return playerHealthBarWidth;
    }

    public double getPlayerHealthBarHeight() {
        return playerHealthBarHeight;
    }

    public double getPlayerHealthBarX() {
        return playerHealthBarX;
    }

    public double getPlayerHealthBarY() {
        return playerHealthBarY;
    }   

    @Override
    public void Update() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void draw(Graphics2D g2) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
