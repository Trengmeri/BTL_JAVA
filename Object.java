/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameGunny;

import java.awt.Graphics2D;
import GameGunny.DoHoaTTK;
import java.awt.Rectangle;
/**
 *
 * @author admin
 */
public abstract class Object {
    private double posX ;
    private double posY ;
    private DoHoaTTK o;
    public static final int ALIVE = 0;
    public static final int BEHURT = 1;
    public static final int FEY = 2;
    public static final int DEATH = 3;
    
    private int state = ALIVE;
    private boolean alive = true;
    

    public Object(double posX, double posY, DoHoaTTK o) {
        this.posX = posX;
        this.posY = posY;
        this.o = o;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // Phương thức kiểm tra xem đối tượng còn sống hay không
    public boolean isAlive() {
        return alive;
    }
    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public DoHoaTTK getO() {
        return o;
    }

    public void setO(DoHoaTTK o) {
        this.o = o;
    }
    public abstract void Update();

    public abstract void draw(Graphics2D g2) ;

}
