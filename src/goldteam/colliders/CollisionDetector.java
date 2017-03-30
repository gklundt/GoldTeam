/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.CollisionPlane;
import goldteam.domain.CollisionRegister;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.util.ArrayList;

/**
 *
 * @author gordon
 */
public class CollisionDetector implements CollisionRegister {

    private final ArrayList<CollisionListener> collisionListeners;
    private final ArrayList<Collidable> collidableObjects;
    private final ArrayList<Collidable> copyCollidableObjects;
    private final GameEngine gameEngine;
    private boolean workingFlag;

    public CollisionDetector(GameEngine gameData) {
        this.collidableObjects = new ArrayList<>();
        this.copyCollidableObjects = new ArrayList<>();
        this.collisionListeners = new ArrayList<>();
        this.gameEngine = gameData;
        this.gameEngine.addCollisionTimer((l) -> this.CheckCollisions());
        this.workingFlag = false;
    }

    private synchronized void CheckCollisions() {
        
        for(Collidable a : collidableObjects){
            GameObject go = (GameObject) a;
            if(go.isRemoveMe()){
                copyCollidableObjects.add(a);
            }
        }
        
        for(Collidable a : copyCollidableObjects){
            GameObject go = (GameObject) a;
            if(go.isRemoveMe()){
                collidableObjects.remove(a);
                go.remove();
            }
        }

        // do logic to find colliding objects 
        // call collision listener event on all listeners
        for (Collidable a : collidableObjects) {
            for (Collidable b : collidableObjects) {
                if (!a.equals(b)) {
                    if (a.getPolygon() != null && b.getPolygon() != null) {
                        if (a.getPolygon().getBounds2D().intersects(b.getPolygon().getBounds2D())) {
                            this.notifyColliders(a, b);
                        }
                    }
                }
            }
        }
    }

    private synchronized void notifyColliders(Collidable a, Collidable b) {
        //System.out.println(String.format("%1$s collided with %2$s", a.getClass().getName(), b.getClass().getName()));
        collisionListeners.forEach((listener) -> {
            listener.CollisionDetected(a, b);
            
        });
    }

    @Override
    public void addCollisionListener(CollisionListener listener) {
        this.collisionListeners.add(listener);
    }

    @Override
    public void removeCollisionListener(CollisionListener listener) {
        this.collisionListeners.remove(listener);
    }

    @Override
    public void registerCollidable(Collidable collidable) {
        this.collidableObjects.add(collidable);
    }

    @Override
    public void removeCollidable(Collidable collidable) {
        this.collidableObjects.remove(collidable);
    }

}
