    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package GameGunny;

    import java.awt.Color;
    import java.awt.Graphics2D;
    import java.awt.Rectangle;

    /**
     *
     * @author admin
     */
    public class Bullet extends Object {
        private double speedx;
        private double speedy;
        private int width;
        private int height;

        private int damage;

        public Bullet(double speedx, double speedy, int width, int height,int damage, int posX, int posY, DoHoaTTK o) {
            super(posX, posY, o);
            this.speedx = speedx;
            this.speedy = speedy;
            this.width = width;
            this.height = height;
            this.damage = damage;
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
        
        public Rectangle getBoundForCollisionWithMap(){
            Rectangle bound = new Rectangle();
            bound.x = (int) (getPosX() - (getWidth()/2));
            bound.y = (int) (getPosY() - (getHeight()/2));
            bound.width = (int) getWidth();
            bound.height = (int) getHeight();
            return bound;
        }
        public void drawBoundForCollisionWithMap(Graphics2D g2){
            Rectangle rect = getBoundForCollisionWithMap();
            g2.setColor(Color.BLUE);
            g2.drawRect(rect.x, rect.y, rect.width, rect.height);

        }
        public void move() {
        // Tính toán sự di chuyển theo trục x và y dựa trên vận tốc và gia tốc trọng trường
            double timeInSeconds = 0.005; // Thời gian mỗi bước đếm (10ms = 0.01s)
            setPosX(getPosX() + speedx) ; // Di chuyển theo trục x

            // Tính toán vận tốc theo trục y dựa trên gia tốc trọng trường
                setPosY(getPosY() + 9.81 * speedy);

    }   
        @Override
        public void Update() {
           setPosX((int) (getPosX() + getSpeedx()));
           setPosY((int) (getPosY() + getSpeedy()));
        }

        @Override
        public void draw(Graphics2D g2) {
        // Kiểm tra nếu speedx vẫn lớn hơn 0
            g2.setColor(Color.RED);
            g2.fillOval((int) getPosX(), (int) getPosY(), width, height);    

            // Cập nhật vị trí của đạn
    }
        public Rectangle getBoundForCollisionWithEnemy() {
                // TODO Auto-generated method stub
                return getBoundForCollisionWithMap();
        }
        public void checkCollision(BossTTK boss) {
    // Lấy hình chữ nhật giới hạn của viên đạn
    Rectangle bulletBounds = getBoundForCollisionWithMap();
    
    // Tạo hình chữ nhật giới hạn của boss
    Rectangle bossBounds = new Rectangle(boss.getBossX(), boss.getBossY(), 100, 100);
    
    // Kiểm tra va chạm giữa viên đạn và boss
    if (bulletBounds.intersects(bossBounds)) {
        // Nếu có va chạm, gọi phương thức takeDamage() của boss để giảm sức khỏe
        boss.takeDamage(damage);
        
        // Đánh dấu viên đạn đã va chạm và không còn sống nữa
        setAlive(false);
    }
}

    //    public boolean checkCollision() {
    //    Rectangle bulletRect = getBoundForCollisionWithMap(); // Lấy hình chữ nhật biểu diễn viên đạn
    //    
    //    // Kiểm tra va chạm với các vật thể trong trò chơi
    //    for (Object object : getO().getListObject()) {
    //        Rectangle objectRect = object.getBoundForCollisionWithMap(); // Lấy hình chữ nhật biểu diễn vật thể
    //        
    //        // Kiểm tra va chạm giữa viên đạn và vật thể
    //        if (bulletRect.intersects(objectRect)) {
    //            // Xử lý va chạm tại đây (trả về true nếu cần dừng lại viên đạn khi va chạm)
    //            return true;
    //        }
    //    }
    //    
    //    // Nếu không có va chạm nào, trả về false
    //    return false;
    //}
    }