package GiaoDien;

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
    }
    
    public void draw(Graphics2D g2){
        synchronized(particularObjects){
            for(Object object: particularObjects)
                object.draw(g2);
        }
    }
	
}