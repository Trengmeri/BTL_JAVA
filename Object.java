/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoDien;
import GiaoDien.DoHoaOKM;
import GiaoDien.DoHoaGC;
import GiaoDien.DoHoaTTK;
import java.awt.Graphics2D;

/**
 *
 * @author LENOVO
 */
public abstract class Object {
    private double posX ;
    private double posY ;
    DoHoaOKM o1;
    DoHoaGC o2;
    DoHoaTTK o3;
    public static final int ALIVE = 0;
    public static final int BEHURT = 1;
    public static final int FEY = 2;
    public static final int DEATH = 3;
    
    private int state = ALIVE;
    private boolean alive = true;
    

    public Object(double posX, double posY, DoHoaOKM o1,DoHoaGC o2, DoHoaTTK o3) {
        this.posX = posX;
        this.posY = posY;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
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

    public DoHoaOKM getO1() {
        return o1;
    }

    public void setO1(DoHoaOKM o1) {
        this.o1 = o1;
    }

    public DoHoaGC getO2() {
        return o2;
    }

    public void setO2(DoHoaGC o2) {
        this.o2 = o2;
    }

    public DoHoaTTK getO3() {
        return o3;
    }

    public void setO3(DoHoaTTK o3) {
        this.o3 = o3;
    }
    public abstract void Update();

    public abstract void draw(Graphics2D g2) ;

}