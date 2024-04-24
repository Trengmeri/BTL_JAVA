/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoDien;

import GiaoDien.Object;
public class BulletManager extends ObjectManager {

    public BulletManager(Object gameWorld) {
        super(gameWorld);
    }
    public void updateBullets() {
    synchronized(particularObjects){
        for(int id = 0; id < particularObjects.size(); id++){
            Object object = particularObjects.get(id);
            if(object instanceof Bullet) {
                Bullet bullet = (Bullet) object;
                bullet.Update(); // Cập nhật vị trí của viên đạn
            }
        }
    }
    }
    @Override
    public void UpdateObjects() {
    super.UpdateObjects();
    synchronized (particularObjects) {
        for (int id = 0; id < particularObjects.size(); id++) {
            Object object = particularObjects.get(id);
            if (object.getState() == Object.DEATH) {
                particularObjects.remove(id);
            }
        }
    }
}
 // Phương thức để xóa tất cả các viên đạn
    public void clearBullets() {
        synchronized(particularObjects) {
            particularObjects.clear();
        }
    }
    
    
}