/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoDien;

import java.awt.Color;
    import java.awt.Graphics2D;
    import java.awt.Rectangle;

import javax.swing.JOptionPane;

    /**
     *
     * @author admin
     */
    public class Bullet extends Object {
        private double speedx;
        private double speedy;

        private int damage;
        private BOM bom;

        public Bullet(double speedx, double speedy, int damage, double posX, double posY, DoHoaOKM o1,DoHoaGC o2,DoHoaTTK o3) {
            super(posX, posY, o1,o2,o3);
            this.speedx = speedx;
            this.speedy = speedy;
            this.damage = damage;
            try {
            	bom = new BOM();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading images: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        public int getDamage() {
            return damage;
        }

        public void setDamage(int damage) {
            this.damage = damage;
        }


        public double getSpeedx() {
            return speedx;
        }

        public void setSpeedx(double speedx) {
            this.speedx = speedx;
        }

        public double getSpeedy() {
            return speedy;
        }

        public void setSpeedy(double speedy) {
            this.speedy = speedy;
        }
        
        public Rectangle getBoundForCollisionWithMap(){
            Rectangle bound = new Rectangle();
            bound.x = (int) (getPosX() - 100);
            bound.y = (int) (getPosY() - 100);
            bound.width = (int) 200;
            bound.height = (int) 100;
            return bound;
        }
        public void drawBoundForCollisionWithMap(Graphics2D g2){
            Rectangle rect = getBoundForCollisionWithMap();
            g2.setColor(Color.BLUE);
            g2.drawRect(rect.x, rect.y, rect.width, rect.height);

        }
        @Override
        public void Update() {
           setPosX( (getPosX() + getSpeedx()));
           setPosY( (getPosY() + getSpeedy()));
        }

        @Override
        public void draw(Graphics2D g2) {
        // Kiểm tra nếu speedx vẫn lớn hơn 0
            bom.paintComponent(g2);
            bom.setX((int) getPosX() -25);
            bom.setY((int) getPosY() -55);
        	//g2.setColor(Color.red);
            g2.fillOval((int) getPosX(), (int) getPosY(), 20,  10);    

            // Cập nhật vị trí của đạn
    }
        public void checkCollision(Boss boss) {
    // Lấy hình chữ nhật giới hạn của viên đạn
            Rectangle bulletBounds = getBoundForCollisionWithMap();
    
    // Tạo hình chữ nhật giới hạn của boss
                Rectangle bossBounds = new Rectangle(boss.getBossX(), boss.getBossY(), 200, 200);
    
    // Kiểm tra va chạm giữa viên đạn và boss
            if (bulletBounds.intersects(bossBounds)) {
        // Nếu có va chạm, gọi phương thức takeDamage() của boss để giảm sức khỏe
             boss.takeDamage(damage);
        
        // Đánh dấu viên đạn đã va chạm và không còn sống nữa
                setAlive(false);
    }
        }
    }