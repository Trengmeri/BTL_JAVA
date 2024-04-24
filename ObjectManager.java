/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoDien;

/**
 *
 * @author LENOVO
 */
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObjectManager {

    protected List<Object> particularObjects;

    private Object gameWorld;
    
    public ObjectManager(Object gameWorld){
        
        particularObjects = Collections.synchronizedList(new LinkedList<Object>());
        this.gameWorld = gameWorld;
        
    }
    
    public Object getGameWorld(){
        return gameWorld;
    }
    
    public void addObject(Object particularObject){
        
        
        synchronized(particularObjects){
            particularObjects.add(particularObject);
        }
        
    }
    
    public void RemoveObject(Object particularObject){
        synchronized(particularObjects){
        
            for(int id = 0; id < particularObjects.size(); id++){
                
                Object object = particularObjects.get(id);
                if(object == particularObject)
                    particularObjects.remove(id);

            }
        }
    }
    
//    public Object getCollisionWidthEnemyObject(Object object){
//        synchronized(particularObjects){
//            for(int id = 0; id < particularObjects.size(); id++){
//                
//                Object objectInList = particularObjects.get(id);
//
//                if(object.getTeamType() != objectInList.getTeamType() && 
//                        object.getBoundForCollisionWithEnemy().intersects(objectInList.getBoundForCollisionWithEnemy())){
//                    return objectInList;
//                }
//            }
//        }
//        return null;
//    }
    
    public void UpdateObjects(){
        
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                Object object = particularObjects.get(id);
                
                
                 object.Update();
                
                if(object.getState() == Object.DEATH){
                    particularObjects.remove(id);
                }
            }
        }

        //System.out.println("Camerawidth  = "+camera.getWidth());
        
    }
    
    public void draw(Graphics2D g2){
        synchronized(particularObjects){
            for(Object object: particularObjects)
                object.draw(g2);
        }
    }
	
}
